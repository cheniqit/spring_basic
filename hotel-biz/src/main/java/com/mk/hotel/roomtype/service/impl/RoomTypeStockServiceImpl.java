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
import com.mk.hotel.roomtype.RoomTypeFullStockLogService;
import com.mk.hotel.roomtype.RoomTypeService;
import com.mk.hotel.roomtype.RoomTypeStockService;
import com.mk.hotel.roomtype.bean.RoomTypeStockBean;
import com.mk.hotel.roomtype.dto.RoomTypeDto;
import com.mk.hotel.roomtype.dto.RoomTypeFullStockLogDto;
import com.mk.hotel.roomtype.dto.StockInfoDto;
import com.mk.hotel.roomtype.enums.RoomTypeStockCacheEnum;
import com.mk.hotel.roomtype.mapper.RoomTypeStockMapper;
import com.mk.hotel.roomtype.model.RoomTypeStock;
import com.mk.hotel.roomtype.model.RoomTypeStockExample;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    @Autowired
    private RoomTypeStockMapper roomTypeStockMapper;
    @Autowired
    private RoomTypeFullStockLogService roomTypeFullStockLogService;

    private Integer getValueByRedisKeyName(Jedis jedis, String key, String field) {

        boolean isNeedCloseJedis = false;
        if (null == jedis) {
            jedis = RedisUtil.getJedis();
            isNeedCloseJedis = true;
        }
        String strNum = jedis.hget(key, field);

        try {
            return Integer.parseInt(strNum);
        } catch (Exception e) {
            Cat.logError(e);
            return null;
        } finally {
            if (isNeedCloseJedis) {
                if (null != jedis) {
                    jedis.close();
                }
            }
        }
    }
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

    public void updatePromoRedisStock(Long hotelId, Long roomTypeId, Integer promoNum) {
        Hotel hotel = hotelMapper.selectByPrimaryKey(hotelId);
        if (null == hotel) {
            Date date = new Date();
            for (int i = 0; i <= 30; i++) {
                Date addDate = DateUtils.addDays(date, i);
                updateRedisStock(hotelId, roomTypeId, addDate, 100, promoNum);
            }
            return;
        }

        RoomTypeDto roomTypeDto = this.roomTypeService.selectById(roomTypeId);
        if (null == roomTypeDto) {
            throw new MyException("-99", "-99", "房型不存在");
        }

        //
        QueryStockRequest queryStockRequest = new QueryStockRequest();
        queryStockRequest.setHotelid(hotel.getFangId().toString());
        queryStockRequest.setRoomtypeid(roomTypeDto.getFangId().toString());
        queryStockRequest.setBegintime(DateUtils.formatDateTime(new Date(), DateUtils.FORMAT_DATE));
        queryStockRequest.setEndtime(DateUtils.formatDate(DateUtils.addDays(new Date(), 30)));
        QueryStockResponse response = hotelStockRemoteService.queryStock(queryStockRequest);
        if (response == null || response.getData() == null) {
            throw new MyException("-99", "-99", "房爸爸接口调用错误");
        }

        //
        for (QueryStockResponse.Roomtypestocks roomtypestock : response.getData().getRoomtypestocks()) {
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

    public String updateRedisStock(Long hotelId, Long roomTypeId, Date day, Integer allAvailableNum, Integer totalPromoNum) {
        if (null == hotelId || null == roomTypeId || null == day || null == allAvailableNum || null == totalPromoNum) {
            throw new MyException("-99", "-99", "更新库存时,hotelId,roomTypeIdk,day,availableNum,promoNum必填");
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = format.format(day);

        //
        Jedis jedis = null;
        try {
            //
            jedis = RedisUtil.getJedis();

            //
            String totalPromoHashName = RoomTypeStockCacheEnum.getTotalPromoHashName(String.valueOf(roomTypeId));
            String availableHashName = RoomTypeStockCacheEnum.getAvailableHashName(String.valueOf(roomTypeId));
            String promoHashName = RoomTypeStockCacheEnum.getPromoHashName(String.valueOf(roomTypeId));

            //
            Integer originalTotalPromoNum = getValueByRedisKeyName(jedis, totalPromoHashName, strDate);
            Integer originalAvailableNum = getValueByRedisKeyName(jedis, availableHashName, strDate);
            Integer originalPromoNum = getValueByRedisKeyName(jedis, promoHashName, strDate);

            //计算 availableNum promoNum
            Integer availableNum = null;
            Integer promoNum = null;
            if (null != originalTotalPromoNum && null != originalAvailableNum && null != originalPromoNum) {
                //更新 (都有值)

                //原已售出特价房数量 = 原计划特价房量 - 原特价房可售数量
                Integer originalTotalPromoSale = originalTotalPromoNum - originalPromoNum;

                //计算 新特价房可售数量 = 计划特价房量 - 原已售出特价房数量
                promoNum = totalPromoNum - originalTotalPromoSale;
            } else {
                //其他情况 新增, 直接覆盖
                promoNum = totalPromoNum;
            }

            //计算 新普通房可售数量
            if (promoNum < 0) {
                //若特价房 已超卖, 普通可售房 = 总可售数量 - 计划特价房量 - 特价房超卖数量
                availableNum = allAvailableNum - totalPromoNum + promoNum;
            } else {
                //若特价房 未超卖, 普通可售房 = 总可售数量 - 计划特价房量
                availableNum = allAvailableNum - totalPromoNum;
            }

            //
            jedis.hset(totalPromoHashName, strDate, String.valueOf(totalPromoNum));
            jedis.hset(availableHashName, strDate, String.valueOf(availableNum));
            jedis.hset(promoHashName, strDate, String.valueOf(promoNum));

            //
            StringBuilder result = new StringBuilder();
            if (promoNum < 0) {
                RoomTypeDto dto = this.roomTypeService.selectById(roomTypeId);
                result.append(dto.getName()).append("房型,").append(strDate).append(" 特价房超卖:").append(promoNum * -1).append("间\n");
            }

            if (availableNum < 0) {
                RoomTypeDto dto = this.roomTypeService.selectById(roomTypeId);
                result.append(dto.getName()).append("房型,").append(strDate).append(" 普通房超卖:").append(availableNum * -1).append("间\n");
            }
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }

        return "库存更新失败,请联系管理员3";
    }

    private RoomTypeStockBean calcStockByTotal(Long roomTypeId, String strDate, Integer totalNum, Integer totalPromoNum, Jedis jedis) {
        if (null == roomTypeId || StringUtils.isBlank(strDate) || null == totalNum || null == totalPromoNum) {
            throw new MyException("-99", "-99", "更新库存时,roomTypeIdk,day,totalNum,promoNum必填");
        }

        if (totalNum.compareTo(totalPromoNum) < 0) {
            //totalNum 大于或等于 promoNum时, 认为计划特价房量 = 签约房量
            totalPromoNum = totalNum;
        }

        //
        boolean isNeedCloseJedis = false;
        try {
            if (null == jedis) {
                isNeedCloseJedis = true;
                jedis = RedisUtil.getJedis();
            }

            //key
            String totalHashName = RoomTypeStockCacheEnum.getTotalHashName(String.valueOf(roomTypeId));
            String totalPromoHashName = RoomTypeStockCacheEnum.getTotalPromoHashName(String.valueOf(roomTypeId));

            String availableHashName = RoomTypeStockCacheEnum.getAvailableHashName(String.valueOf(roomTypeId));
            String promoHashName = RoomTypeStockCacheEnum.getPromoHashName(String.valueOf(roomTypeId));

            //
            Integer originalTotalNum = getValueByRedisKeyName(jedis, totalHashName, strDate);
            Integer originalTotalPromoNum = getValueByRedisKeyName(jedis, totalPromoHashName, strDate);
            Integer originalAvailableNum = getValueByRedisKeyName(jedis, availableHashName, strDate);
            Integer originalPromoNum = getValueByRedisKeyName(jedis, promoHashName, strDate);

            //计算 availableNum promoNum
            Integer availableNum = null;
            Integer promoNum = null;

            if (null != originalTotalNum && null != originalTotalPromoNum && null != originalAvailableNum && null != originalPromoNum) {
                //更新 (都有值), 以负数记录超卖情况

                //原已售出特价房数量 = 原计划特价房量 - 原特价房可售数量
                Integer originalTotalPromoSale = originalTotalPromoNum - originalPromoNum;

                //原已售出普通房数量 = 原签约普通房量 - 原计划特价房量 - 原签约普通房可售数量
                Integer originalTotalSale = originalTotalNum - originalTotalPromoNum - originalAvailableNum;

                //若有超卖情况, 原已售出普通房数量 = 原已售出普通房数量 - 超卖数量
                if (originalPromoNum < 0) {
                    originalTotalSale = originalTotalSale + originalPromoNum;
                }

                //可售普通房 = 签约总量 - 计划特价房量 - 原已售出普通房量
                availableNum = totalNum - totalPromoNum - originalTotalSale;
                //可售特价房量 = 计划特价房量 - 原已售出特价房数量;
                promoNum = totalPromoNum - originalTotalPromoSale;

            } else if (null == originalTotalNum && null != originalTotalPromoNum && null != originalAvailableNum && null != originalPromoNum) {
                //错误情况, originalTotalNum 为空

                //原已售出特价房数量 = 原计划特价房量 - 原特价数量
                Integer originalTotalPromoSale = originalTotalPromoNum - originalPromoNum;

                //计算 新特价房可售数量 = 计划特价房量 - 原已售出特价房数量
                promoNum = totalPromoNum - originalTotalPromoSale;

                //计算 新普通房可售数量 = 签约总量 - 计划特价房量
                availableNum = totalNum - totalPromoNum;

            } else {
                //其他情况 新增, 直接覆盖
                promoNum = totalPromoNum;

                //计算 新普通房可售数量 = 签约总量 - 计划特价房量
                availableNum = totalNum - totalPromoNum;
            }


            //计算 新普通房可售数量
            if (promoNum < 0) {
                //若特价房 已超卖, 普通可售房 = 普通房可售数量 - 特价房超卖数量
                availableNum = availableNum + promoNum;
            } else {
                //若特价房 未超卖, 普通可售房 = 普通可售房
                //不变
            }

            //
            RoomTypeStockBean stockBean = new RoomTypeStockBean();
            stockBean.setTotalNum(totalNum);
            stockBean.setTotalPromoNum(totalPromoNum);
            stockBean.setAvailableNum(availableNum);
            stockBean.setPromoNum(promoNum);
            return stockBean;
        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        } finally {
            if (isNeedCloseJedis && null != jedis) {
                jedis.close();
            }
        }

        return null;
    }

    public String updateRedisStockByTotal(Long hotelId, Long roomTypeId, Date day, Integer totalNum, Integer totalPromoNum) {
        if (null == hotelId || null == roomTypeId || null == day || null == totalNum || null == totalPromoNum) {
            throw new MyException("-99", "-99", "更新库存时,hotelId,roomTypeIdk,day,totalNum,promoNum必填");
        }

        if (totalNum.compareTo(totalPromoNum) < 0) {
            //totalNum 大于或等于 promoNum时, 认为计划特价房量 = 签约房量
            totalPromoNum = totalNum;
        }

        //
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = format.format(day);

        //
        Jedis jedis = null;
        try {
            //
            jedis = RedisUtil.getJedis();

            //key
            String totalHashName = RoomTypeStockCacheEnum.getTotalHashName(String.valueOf(roomTypeId));
            String totalPromoHashName = RoomTypeStockCacheEnum.getTotalPromoHashName(String.valueOf(roomTypeId));

            String availableHashName = RoomTypeStockCacheEnum.getAvailableHashName(String.valueOf(roomTypeId));
            String promoHashName = RoomTypeStockCacheEnum.getPromoHashName(String.valueOf(roomTypeId));

            //
            RoomTypeStockBean stockBean = this.calcStockByTotal(roomTypeId, strDate, totalNum, totalPromoNum, jedis);
            if (null == stockBean) {
                return "库存更新失败,请联系管理员1";
            }

            //
            Integer availableNum = stockBean.getAvailableNum();
            Integer promoNum = stockBean.getPromoNum();

            //
            jedis.hset(totalHashName, strDate, String.valueOf(totalNum));
            jedis.hset(totalPromoHashName, strDate, String.valueOf(totalPromoNum));
            jedis.hset(availableHashName, strDate, String.valueOf(availableNum));
            jedis.hset(promoHashName, strDate, String.valueOf(promoNum));

            //
            StringBuilder result = new StringBuilder();
            if (promoNum < 0) {
                RoomTypeDto dto = this.roomTypeService.selectById(roomTypeId);
                result.append(dto.getName()).append("房型,").append(strDate).append(" 特价房超卖:").append(promoNum * -1).append("间\n");
            }

            if (availableNum < 0) {
                RoomTypeDto dto = this.roomTypeService.selectById(roomTypeId);
                result.append(dto.getName()).append("房型,").append(strDate).append(" 普通房超卖:").append(availableNum * -1).append("间\n");
            }
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }

        return "库存更新失败,请联系管理员2";
    }

    public void fullStock(Long hotelId, Long roomTypeId, Date from, Date to) {

        Date[] dates = DateUtils.getStartEndDate(from, to);

        try {
            //lock
            for (Date day : dates) {
                this.lock(String.valueOf(hotelId), String.valueOf(roomTypeId), day, RoomTypeStockService.LOCK_TIIME,
                        RoomTypeStockService.MAX_WAIT_TIME_OUT);
            }
            //
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            //key
            String totalHashName = RoomTypeStockCacheEnum.getTotalHashName(String.valueOf(roomTypeId));
            String totalPromoHashName = RoomTypeStockCacheEnum.getTotalPromoHashName(String.valueOf(roomTypeId));

            String availableHashName = RoomTypeStockCacheEnum.getAvailableHashName(String.valueOf(roomTypeId));
            String promoHashName = RoomTypeStockCacheEnum.getPromoHashName(String.valueOf(roomTypeId));

            //full
            for (Date day : dates) {
                //
                String strDate = format.format(day);
                //
                Integer originalTotalNum = getValueByRedisKeyName(null, totalHashName, strDate);
                Integer originalTotalPromoNum = getValueByRedisKeyName(null, totalPromoHashName, strDate);
                Integer originalAvailableNum = getValueByRedisKeyName(null, availableHashName, strDate);
                Integer originalPromoNum = getValueByRedisKeyName(null, promoHashName, strDate);

                //savelog
                RoomTypeFullStockLogDto logDto = new RoomTypeFullStockLogDto();
                logDto.setRoomTypeId(roomTypeId);
                logDto.setDay(day);

                if (null != originalTotalNum) {
                    logDto.setTotalNumber(originalTotalNum.longValue());
                }

                if (null != originalTotalPromoNum) {
                    logDto.setTotalPromoNumber(originalTotalPromoNum.longValue());
                }

                if (null != originalAvailableNum) {
                    logDto.setNormalNumber(originalAvailableNum.longValue());
                }

                if (null != originalPromoNum) {
                    logDto.setPromoNumber(originalPromoNum.longValue());
                }

                logDto.setIsValid("T");

                roomTypeFullStockLogService.saveLog(logDto);

                //
                this.updateRedisStock(
                        hotelId,
                        roomTypeId,
                        day,
                        0,
                        0);
            }
        } finally {
            //unlock
            for (Date day : dates) {
                this.unlock(String.valueOf(hotelId), String.valueOf(roomTypeId), day);
            }
        }
    }

    public List<RoomTypeStock> queryRoomStockByRoomTypeId(Long roomTypeId, Date fromDate, Date toDate) {
        RoomTypeStockExample example = new RoomTypeStockExample();
        example.createCriteria().andRoomTypeIdEqualTo(roomTypeId).andDayBetween(fromDate, toDate);
        return roomTypeStockMapper.selectByExample(example);
    }

    public List<StockInfoDto> getStock(Long roomTypeId, Date begin, Date end){
        List<RoomTypeStock> roomTypeStockList = queryRoomStockByRoomTypeId(roomTypeId, begin, end);
        if(CollectionUtils.isEmpty(roomTypeStockList)){
            return null;
        }
        List<StockInfoDto> result = new ArrayList<StockInfoDto>();
        for(RoomTypeStock roomTypeStock : roomTypeStockList){
            StockInfoDto stockInfoDto = new StockInfoDto();
            stockInfoDto.setDate(DateUtils.formatDateTime(roomTypeStock.getDay(), DateUtils.FORMAT_DATE));
            stockInfoDto.setNum(roomTypeStock.getNumber().toString());
            result.add(stockInfoDto);
        }
        return result;
    }


    public List<StockInfoDto> getRemoteStock (Long roomTypeId, Date begin, Date end) {

        List<StockInfoDto> result = new ArrayList<StockInfoDto>();
        if (null == roomTypeId || null == begin || null == end) {
            return result;
        }

        //
        Long hotelId = this.roomTypeService.getHotelIdByRedis(roomTypeId);
        if (null == hotelId) {
            return result;
        }

        Hotel hotel = this.hotelMapper.selectByPrimaryKey(hotelId);
        if (null == hotel) {
            return result;
        }

        //
        QueryStockRequest queryStockRequest = new QueryStockRequest();
        queryStockRequest.setHotelid(hotel.getFangId().toString());
        queryStockRequest.setBegintime(DateUtils.formatDateTime(begin, DateUtils.FORMAT_DATE));
        queryStockRequest.setEndtime(DateUtils.formatDateTime(end, DateUtils.FORMAT_DATE));

        //
        QueryStockResponse response = hotelStockRemoteService.queryStock(queryStockRequest);

        if (null == response) {
            return result;
        }

        QueryStockResponse.Roominfo roominfo = response.getData();
        if (null == roominfo) {
            return result;
        }
        List<QueryStockResponse.Roomtypestocks>  roomtypestocksList = roominfo.getRoomtypestocks();
        if (null == roomtypestocksList || roomtypestocksList.isEmpty()) {
            return result;
        }

        //
        for (QueryStockResponse.Roomtypestocks roomtypestocks : roomtypestocksList) {
            int responseRoomTypeId = roomtypestocks.getRoomtypeid();
            if (roomTypeId.intValue() == responseRoomTypeId) {
                List<QueryStockResponse.Stockinfos> stockList = roomtypestocks.getStockinfos();
                for (QueryStockResponse.Stockinfos stockinfo : stockList) {
                    StockInfoDto infoDto = new StockInfoDto();
                    BeanUtils.copyProperties(stockinfo, infoDto);
                    result.add(infoDto);
                }
                return result;
            }

        }

        return result;

    }
}
