package com.mk.taskfactory.biz.impl;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.mk.framework.manager.RedisCacheName;
import com.mk.framework.proxy.http.RedisUtil;
import com.mk.taskfactory.api.*;
import com.mk.taskfactory.api.dtos.*;
import com.mk.taskfactory.api.enums.ValidEnum;
import com.mk.taskfactory.biz.mapper.ots.HotelMapper;
import com.mk.taskfactory.biz.mapper.ots.RoomSaleConfigInfoMapper;
import com.mk.taskfactory.biz.utils.DateUtils;
import com.mk.taskfactory.model.TRoomSaleConfigInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PriceToRedisServiceImpl implements PriceToRedisService {
    private static Logger logger = LoggerFactory.getLogger(PriceToRedisServiceImpl.class);

    @Autowired
    private RoomSaleAgreementPriceService priceService;

    public Map<String,Object> priceToRedis(RoomSaleAgreementPriceDto dto){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Cat.logEvent("priceToRedis","协议价同步到Radis",Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date()));
        logger.info(String.format("\n====================priceToRedis begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));
        dto.setValid("T");
        dto.setIsLeZhu("T");
        int count = priceService.countByPramas(dto);
        if (count<=0){
            resultMap.put("message","agrementPrice count is 0");
            resultMap.put("SUCCESS", false);
            return resultMap;
        }
        int pageSize=1000;
        int pageCount=count/pageSize;
        logger.info(String.format("\n====================size={}&pageSize={}&pageCount={}====================\n")
                ,count,pageSize,pageCount);
        Jedis jedis = RedisUtil.getJedis();
        try {
            for (int i=0;i<=pageCount;i++){
                logger.info(String.format("\n====================pageIndex={}====================\n")
                        ,i*pageSize);
                dto.setPageSize(pageSize);
                dto.setPageIndex(i*pageSize);
                List<RoomSaleAgreementPriceDto> agreementPriceList = priceService.qureyByPramas(dto);
                if (CollectionUtils.isEmpty(agreementPriceList)){
                    logger.info(String.format("\n====================agreementPriceList is empty====================\n"));
                    continue;
                }

                Map<Integer,String> hotelMap = new HashMap<Integer, String>();

                    for (RoomSaleAgreementPriceDto agreementPrice:agreementPriceList){
                        if (agreementPrice.getHotelId()==null||agreementPrice.getRoomTypeId()==null){
                            logger.info(String.format("\n====================hotelId={}&roomTypeId={}====================\n")
                            ,agreementPrice.getHotelId(),agreementPrice.getRoomTypeId());
                            continue;
                        }
                        if (agreementPrice.getSettleValue()!=null)
                            jedis.set(String.format("%s:%s:%s", RedisCacheName.DYNAMIC_PRICE_AGREEMENT,
                                    agreementPrice.getHotelId(),agreementPrice.getRoomTypeId()),
                                    agreementPrice.getSettleValue().toString());
                        if (agreementPrice.getMeiTuanPrice()!=null)
                            jedis.set(String.format("%s:%s:%s", RedisCacheName.DYNAMIC_PRICE_MEITUAN,
                                agreementPrice.getHotelId(),agreementPrice.getRoomTypeId()),
                                agreementPrice.getMeiTuanPrice().toString());
                        if (hotelMap.get(agreementPrice.getHotelId())==null){
                            jedis.set(String.format("%s:%s", RedisCacheName.DYNAMIC_PRICE_AGREEMENT,
                                    agreementPrice.getHotelId()),
                                    "1");
                            hotelMap.put(agreementPrice.getHotelId(),agreementPrice.getHotelName());
                        }
                    }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
        Cat.logEvent("priceToRedis", "协议价同步到Radis", Event.SUCCESS,
                "endTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info(String.format("\n====================priceToRedis  endTime={}====================\n")
                , DateUtils.format_yMdHms(new Date()));
        resultMap.put("message","执行结束");
        resultMap.put("SUCCESS", true);
        return resultMap;
    }
    public Map<String,Object> updateDealCountToRedis(RoomSaleAgreementPriceDto dto){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Cat.logEvent("updateDealCountToRedis","协议价同步到Radis",Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date()));
        logger.info(String.format("\n====================updateDealCountToRedis begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));
        dto.setValid("T");
        dto.setIsLeZhu("T");
        int count = priceService.countByPramas(dto);
        if (count<=0){
            resultMap.put("message","agrementPrice count is 0");
            resultMap.put("SUCCESS", false);
            return resultMap;
        }
        int pageSize=1000;
        int pageCount=count/pageSize;
        logger.info(String.format("\n====================size={}&pageSize={}&pageCount={}====================\n")
                ,count,pageSize,pageCount);
        Jedis jedis = RedisUtil.getJedis();
        try {
            for (int i=0;i<=pageCount;i++){
                logger.info(String.format("\n====================pageIndex={}====================\n")
                        ,i*pageSize);
                dto.setPageSize(pageSize);
                dto.setPageIndex(i*pageSize);
                List<RoomSaleAgreementPriceDto> agreementPriceList = priceService.qureyByPramas(dto);
                if (CollectionUtils.isEmpty(agreementPriceList)){
                    logger.info(String.format("\n====================agreementPriceList is empty====================\n"));
                    continue;
                }

                for (RoomSaleAgreementPriceDto agreementPrice:agreementPriceList){
                    if (agreementPrice.getHotelId()==null||agreementPrice.getRoomTypeId()==null){
                        logger.info(String.format("\n====================hotelId={}&roomTypeId={}====================\n")
                                ,agreementPrice.getHotelId(),agreementPrice.getRoomTypeId());
                        continue;
                    }
                    if (agreementPrice.getDealCount()!=null)
                        jedis.set(String.format("%s:%s:%s", RedisCacheName.DYNAMIC_DEALCOUNT,
                                agreementPrice.getHotelId(),agreementPrice.getRoomTypeId()),
                                agreementPrice.getDealCount().toString());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
        Cat.logEvent("updateDealCountToRedis", "协议价同步到Radis", Event.SUCCESS,
                "endTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info(String.format("\n====================updateDealCountToRedis  endTime={}====================\n")
                , DateUtils.format_yMdHms(new Date()));
        resultMap.put("message","执行结束");
        resultMap.put("SUCCESS", true);
        return resultMap;
    }

}
