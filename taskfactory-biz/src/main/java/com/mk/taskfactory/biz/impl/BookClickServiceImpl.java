package com.mk.taskfactory.biz.impl;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.mk.framework.AppUtils;
import com.mk.framework.manager.RedisCacheName;
import com.mk.framework.proxy.http.RedisUtil;
import com.mk.taskfactory.api.BookClickService;
import com.mk.taskfactory.api.DynamicPriceService;
import com.mk.taskfactory.api.RoomSaleAgreementPriceService;
import com.mk.taskfactory.api.dtos.*;
import com.mk.taskfactory.api.ots.HotelDayClickService;
import com.mk.taskfactory.api.ots.HotelHourClickService;
import com.mk.taskfactory.api.ots.RoomTypeDynamicPriceService;
import com.mk.taskfactory.biz.mapper.ots.HotelMapper;
import com.mk.taskfactory.biz.utils.DateUtils;
import com.mk.taskfactory.biz.utils.HttpUtils;
import com.mk.taskfactory.biz.utils.JsonUtils;
import com.mk.taskfactory.common.Constants;
import com.mk.taskfactory.model.HotelHourClick;
import com.mk.taskfactory.model.THotel;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class BookClickServiceImpl implements BookClickService {
    private static Logger logger = LoggerFactory.getLogger(BookClickServiceImpl.class);
    private static ExecutorService pool = Executors.newFixedThreadPool(40);

    @Autowired
    private HotelHourClickService hourClickService;

    @Autowired
    private HotelDayClickService dayClickService;

    @Autowired
    private HotelMapper hotelMapper;


    public Map<String,Object>  thotelHourStatistics(HotelHourClickDto dto) {
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Cat.logEvent("thotelHourStatistics", "酒店整点点击数量统计", Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info("\n====================thotelHourStatistics method begin time{}====================\n"
                , DateUtils.format_yMdHms(new Date()));
        THotelDto hotelDto = new THotelDto();
        Integer  countTHotel=hotelMapper.countTHotel(hotelDto);
        if (countTHotel==null||countTHotel<=0){
            resultMap.put("message","Thotel count is 0");
            resultMap.put("SUCCESS", false);
            return resultMap;
        }
        int pageSize=1000;
        int pageCount=countTHotel/pageSize;
        final String nowDate=DateUtils.format_yMdHms(new Date()).substring(0,13);
        logger.info(String.format("\n====================size={}&pageSize={}&pageCount={}====================\n")
                ,countTHotel,pageSize,pageCount);

        try {
            for (int i=0;i<=pageCount;i++){
                logger.info(String.format("\n====================pageIndex={}====================\n")
                        ,i*pageSize);
                hotelDto.setPageSize(pageSize);
                hotelDto.setPageIndex(i*pageSize);
                List<THotel> hotelList = hotelMapper.queryTHotel(hotelDto);
                if (CollectionUtils.isEmpty(hotelList)){
                    logger.info(String.format("\n====================hotelList is empty====================\n"));
                    continue;
                }

                for (final THotel hotel:hotelList){
                    pool.execute(new Runnable() {
                        @Override
                        public void run() {
                            Jedis jedis = null;
                            try {
                                jedis =  RedisUtil.getJedis();
                                String  viewCount = jedis.get(String.format("%s%s", RedisCacheName.HOTEL_CLICK_NUM,hotel.getId()));
                                String  telClickCount = jedis.get(String.format("%s%s", RedisCacheName.CURRENT_OCLOCK_INTELLIGENT_PHONE_CLICK_NUM,hotel.getId()));
                                String  telCount=jedis.get(String.format("%s%s", RedisCacheName.INTELLIGENT_PHONE_ORDER_NUM,hotel.getId()));
                                String  rejectCount=jedis.get(String.format("%s%s", RedisCacheName.CURRENT_OCLOCK_INTELLIGENT_PHONE_REFUSE_ORDER_NUM,hotel.getId()));
                                HotelHourClickDto hotelHourClickDto = new HotelHourClickDto();
                                hotelHourClickDto.setHotelId(hotel.getId().longValue());
                                if (StringUtils.isNotEmpty(viewCount)){
                                    hotelHourClickDto.setViewCount(Integer.valueOf(viewCount));
                                }
                                if (StringUtils.isNotEmpty(telClickCount)){
                                    hotelHourClickDto.setTelClickCount(Integer.valueOf(telClickCount));
                                }
                                if (StringUtils.isNotEmpty(telCount)){
                                    hotelHourClickDto.setTelCount(Integer.valueOf(telCount));
                                }
                                if (StringUtils.isNotEmpty(rejectCount)){
                                    hotelHourClickDto.setRejectCount(Integer.valueOf(rejectCount));
                                }
                                hotelHourClickDto.setTime(nowDate);
                                hotelHourClickDto.setCreateTime(new Date());
                                HotelHourClickDto checkBean = new HotelHourClickDto();
                                checkBean.setHotelId(hotel.getId().longValue());
                                checkBean.setTime(nowDate);
                                checkBean.setRoomTypeIdIsNull(true);
                                checkBean= hourClickService.getByPramas(checkBean);
                                if (null==checkBean.getId()){
                                    logger.info(String.format("\n====================hotelId={} insert====================\n"));
                                    hourClickService.save(hotelHourClickDto);
                                }else {
                                    hotelHourClickDto.setId(checkBean.getId());
                                    hourClickService.updateById(hotelHourClickDto);
                                    logger.info(String.format("\n====================hotelId={} is exist====================\n"));
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                                System.out.println("oomCensusJob::error::hotelid:"+hotel.getId());
                                logger.error("RoomCensusJob::error{}{}",hotel.getId(),e.getMessage());
                            }finally {
                                if(null != jedis){
                                    jedis.close();
                                }

                            }

                        }
                    });

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultMap.put("message","执行成功");
        resultMap.put("SUCCESS", true);
        Cat.logEvent("thotelHourStatistics", "酒店整点点击数量统计", Event.SUCCESS,
                "endTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info(String.format("\n====================thotelHourStatistics method end time{}====================\n")
                , DateUtils.format_yMdHms(new Date()));
        return resultMap;
    }
    public Map<String,Object> thotelDayStatistics(HotelDayClickDto bean){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Cat.logEvent("thotelDayStatistics", "酒店日点击数量统计", Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info("\n====================thotelDayStatistics method begin time{}====================\n"
                , DateUtils.format_yMdHms(new Date()));
        THotelDto hotelDto = new THotelDto();
        Integer  countTHotel=hotelMapper.countTHotel(hotelDto);
        if (countTHotel==null||countTHotel<=0){
            resultMap.put("message","Thotel count is 0");
            resultMap.put("SUCCESS", false);
            return resultMap;
        }
        int pageSize=1000;
        int pageCount=countTHotel/pageSize;
        final String nowDate=DateUtils.format_yMdHms(new Date()).substring(0,10);
        logger.info(String.format("\n====================size={}&pageSize={}&pageCount={}====================\n")
                ,countTHotel,pageSize,pageCount);

        try {
            for (int i=0;i<=pageCount;i++){
                logger.info(String.format("\n====================pageIndex={}====================\n")
                        ,i*pageSize);
                hotelDto.setPageSize(pageSize);
                hotelDto.setPageIndex(i*pageSize);
                List<THotel> hotelList = hotelMapper.queryTHotel(hotelDto);
                if (CollectionUtils.isEmpty(hotelList)){
                    logger.info(String.format("\n====================hotelList is update====================\n"));
                    continue;
                }

                for (final THotel hotel:hotelList){
                    pool.execute(new Runnable() {
                        @Override
                        public void run() {
                            Jedis jedis = null;
                            try {
                                jedis =  RedisUtil.getJedis();
                                String  viewCount = jedis.get(String.format("%s%s", RedisCacheName.HOTEL_CLICK_NUM,hotel.getId()));
                                String  telClickCount = jedis.get(String.format("%s%s", RedisCacheName.CURRENT_OCLOCK_INTELLIGENT_PHONE_CLICK_NUM,hotel.getId()));
                                String  telCount=jedis.get(String.format("%s%s", RedisCacheName.INTELLIGENT_PHONE_ORDER_NUM,hotel.getId()));
                                String  rejectCount=jedis.get(String.format("%s%s", RedisCacheName.CURRENT_OCLOCK_INTELLIGENT_PHONE_REFUSE_ORDER_NUM,hotel.getId()));
                                HotelDayClickDto hotelDayClickDto = new HotelDayClickDto();
                                hotelDayClickDto.setHotelId(hotel.getId().longValue());
                                if (StringUtils.isNotEmpty(viewCount)){
                                    hotelDayClickDto.setViewCount(Integer.valueOf(viewCount));
                                }
                                if (StringUtils.isNotEmpty(telClickCount)){
                                    hotelDayClickDto.setTelClickCount(Integer.valueOf(telClickCount));
                                }
                                if (StringUtils.isNotEmpty(telCount)){
                                    hotelDayClickDto.setTelCount(Integer.valueOf(telCount));
                                }
                                if (StringUtils.isNotEmpty(rejectCount)){
                                    hotelDayClickDto.setRejectCount(Integer.valueOf(rejectCount));
                                }
                                hotelDayClickDto.setTime(nowDate);
                                hotelDayClickDto.setCreateTime(new Date());
                                HotelDayClickDto checkBean = new HotelDayClickDto();
                                checkBean.setHotelId(hotel.getId().longValue());
                                checkBean.setTime(nowDate);
                                checkBean.setRoomTypeIdIsNull(true);
                                checkBean= dayClickService.getByPramas(checkBean);
                                if (null==checkBean.getId()){
                                    logger.info(String.format("\n====================hotelId={} insert====================\n"));
                                    dayClickService.save(hotelDayClickDto);
                                }else {
                                    hotelDayClickDto.setId(checkBean.getId());
                                    dayClickService.updateById(hotelDayClickDto);
                                    logger.info(String.format("\n====================hotelId={} is update====================\n"));
                                }
                                resetRedis(hotel.getId(),jedis);
                            } catch (Exception e) {
                                e.printStackTrace();
                                System.out.println("oomCensusJob::error::hotelid:"+hotel.getId());
                                logger.error("RoomCensusJob::error{}{}",hotel.getId(),e.getMessage());
                            }finally {
                                if(null != jedis){
                                    jedis.close();
                                }

                            }

                        }
                    });

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultMap.put("message","酒店日点击数量统计");
        resultMap.put("SUCCESS", true);
        Cat.logEvent("thotelDayStatistics", "酒店日点击数量统计", Event.SUCCESS,
                "endTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info(String.format("\n====================thotelDayStatistics method end time{}====================\n")
                , DateUtils.format_yMdHms(new Date()));
        return resultMap;
    }
    public void resetRedis(Integer hotelId, Jedis jedis){
        if (null==hotelId||null==jedis){
            return;
        }
        /***当前整点电话预定点击率***/
        jedis.set(String.format("%s%s",RedisCacheName.CURRENT_OCLOCK_INTELLIGENT_PHONE_CLICK_RATIO,hotelId),"0");
        /***当前整点电话预定点击数***/
        jedis.set(String.format("%s%s",RedisCacheName.CURRENT_OCLOCK_INTELLIGENT_PHONE_CLICK_NUM,hotelId),"0");
        /***上一个整点电话预定点击率***/
        jedis.set(String.format("%s%s",RedisCacheName.FRONT_OCLOCK_INTELLIGENT_PHONE_CLICK_RATIO,hotelId),"0");
        /***上一个整点电话预定点击数***/
        jedis.set(String.format("%s%s",RedisCacheName.FRONT_OCLOCK_INTELLIGENT_PHONE_CLICK_NUM,hotelId),"0");
        /***昨日电话预定点击率***/
        jedis.set(String.format("%s%s",RedisCacheName.YESTERDAY_OCLOCK_INTELLIGENT_PHONE_CLICK_RATIO,hotelId),"0");
        /***昨日电话预定点击数***/
        jedis.set(String.format("%s%s",RedisCacheName.YESTERDAY_OCLOCK_INTELLIGENT_PHONE_CLICK_NUM,hotelId),"0");
        /***电话预定点击率***/
        jedis.set(String.format("%s%s",RedisCacheName.INTELLIGENT_PHONE_CLICK_RATIO,hotelId),"0");
        /***电话预定点击率***/
        jedis.set(String.format("%s%s",RedisCacheName.INTELLIGENT_PHONE_CLICK_NUM,hotelId),"0");
        /***当前整点酒店拒单率***/
        jedis.set(String.format("%s%s",RedisCacheName.CURRENT_OCLOCK_INTELLIGENT_PHONE_REFUSE_ORDER_RATIO,hotelId),"0");
        /***当前整点酒店拒单数***/
        jedis.set(String.format("%s%s",RedisCacheName.CURRENT_OCLOCK_INTELLIGENT_PHONE_REFUSE_ORDER_NUM,hotelId),"0");
        /***上一个整点酒店拒单率***/
        jedis.set(String.format("%s%s",RedisCacheName.FRONT_OCLOCK_INTELLIGENT_PHONE_REFUSE_ORDER_RATIO,hotelId),"0");
        /***上一个整点酒店拒单数***/
        jedis.set(String.format("%s%s",RedisCacheName.FRONT_OCLOCK_INTELLIGENT_PHONE_REFUSE_ORDER_NUM,hotelId),"0");
        /***昨日酒店拒单率***/
        jedis.set(String.format("%s%s",RedisCacheName.YESTERDAY_OCLOCK_INTELLIGENT_PHONE_REFUSE_ORDER_RATIO,hotelId),"0");
        /***昨日酒店拒单数***/
        jedis.set(String.format("%s%s",RedisCacheName.YESTERDAY_OCLOCK_INTELLIGENT_PHONE_REFUSE_ORDER_NUM,hotelId),"0");
        /***酒店拒单率***/
        jedis.set(String.format("%s%s",RedisCacheName.INTELLIGENT_PHONE_REFUSE_ORDER_RATIO,hotelId),"0");
        /***酒店拒单数***/
        jedis.set(String.format("%s%s",RedisCacheName.INTELLIGENT_PHONE_REFUSE_ORDER_NUM,hotelId),"0");
        /***酒店点击数量***/
        jedis.set(String.format("%s%s",RedisCacheName.HOTEL_CLICK_NUM,hotelId),"0");
        /***电话预定数量***/
        jedis.set(String.format("%s%s",RedisCacheName.INTELLIGENT_PHONE_ORDER_NUM,hotelId),"0");

        logger.info(String.format("\n====================resetRedis hotelId={}====================\n")
                , hotelId);
    }

}

