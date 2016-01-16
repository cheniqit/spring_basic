package com.mk.taskfactory.biz.impl;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.mk.framework.manager.RedisCacheName;
import com.mk.framework.proxy.http.RedisUtil;
import com.mk.taskfactory.api.*;
import com.mk.taskfactory.api.dtos.*;
import com.mk.taskfactory.biz.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;

import java.util.*;

@Service
public class PriceToRedisServiceImpl implements PriceToRedisService {
    private static Logger logger = LoggerFactory.getLogger(PriceToRedisServiceImpl.class);

    @Autowired
    private RoomSaleAgreementPriceService priceService;

    public Map<String,Object> priceToRedis(TRoomSaleAgreementPriceDto dto){
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
                List<TRoomSaleAgreementPriceDto> agreementPriceList = priceService.qureyByPramas(dto);
                if (CollectionUtils.isEmpty(agreementPriceList)){
                    logger.info(String.format("\n====================agreementPriceList is empty====================\n"));
                    continue;
                }

                Map<Integer,String> hotelMap = new HashMap<Integer, String>();

                    for (TRoomSaleAgreementPriceDto agreementPrice:agreementPriceList){
                        if (agreementPrice.getHotelId()==null||agreementPrice.getRoomTypeId()==null){
                            logger.info(String.format("\n====================hotelId={}&roomTypeId={}====================\n")
                            ,agreementPrice.getHotelId(),agreementPrice.getRoomTypeId());
                            continue;
                        }
                        logger.info(String.format("\n====hotelId={}&roomTypeId={}&settleValue={}&meiTuanPrice={}======\n")
                        ,agreementPrice.getHotelId(),agreementPrice.getRoomTypeId(),agreementPrice.getSettleValue(),agreementPrice.getMeiTuanPrice());
                        if (agreementPrice.getSettleValue()!=null)
                            jedis.set(String.format("%s%s:%s", RedisCacheName.DYNAMIC_PRICE_AGREEMENT,
                                    agreementPrice.getHotelId(),agreementPrice.getRoomTypeId()),
                                    agreementPrice.getSettleValue().toString());
                        if (agreementPrice.getMeiTuanPrice()!=null)
                            jedis.set(String.format("%s%s:%s", RedisCacheName.DYNAMIC_PRICE_MEITUAN,
                                agreementPrice.getHotelId(),agreementPrice.getRoomTypeId()),
                                agreementPrice.getMeiTuanPrice().toString());

                        if (hotelMap.get(agreementPrice.getHotelId()) == null) {
                            jedis.set(String.format("%s%s", RedisCacheName.DYNAMIC_PRICE_AGREEMENT,
                                    agreementPrice.getHotelId()),
                                    "1");
                            hotelMap.put(agreementPrice.getHotelId(), agreementPrice.getHotelName());
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
    public Map<String,Object> updateDealCountToRedis(TRoomSaleAgreementPriceDto dto){
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
                List<TRoomSaleAgreementPriceDto> agreementPriceList = priceService.qureyByPramas(dto);
                if (CollectionUtils.isEmpty(agreementPriceList)){
                    logger.info(String.format("\n====================agreementPriceList is empty====================\n"));
                    continue;
                }

                for (TRoomSaleAgreementPriceDto agreementPrice:agreementPriceList){
                    if (agreementPrice.getHotelId()==null||agreementPrice.getRoomTypeId()==null){
                        logger.info(String.format("\n====================hotelId={}&roomTypeId={}====================\n")
                                ,agreementPrice.getHotelId(),agreementPrice.getRoomTypeId());
                        continue;
                    }
                    logger.info(String.format("\n====hotelId={}&roomTypeId={}&dealCount={}======\n")
                            ,agreementPrice.getHotelId(),agreementPrice.getRoomTypeId(),agreementPrice.getDealCount());
                    if (agreementPrice.getDealCount()!=null)
                        jedis.set(String.format("%s%s:%s", RedisCacheName.DYNAMIC_DEALCOUNT,
                                agreementPrice.getHotelId(),agreementPrice.getRoomTypeId()),
                                agreementPrice.getDealCount().toString());
                        jedis.set(String.format("%s%s:%s", RedisCacheName.DYNAMIC_STORECOUNT,
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
    public Map<String,Object> deleteRedis(Integer id,String key){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Cat.logEvent("deleteRedis","删除Radis",Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date()));
        logger.info(String.format("\n====================deleteRedis begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));
        logger.info(String.format("\n=========id={}&key={}=======\n"),id,key);
        if (id==null&&StringUtils.isEmpty(key)){
            resultMap.put("message","参数为空");
            resultMap.put("SUCCESS", false);
            return resultMap;
        }
        Jedis jedis = RedisUtil.getJedis();
        try {
            if (id!=null){
                TRoomSaleAgreementPriceDto dto = priceService.getById(id);
                if (dto==null||dto.getHotelId()==null||dto.getRoomTypeId()==null){
                    logger.info(String.format("\n====================hotelId={}&roomTypeId={}====================\n")
                            ,dto.getHotelId(),dto.getRoomTypeId());
                    resultMap.put("message","dto select is null");
                    resultMap.put("SUCCESS", true);
                    return resultMap;
                }
                logger.info(String.format("\n====================hotelId={}&roomTypeId={}====================\n")
                        ,dto.getHotelId(),dto.getRoomTypeId());
                jedis.del(String.format("%s%s:%s", RedisCacheName.DYNAMIC_PRICE_AGREEMENT,
                        dto.getHotelId(),dto.getRoomTypeId())
                );
                jedis.del(String.format("%s%s:%s", RedisCacheName.DYNAMIC_PRICE_MEITUAN,
                        dto.getHotelId(),dto.getRoomTypeId())
                );
                jedis.del(String.format("%s%s", RedisCacheName.DYNAMIC_PRICE_AGREEMENT,
                        dto.getHotelId()));
                jedis.del(String.format("%s%s:%s", RedisCacheName.DYNAMIC_DEALCOUNT,
                        dto.getHotelId(),dto.getRoomTypeId())
                );
                TRoomSaleAgreementPriceDto checkBean = new TRoomSaleAgreementPriceDto();
                checkBean.setHotelId(dto.getHotelId());
                checkBean.setValid("T");
                int checkCount = priceService.countByPramas(checkBean);
                if (checkCount<0) {
                    jedis.del(String.format("%s%s:%s", RedisCacheName.DYNAMIC_DEALCOUNT,
                            dto.getHotelId(), dto.getRoomTypeId())
                    );
                }


            }else if (StringUtils.isNotEmpty(key)){
                jedis.del(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
        Cat.logEvent("deleteRedis", "删除Radis", Event.SUCCESS,
                "endTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info(String.format("\n====================deleteRedis  endTime={}====================\n")
                , DateUtils.format_yMdHms(new Date()));
        resultMap.put("message","执行结束");
        resultMap.put("SUCCESS", true);
        return resultMap;
    }
}
