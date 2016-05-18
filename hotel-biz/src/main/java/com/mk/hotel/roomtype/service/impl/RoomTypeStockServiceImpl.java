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

//    @Override
//    public void lock(String hotelId, String roomTypeId, Date from, Date to, int lockTime, long maxWaitTimeOut) {
//        if (StringUtils.isEmpty(hotelId) || StringUtils.isEmpty(roomTypeId) || null == from || null == to) {
//            throw new MyException("-99", "-99", "参数不可为空");
//        }
//        Date[] dates = DateUtils.getStartEndDate(from, to);
//
//        for (Date date : dates) {
//            this.lock(hotelId, roomTypeId, date, lockTime, maxWaitTimeOut);
//        }
//    }


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
//
//    @Override
//    public void unlock(String hotelId, String roomTypeId, Date from, Date to) {
//        if (StringUtils.isEmpty(hotelId) || StringUtils.isEmpty(roomTypeId) || null == from || null == to) {
//            throw new MyException("-99", "-99", "参数不可为空");
//        }
//        Date[] dates = DateUtils.getStartEndDate(from, to);
//
//        for (Date date : dates) {
//            this.unlock(hotelId, roomTypeId, date);
//        }
//    }
//
//    public void lockRoomType(String hotelId, String roomTypeId, Date from, Date to, Integer num) {
//        if (StringUtils.isBlank(hotelId) || StringUtils.isBlank(roomTypeId) || null == from || null == to || null == num) {
//            return;
//        }
//
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        //
//        Jedis jedis = null;
//        //
//        Date[] dates = DateUtils.getStartEndDate(from, to);
//        try {
//            //hashName
//            String availableHashName = RoomTypeStockCacheEnum.getAvailableHashName(hotelId, roomTypeId);
//            String otsUsingHashName = RoomTypeStockCacheEnum.getOtsUsingHashName(hotelId, roomTypeId);
//            //
//            jedis = RedisUtil.getJedis();
//
//            //
//            for (Date date : dates) {
//                this.lock(hotelId, roomTypeId, date, 6, 10 * 1000);
//
//                //检查是否有房
//                String strDate = format.format(date);
//                String strAvailableNum = jedis.hget(availableHashName, strDate);
//                if (null == strAvailableNum) {
//                    strAvailableNum = "0";
//                }
//                //
//                Long availableNum = Long.parseLong(strAvailableNum);
//
//                if (availableNum < num) {
//                    throw new MyException("-99", "-99", strDate + "无房");
//                }
//            }
//
//            //锁房源
//            for (Date date : dates) {
//                String strDate = format.format(date);
//
//                jedis.hincrBy(availableHashName, strDate, num * -1);
//                jedis.hincrBy(otsUsingHashName, strDate, num);
//            }
//
//        } catch (MyException e) {
//            throw e;
//        } catch (Exception e) {
//            e.printStackTrace();
//            Cat.logError(e);
//        } finally {
//            if (null != jedis) {
//                jedis.close();
//            }
//
//            //解锁
//            for (Date date : dates) {
//                this.unlock(hotelId, roomTypeId, date);
//            }
//        }
//
//    }
//
//    public void unlockRoomType(String hotelId, String roomTypeId, Date from, Date to, Integer num) {
//        if (StringUtils.isBlank(hotelId) || StringUtils.isBlank(roomTypeId) || null == from || null == to || null == num) {
//            return;
//        }
//
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//
//        //
//        Jedis jedis = null;
//        //
//        Date[] dates = DateUtils.getStartEndDate(from, to);
//        try {
//            //
//            jedis = RedisUtil.getJedis();
//            //hashName
//            String availableHashName = RoomTypeStockCacheEnum.getAvailableHashName(hotelId, roomTypeId);
//            String otsUsingHashName = RoomTypeStockCacheEnum.getOtsUsingHashName(hotelId, roomTypeId);
//
//            //
//            for (Date date : dates) {
//                this.lock(hotelId, roomTypeId, date, 6, 10 * 1000);
//
//                //检查是否有锁房
//                String strDate = format.format(date);
//                String strOtsUsingNum = jedis.hget(otsUsingHashName, strDate);
//                if (null == strOtsUsingNum) {
//                    strOtsUsingNum = "0";
//                }
//
//                //
//                Long otsUsingNum = Long.parseLong(strOtsUsingNum);
//
//                if (otsUsingNum < num) {
//                    throw new MyException("-99", "-99", strDate + "无锁房");
//                }
//
//            }
//
//            for (Date date : dates) {
//                String strDate = format.format(date);
//
//                jedis.hincrBy(availableHashName, strDate, num);
//                jedis.hincrBy(otsUsingHashName, strDate, num * -1);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            Cat.logError(e);
//        } finally {
//            if (null != jedis) {
//                jedis.close();
//            }
//
//            //解锁
//            for (Date date : dates) {
//                this.unlock(hotelId, roomTypeId, date);
//            }
//        }
//
//    }
//
//    @Override
//    public void lockPms(String hotelId, String roomTypeId, Date from, Date to, Integer num) {
//        if (StringUtils.isBlank(hotelId) || StringUtils.isBlank(roomTypeId) || null == from || null == to || null == num) {
//            return;
//        }
//
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        //
//        Jedis jedis = null;
//        //
//        Date[] dates = DateUtils.getStartEndDate(from, to);
//        try {
//            //
//            jedis = RedisUtil.getJedis();
//            //hashName
//            String otsUsingHashName = RoomTypeStockCacheEnum.getOtsUsingHashName(hotelId, roomTypeId);
//            String pmsUsingHashName = RoomTypeStockCacheEnum.getPmsUsingHashName(hotelId, roomTypeId);
//
//            //检查是否已锁, 及是否有房
//            this.checkHaveRoom(hotelId, roomTypeId, dates, otsUsingHashName, num);
//
//            //锁房
//            for (Date date : dates) {
//                String strDate = format.format(date);
//
//                jedis.hincrBy(otsUsingHashName, strDate, num * -1);
//                jedis.hincrBy(pmsUsingHashName, strDate, num);
//            }
//        } catch (MyException e) {
//            throw e;
//        } catch (Exception e) {
//            e.printStackTrace();
//            Cat.logError(e);
//        } finally {
//            if (null != jedis) {
//                jedis.close();
//            }
//        }
//    }
//
//    @Override
//    public void unlockPms(String hotelId, String roomTypeId, Date from, Date to, Integer num) {
//        if (StringUtils.isBlank(hotelId) || StringUtils.isBlank(roomTypeId) || null == from || null == to || null == num) {
//            return;
//        }
//
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        //
//        Jedis jedis = null;
//        //
//        Date[] dates = DateUtils.getStartEndDate(from, to);
//        try {
//            //
//            jedis = RedisUtil.getJedis();
//            //hashName
//            String otsUsingHashName = RoomTypeStockCacheEnum.getOtsUsingHashName(hotelId, roomTypeId);
//            String pmsUsingHashName = RoomTypeStockCacheEnum.getPmsUsingHashName(hotelId, roomTypeId);
//
//
//            //检查是否已锁, 及是否有房
//            this.checkHaveRoom(hotelId, roomTypeId, dates, pmsUsingHashName, num);
//
//            //解锁
//            for (Date date : dates) {
//                String strDate = format.format(date);
//
//                jedis.hincrBy(otsUsingHashName, strDate, num);
//                jedis.hincrBy(pmsUsingHashName, strDate, num * -1);
//            }
//        } catch (MyException e) {
//            throw e;
//        } catch (Exception e) {
//            e.printStackTrace();
//            Cat.logError(e);
//        } finally {
//            if (null != jedis) {
//                jedis.close();
//            }
//        }
//    }
//
//    @Override
//    public void cancelLockPms(String hotelId, String roomTypeId, Date from, Date to, Integer num) {
//        if (StringUtils.isBlank(hotelId) || StringUtils.isBlank(roomTypeId) || null == from || null == to || null == num) {
//            return;
//        }
//
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        //
//        Jedis jedis = null;
//        //
//        Date[] dates = DateUtils.getStartEndDate(from, to);
//        try {
//            //
//            jedis = RedisUtil.getJedis();
//            //hashName
//            String availableHashName = RoomTypeStockCacheEnum.getAvailableHashName(hotelId, roomTypeId);
//            String pmsUsingHashName = RoomTypeStockCacheEnum.getPmsUsingHashName(hotelId, roomTypeId);
//
//            //检查是否已锁, 及是否有房
//            this.checkHaveRoom(hotelId, roomTypeId, dates, pmsUsingHashName, num);
//
//            //解锁
//            for (Date date : dates) {
//                String strDate = format.format(date);
//
//                jedis.hincrBy(availableHashName, strDate, num);
//                jedis.hincrBy(pmsUsingHashName, strDate, num * -1);
//            }
//        } catch (MyException e) {
//            throw e;
//        } catch (Exception e) {
//            e.printStackTrace();
//            Cat.logError(e);
//        } finally {
//            if (null != jedis) {
//                jedis.close();
//            }
//        }
//    }

    public void updateRedisStock(String hotelId, String roomTypeId, Date day, Integer num) {
        if (StringUtils.isBlank(hotelId) || StringUtils.isBlank(roomTypeId) || null == day || null == num) {
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

//            //hashName
//            String availableHashName = RoomTypeStockCacheEnum.getAvailableHashName(hotelId, roomTypeId);
//            String otsUsingHashName = RoomTypeStockCacheEnum.getOtsUsingHashName(hotelId, roomTypeId);
//
//            //
//            String strOtsUsingNum = jedis.hget(otsUsingHashName, strDate);
//            if (null == strOtsUsingNum) {
//                strOtsUsingNum = "0";
//            }
//
//            //ots锁数量
//            Long otsUsingNum = Long.parseLong(strOtsUsingNum);
//
//            //计算 可用房量
//            Long availableNum = num - otsUsingNum;
//
//            //更新
//            jedis.hset(availableHashName, strDate, String.valueOf(availableNum));

            String availableHashName = RoomTypeStockCacheEnum.getAvailableHashName(roomTypeId);
            jedis.hset(availableHashName, strDate, String.valueOf(num));
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

//    /**
//     * 检查redis中是否有房
//     * @param hotelId 酒店id
//     * @param roomTypeId 房型id
//     * @param dates 检查日期队列
//     * @param checkKey
//     * @param checkNum
//     */
//    private void checkHaveRoom(String hotelId, String roomTypeId, Date[] dates, String checkKey, Integer checkNum) {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        //
//        Jedis jedis = null;
//        //
//        try {
//            //
//            jedis = RedisUtil.getJedis();
//            for (Date date : dates) {
//                String lockKeyName = RoomTypeStockCacheEnum.getLockKeyName(hotelId, roomTypeId, date);
//
//                String lockKey = jedis.get(lockKeyName);
//
//                if (null == lockKey) {
//                    throw new MyException("-99", "-99", "未获得操作锁");
//                }
//
//                //检查是否有房
//                String strDate = format.format(date);
//                String strCacheNum = jedis.hget(checkKey, strDate);
//                if (null == strCacheNum) {
//                    strCacheNum = "0";
//                }
//                //
//                Long cacheNum = Long.parseLong(strCacheNum);
//
//                if (cacheNum < checkNum) {
//                    throw new MyException("-99", "-99", strDate + "无锁房");
//                }
//
//            }
//        } finally {
//            if (null != jedis) {
//                jedis.close();
//            }
//        }
//    }


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
            String availableHashName = RoomTypeStockCacheEnum.getAvailableHashName(roomTypeId);
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
            String availableHashName = RoomTypeStockCacheEnum.getAvailableHashName(roomTypeId);
            //
            for (Date date : dates) {
                this.lock(hotelId, roomTypeId, date, 6, 10 * 1000);
            }

            //释放房源
            for (Date date : dates) {
                String strDate = format.format(date);

                jedis.hincrBy(availableHashName, strDate, num);
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
}
