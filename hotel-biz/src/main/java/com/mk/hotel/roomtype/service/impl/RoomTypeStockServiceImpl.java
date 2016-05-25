package com.mk.hotel.roomtype.service.impl;

import com.dianping.cat.Cat;
import com.mk.framework.DateUtils;
import com.mk.framework.excepiton.MyException;
import com.mk.framework.proxy.http.RedisUtil;
import com.mk.hotel.roomtype.RoomTypeStockService;
import com.mk.hotel.roomtype.enums.RoomTypeStockCacheEnum;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by huangjie on 16/5/11.
 */
@Service
public class RoomTypeStockServiceImpl implements RoomTypeStockService {

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

    public void updateRedisStock(String hotelId, String roomTypeId, Date day, Integer totalNum, Integer promoNum) {
        if (StringUtils.isBlank(hotelId) || StringUtils.isBlank(roomTypeId) || null == day || null == totalNum || null == promoNum) {
            throw new MyException("-99", "-99", "更新库存时,hotelId,roomTypeIdk,day,totalNum,promoNum必填");
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = format.format(day);

        //
        this.lock(hotelId, roomTypeId, day, 6, 10 * 1000);

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

        //
        this.unlock(hotelId, roomTypeId, day);
    }
}
