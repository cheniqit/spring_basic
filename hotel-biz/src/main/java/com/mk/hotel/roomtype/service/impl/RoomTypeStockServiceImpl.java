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
import java.util.HashMap;
import java.util.Map;
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

    public void lockRoomType(String hotelId, String roomTypeId, Date from, Date to, Integer num) {
        if (StringUtils.isBlank(hotelId) || StringUtils.isBlank(roomTypeId) || null == from || null == to || null == num) {
            return;
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //
        Jedis jedis = null;
        //
        Date[] dates = DateUtils.getStartEndDate(from, to);
        try {
            //hashName
            String availableHashName = RoomTypeStockCacheEnum.getAvailableHashName(hotelId, roomTypeId);
            String otsUsingHashName = RoomTypeStockCacheEnum.getOtsUsingHashName(hotelId, roomTypeId);
            //
            jedis = RedisUtil.getJedis();

            //
            for (Date date : dates) {
                this.lock(hotelId, roomTypeId, date, 6, 10 * 1000);

                //检查是否有房
                String strDate = format.format(date);
                String strAvailableNum = jedis.hget(availableHashName, strDate);
                if (null == strAvailableNum) {
                    strAvailableNum = "0";
                }
                //
                Long availableNum = Long.parseLong(strAvailableNum);

                if (availableNum < num) {
                    throw new MyException("-99", "-99", strDate + "无房");
                }
            }

            //锁房源
            for (Date date : dates) {
                String strDate = format.format(date);

                jedis.hincrBy(availableHashName, strDate, num * -1);
                jedis.hincrBy(otsUsingHashName, strDate, num);
            }

        } catch (MyException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }

            //解锁
            for (Date date : dates) {
                this.unlock(hotelId, roomTypeId, date);
            }
        }

    }

    public void unlockRoomType(String hotelId, String roomTypeId, Date from, Date to, Integer num) {
        if (StringUtils.isBlank(hotelId) || StringUtils.isBlank(roomTypeId) || null == from || null == to || null == num) {
            return;
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        //
        Jedis jedis = null;
        //
        Date[] dates = DateUtils.getStartEndDate(from, to);
        try {
            //
            jedis = RedisUtil.getJedis();
            //hashName
            String availableHashName = RoomTypeStockCacheEnum.getAvailableHashName(hotelId, roomTypeId);
            String otsUsingHashName = RoomTypeStockCacheEnum.getOtsUsingHashName(hotelId, roomTypeId);

            //
            for (Date date : dates) {
                this.lock(hotelId, roomTypeId, date, 6, 10 * 1000);

                //检查是否有锁房
                String strDate = format.format(date);
                String strOtsUsingNum = jedis.hget(otsUsingHashName, strDate);
                if (null == strOtsUsingNum) {
                    strOtsUsingNum = "0";
                }

                //
                Long otsUsingNum = Long.parseLong(strOtsUsingNum);

                if (otsUsingNum < num) {
                    throw new MyException("-99", "-99", strDate + "无锁房");
                }

            }

            for (Date date : dates) {
                String strDate = format.format(date);

                jedis.hincrBy(availableHashName, strDate, num);
                jedis.hincrBy(otsUsingHashName, strDate, num * -1);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }

            //解锁
            for (Date date : dates) {
                this.unlock(hotelId, roomTypeId, date);
            }
        }

    }

    @Override
    public void lockPms(String hotelId, String roomTypeId, Date from, Date to, Integer num) {
        if (StringUtils.isBlank(hotelId) || StringUtils.isBlank(roomTypeId) || null == from || null == to || null == num) {
            return;
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //
        Jedis jedis = null;
        //
        Date[] dates = DateUtils.getStartEndDate(from, to);
        try {
            //
            jedis = RedisUtil.getJedis();
            //hashName
            String otsUsingHashName = RoomTypeStockCacheEnum.getOtsUsingHashName(hotelId, roomTypeId);
            String pmsUsingHashName = RoomTypeStockCacheEnum.getPmsUsingHashName(hotelId, roomTypeId);

            for (Date date : dates) {
                String lockKeyName = RoomTypeStockCacheEnum.getLockKeyName(hotelId, roomTypeId, date);

                String lockKey = jedis.get(lockKeyName);

                if (null == lockKey) {
                    throw new MyException("-99","-99","未获得操作锁");
                }
            }


            for (Date date : dates) {
                String strDate = format.format(date);

                jedis.hincrBy(otsUsingHashName, strDate, num * -1);
                jedis.hincrBy(pmsUsingHashName, strDate, num);
            }
        } catch (MyException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    @Override
    public void unlockPms(String hotelId, String roomTypeId, Date from, Date to, Integer num) {

    }

    /**
     * @param hotelId
     * @param roomTypeId
     * @param day
     * @param totalNum
     * @param num
     */
    public void updateStock(String hotelId, String roomTypeId, Date day, Integer totalNum, Integer num) {
        if (StringUtils.isBlank(hotelId) || StringUtils.isBlank(roomTypeId) || null == day || null == num || null == totalNum) {
            return;
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

            //hashName
            String availableHashName = RoomTypeStockCacheEnum.getAvailableHashName(hotelId, roomTypeId);
            String usingHashName = RoomTypeStockCacheEnum.getOtsUsingHashName(hotelId, roomTypeId);

            //
            String strAvailableNum = jedis.hget(availableHashName, strDate);
            if (null == strAvailableNum) {
                strAvailableNum = "0";
            }
            String strUsingNum = jedis.hget(usingHashName, strDate);
            if (null == strUsingNum) {
                strUsingNum = "0";
            }

            //
            Long availableNum = Long.parseLong(strAvailableNum);
            Long usingNum = Long.parseLong(strUsingNum);

            //计算 可用房量
            Long redisTotal = availableNum + usingNum;

            if (redisTotal > totalNum) {
                Long diff = redisTotal - totalNum;
                availableNum = num - diff;
            } else {
                availableNum = num.longValue();
            }

            //更新
            jedis.hset(availableHashName, strDate, String.valueOf(availableNum));

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
