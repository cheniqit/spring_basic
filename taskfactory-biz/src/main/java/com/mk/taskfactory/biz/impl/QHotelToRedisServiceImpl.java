package com.mk.taskfactory.biz.impl;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.mk.framework.manager.RedisCacheName;
import com.mk.framework.proxy.http.RedisUtil;
import com.mk.taskfactory.api.PriceToRedisService;
import com.mk.taskfactory.api.QHotelToRedisService;
import com.mk.taskfactory.api.RoomSaleAgreementPriceService;
import com.mk.taskfactory.api.dtos.QHotelDto;
import com.mk.taskfactory.api.dtos.TRoomSaleAgreementPriceDto;
import com.mk.taskfactory.api.ots.QHotelService;
import com.mk.taskfactory.biz.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class QHotelToRedisServiceImpl implements QHotelToRedisService {
    private static Logger logger = LoggerFactory.getLogger(QHotelToRedisServiceImpl.class);

    @Autowired
    private QHotelService hotelService;
    private static ExecutorService pool = Executors.newFixedThreadPool(40);


    public Map<String,Object> qHotelToRedis(QHotelDto dto){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Cat.logEvent("qHotelToRedis","QHotel同步到Radis",Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date()));
        logger.info(String.format("\n====================qHotelToRedis begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));

        int count = hotelService.count(dto);
        if (count<=0){
            resultMap.put("message","QHotel count is 0");
            resultMap.put("SUCCESS", false);
            return resultMap;
        }
        int pageSize=1000;
        int pageCount=count/pageSize;
        logger.info(String.format("\n====================size={}&pageSize={}&pageCount={}====================\n")
                ,count,pageSize,pageCount);
        for (int i=0;i<=pageCount;i++){
            logger.info(String.format("\n====================pageIndex={}====================\n")
                    ,i*pageSize);
            dto.setPageSize(pageSize);
            dto.setPageIndex(i*pageSize);
            List<QHotelDto> hotelList = hotelService.qureyByPramas(dto);
            if (CollectionUtils.isEmpty(hotelList)){
                logger.info(String.format("\n====================hotelList is empty====================\n"));
                continue;
            }

            for (final QHotelDto hotelDto:hotelList){
                pool.execute(new Runnable() {
                    @Override
                    public void run() {
                        Jedis jedis = null;
                        try {
                            jedis =  RedisUtil.getJedis();
                            logger.info(String.format("\n====================sourceId={}====================\n")
                                    ,hotelDto.getSourceId());
                            if (hotelDto.getSourceId()!=null){
                                jedis.set(String.format("%s%s", RedisCacheName.QHOTE,
                                        hotelDto.getSourceId()),
                                        "1");
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("qHotelToRedis::error::hotelid:"+hotelDto.getSourceId());
                            logger.error("qHotelToRedis::error{}{}",hotelDto.getSourceId(),e.getMessage());
                        }finally {
                            if(null != jedis){
                                jedis.close();
                            }

                        }

                    }
                });

            }

        }
        Cat.logEvent("qHotelToRedis", "QHotel同步到Radis", Event.SUCCESS,
                "endTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info(String.format("\n====================qHotelToRedis  endTime={}====================\n")
                , DateUtils.format_yMdHms(new Date()));
        resultMap.put("message","执行结束");
        resultMap.put("SUCCESS", true);
        return resultMap;
    }

}
