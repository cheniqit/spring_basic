package com.mk.hotel.roomtype.service.impl;

import com.dianping.cat.Cat;
import com.mk.framework.DateUtils;
import com.mk.framework.excepiton.MyException;
import com.mk.framework.proxy.http.RedisUtil;
import com.mk.hotel.hotelinfo.mapper.HotelMapper;
import com.mk.hotel.hotelinfo.model.Hotel;
import com.mk.hotel.remote.pms.hotelstock.HotelStockRemoteService;
import com.mk.hotel.remote.pms.hotelstock.json.QueryStockRequest;
import com.mk.hotel.remote.pms.hotelstock.json.QueryStockResponse;
import com.mk.hotel.roomtype.RoomTypeService;
import com.mk.hotel.roomtype.RoomTypeStockService;
import com.mk.hotel.roomtype.dto.RoomTypeDto;
import com.mk.hotel.roomtype.enums.RoomTypeStockCacheEnum;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by huangjie on 16/5/11.
 */
@Service
public class RoomTypeStockServiceImpl implements RoomTypeStockService {

    @Autowired
    private HotelStockRemoteService hotelStockRemoteService;
    @Autowired
    private HotelMapper hotelMapper;
    @Autowired
    private RoomTypeService roomTypeService;

    public void lock(String hotelId, String roomTypeId, Date day, int lockTime, long maxWaitTimeOut) {

        Jedis jedis = null;
        try {
            jedis = RedisUtil.getJedis();

            //
            String lockKeyName = RoomTypeStockCacheEnum.getLockKeyName(hotelId, roomTypeId, day);

            long start = System.currentTimeMillis();

            String h = jedis.set(lockKeyName, "1", "NX", "EX", lockTime);

            while (null == h) {
                TimeUnit.MILLISECONDS.sleep(10);
                h = jedis.set(lockKeyName, "1", "NX", "EX", lockTime);

                //
                long now = System.currentTimeMillis();
                long diff = now - start;
                if (diff > maxWaitTimeOut) {
                    throw new MyException("-99", "-99", "锁定超时");
                }
            }

            return;

        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }

        }

        throw new MyException("-99", "-99", "锁定超时");
    }

    public void unlock(String hotelId, String roomTypeId, Date day) {
        Jedis jedis = null;
        try {
            jedis = RedisUtil.getJedis();

            //
            String lockKeyName = RoomTypeStockCacheEnum.getLockKeyName(hotelId, roomTypeId, day);
            jedis.del(lockKeyName);

        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public void updatePromoRedisStock(String hotelId, String roomTypeId, Integer promoNum){
        QueryStockRequest queryStockRequest = new QueryStockRequest();
        Hotel hotel = hotelMapper.selectByPrimaryKey(Long.valueOf(hotelId));
        if(hotel == null){
            return;
        }
        queryStockRequest.setHotelid(hotel.getFangId().toString());
        queryStockRequest.setRoomtypeid(roomTypeId);
        queryStockRequest.setBegintime(DateUtils.formatDateTime(new Date(), DateUtils.FORMAT_DATE));
        queryStockRequest.setEndtime(DateUtils.formatDate(DateUtils.addDays(new Date(), 30)));
        QueryStockResponse response = hotelStockRemoteService.queryStock(queryStockRequest);
        if(response == null || response.getData() == null){
            return;
        }
        for(QueryStockResponse.Roomtypestocks roomtypestock :  response.getData().getRoomtypestocks()) {
            RoomTypeDto roomTypeDto = roomTypeService.selectByFangId(Long.valueOf(roomtypestock.getRoomtypeid()));
            if (roomTypeDto == null) {
                return;
            }
            for (QueryStockResponse.Stockinfos stockInfo : roomtypestock.getStockinfos()) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = format.parse(stockInfo.getDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                updateRedisStock(hotelId, roomTypeId, date, Integer.valueOf(stockInfo.getNum()), promoNum);
            }
        }

    }

    public void updateRedisStock(String hotelId, String roomTypeId, Date day, Integer totalNum, Integer promoNum) {
        if (StringUtils.isBlank(hotelId) || StringUtils.isBlank(roomTypeId) || null == day || null == totalNum || null == promoNum) {
            throw new MyException("-99", "-99", "更新库存时,hotelId,roomTypeIdk,day,totalNum,promoNum必填");
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = format.format(day);

        //
        Jedis jedis = null;
        try {
            //
            jedis = RedisUtil.getJedis();

            String availableHashName = RoomTypeStockCacheEnum.getAvailableHashName(roomTypeId);
            String promoHashName = RoomTypeStockCacheEnum.getPromoHashName(roomTypeId);

            if (totalNum.compareTo(promoNum) <= 0) {
                //
                jedis.hset(availableHashName, strDate, "0");
                jedis.hset(promoHashName, strDate, String.valueOf(totalNum));
            } else if (totalNum.compareTo(promoNum) > 0) {
                //
                jedis.hset(availableHashName, strDate, String.valueOf(totalNum - promoNum));
                jedis.hset(promoHashName, strDate, String.valueOf(promoNum));
            }

        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }
}
