package com.mk.hotel.roomtype.service.impl;

import com.dianping.cat.Cat;
import com.mk.framework.date.DateUtils;
import com.mk.framework.excepiton.MyErrorEnum;
import com.mk.framework.excepiton.MyException;
import com.mk.framework.json.JsonUtils;
import com.mk.framework.redis.MkJedisConnectionFactory;
import com.mk.hotel.common.enums.ValidEnum;
import com.mk.hotel.consume.enums.TopicEnum;
import com.mk.hotel.hotelinfo.enums.HotelSourceEnum;
import com.mk.hotel.hotelinfo.mapper.HotelMapper;
import com.mk.hotel.hotelinfo.model.Hotel;
import com.mk.hotel.mq.producer.MsgProducer;
import com.mk.hotel.remote.pms.hotelstock.HotelStockRemoteService;
import com.mk.hotel.remote.pms.hotelstock.json.QueryStockRequest;
import com.mk.hotel.remote.pms.hotelstock.json.QueryStockResponse;
import com.mk.hotel.roomtype.RoomTypeFullStockLogService;
import com.mk.hotel.roomtype.RoomTypeService;
import com.mk.hotel.roomtype.RoomTypeStockService;
import com.mk.hotel.roomtype.bean.RoomTypeStockBean;
import com.mk.hotel.roomtype.dto.*;
import com.mk.hotel.roomtype.enums.RoomTypeStockCacheEnum;
import com.mk.hotel.roomtype.mapper.RoomTypeStockMapper;
import com.mk.hotel.roomtype.model.RoomTypeStock;
import com.mk.hotel.roomtype.model.RoomTypeStockExample;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by huangjie on 16/5/11.
 */
@Service
public class RoomTypeStockServiceImpl implements RoomTypeStockService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

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

    @Autowired
    private MkJedisConnectionFactory jedisConnectionFactory;

    @Autowired
    private MsgProducer msgProducer;


    private Integer getValueByRedisKeyName(Jedis jedis, String key, String field) {

        boolean isNeedCloseJedis = false;
        if (null == jedis) {
            jedis = jedisConnectionFactory.getJedis();
            isNeedCloseJedis = true;
        }
        String strNum = jedis.hget(key, field);
        if(StringUtils.isBlank(strNum)) {
            return null;
        }

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
            jedis = jedisConnectionFactory.getJedis();

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
                    throw MyErrorEnum.COMMON_LOCK_TIMEOUT.getMyException();
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

        throw MyErrorEnum.COMMON_LOCK_TIMEOUT.getMyException();
    }

    public void unlock(String hotelId, String roomTypeId, Date day) {
        Jedis jedis = null;
        try {
            jedis = jedisConnectionFactory.getJedis();

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
            throw MyErrorEnum.ROOM_NOT_FOUND.getMyException();
        }

        //
        QueryStockRequest queryStockRequest = new QueryStockRequest();
        queryStockRequest.setHotelid(hotel.getFangId().toString());
        queryStockRequest.setRoomtypeid(roomTypeDto.getFangId().toString());
        queryStockRequest.setBegintime(DateUtils.formatDateTime(new Date(), DateUtils.FORMAT_DATE));
        queryStockRequest.setEndtime(DateUtils.formatDate(DateUtils.addDays(new Date(), 30)));
        QueryStockResponse response = hotelStockRemoteService.queryStock(queryStockRequest);
        if (response == null || response.getData() == null) {
            throw MyErrorEnum.ROOM_FANGBABA_INTERFACE_ERROR.getMyException();
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
            throw MyErrorEnum.ROOM_UPDATE_STOCK_ERROR.getMyException();
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = format.format(day);

        //
        Jedis jedis = null;
        try {
            //
            jedis = jedisConnectionFactory.getJedis();

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
            StringBuilder result = new StringBuilder();
            if (promoNum < 0) {
                RoomTypeDto dto = this.roomTypeService.selectById(roomTypeId);
                result.append(dto.getName()).append("房型,").append(strDate).append(" 特价房超卖:").append(promoNum * -1).append("间\n");
            }

            if (availableNum < 0) {
                RoomTypeDto dto = this.roomTypeService.selectById(roomTypeId);
                result.append(dto.getName()).append("房型,").append(strDate).append(" 普通房超卖:").append(availableNum * -1).append("间\n");
            }

            //判断是否要持久化. 持久化条件 1-原值有空情况, 2-原值与新值不相同(有变化)
            if (null == originalTotalPromoNum || null == originalAvailableNum || null == originalPromoNum ||
                    originalTotalPromoNum.compareTo(totalPromoNum) != 0 ||
                    originalAvailableNum.compareTo(allAvailableNum) != 0 ||
                    originalPromoNum.compareTo(promoNum) != 0) {

                //
                jedis.hset(totalPromoHashName, strDate, String.valueOf(totalPromoNum));
                jedis.hset(availableHashName, strDate, String.valueOf(availableNum));
                jedis.hset(promoHashName, strDate, String.valueOf(promoNum));

                //
                this.messageToPersist(roomTypeId, day);
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
            throw MyErrorEnum.ROOM_UPDATE_STOCK_ERROR.getMyException();
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
                jedis = jedisConnectionFactory.getJedis();
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
            throw MyErrorEnum.ROOM_UPDATE_STOCK_ERROR.getMyException();
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
            jedis = jedisConnectionFactory.getJedis();

            //key
            String totalHashName = RoomTypeStockCacheEnum.getTotalHashName(String.valueOf(roomTypeId));
            String totalPromoHashName = RoomTypeStockCacheEnum.getTotalPromoHashName(String.valueOf(roomTypeId));

            String availableHashName = RoomTypeStockCacheEnum.getAvailableHashName(String.valueOf(roomTypeId));
            String promoHashName = RoomTypeStockCacheEnum.getPromoHashName(String.valueOf(roomTypeId));

            //
            Integer originalTotalHashNum = getValueByRedisKeyName(jedis, totalHashName, strDate);
            Integer originalTotalPromoNum = getValueByRedisKeyName(jedis, totalPromoHashName, strDate);
            Integer originalAvailableNum = getValueByRedisKeyName(jedis, availableHashName, strDate);
            Integer originalPromoNum = getValueByRedisKeyName(jedis, promoHashName, strDate);

            //
            RoomTypeStockBean stockBean = this.calcStockByTotal(roomTypeId, strDate, totalNum, totalPromoNum, jedis);
            if (null == stockBean) {
                return "库存更新失败,请联系管理员1";
            }

            //
            Integer availableNum = stockBean.getAvailableNum();
            Integer promoNum = stockBean.getPromoNum();

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

            //判断是否要持久化. 持久化条件 1-原值有空情况, 2-原值与新值不相同(有变化)
            if (null == originalTotalHashNum ||null == originalTotalPromoNum ||
                    null == originalAvailableNum || null == originalPromoNum ||
                    originalTotalHashNum.compareTo(totalNum) != 0 ||
                    originalTotalPromoNum.compareTo(totalPromoNum) != 0 ||
                    originalAvailableNum.compareTo(availableNum) != 0 ||
                    originalPromoNum.compareTo(promoNum) != 0) {

                //
                jedis.hset(totalHashName, strDate, String.valueOf(totalNum));
                jedis.hset(totalPromoHashName, strDate, String.valueOf(totalPromoNum));
                jedis.hset(availableHashName, strDate, String.valueOf(availableNum));
                jedis.hset(promoHashName, strDate, String.valueOf(promoNum));

                //
                this.messageToPersist(roomTypeId, day);
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

                //持久化
                this.messageToPersist(roomTypeId, day);
            }
        } finally {
            //unlock
            for (Date day : dates) {
                this.unlock(String.valueOf(hotelId), String.valueOf(roomTypeId), day);
            }
        }
    }

//    public List<StockInfoDto> getStock(Long roomTypeId, Date begin, Date end){
//        List<RoomTypeStock> roomTypeStockList = queryFromDb(roomTypeId, begin, end);
//        if(CollectionUtils.isEmpty(roomTypeStockList)){
//            return null;
//        }
//        List<StockInfoDto> result = new ArrayList<StockInfoDto>();
//        for(RoomTypeStock roomTypeStock : roomTypeStockList){
//            StockInfoDto stockInfoDto = new StockInfoDto();
//            stockInfoDto.setDate(DateUtils.formatDateTime(roomTypeStock.getDay(), DateUtils.FORMAT_DATE));
//            stockInfoDto.setNum(roomTypeStock.getNumber().toString());
//            result.add(stockInfoDto);
//        }
//        return result;
//    }

    public List<StockInfoDto> getRemoteStock (Long roomTypeId, Date begin, Date end) {

        List<StockInfoDto> result = new ArrayList<StockInfoDto>();
        if (null == roomTypeId || null == begin || null == end) {
            return result;
        }

        //
        RoomTypeDto roomTypeDto = roomTypeService.selectById(roomTypeId);
        if (null == roomTypeDto) {
            return result;
        }

        Hotel hotel = this.hotelMapper.selectByPrimaryKey(roomTypeDto.getHotelId());
        if (null == hotel || !HotelSourceEnum.LEZHU.getId().equals(hotel.getSourceType())) {
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
            if (roomTypeDto.getFangId().intValue() == responseRoomTypeId) {
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

    public int savePersistToDb(Long roomTypeId, Date date){

        if (null == roomTypeId || null == date) {
            return -1;
        }

        //get from redis
        RoomTypeStockRedisDto redisDto = this.queryFromRedis(roomTypeId, date);
        if (null == redisDto) {
            return -1;
        }

        //get from db
        RoomTypeStockDto dbDto = this.queryFromDb(roomTypeId, date);

        if (null == dbDto) {
            dbDto = new RoomTypeStockDto();
            dbDto.setDay(date);
            dbDto.setRoomTypeId(roomTypeId);
            dbDto.setUpdateBy("savePersistToDb");
            dbDto.setUpdateDate(new Date());
            dbDto.setCreateBy("savePersistToDb");
            dbDto.setCreateDate(new Date());
            dbDto.setIsValid(ValidEnum.VALID.getCode());
        }

        //
        Integer num = 0;
        Integer availableNum = redisDto.getAvailableNum();
        Integer promoNum = redisDto.getPromoNum();

        if (null != availableNum && null != promoNum) {
            num = availableNum + promoNum;
        } else if (null != availableNum) {
            num = availableNum;
        } else if (null != promoNum) {
            num = promoNum;
        }

        dbDto.setNumber(num.longValue());
        if (null == redisDto.getTotalNum()) {
            dbDto.setTotalNumber(0l);
        } else {
            dbDto.setTotalNumber(redisDto.getTotalNum().longValue());
        }
        dbDto.setUpdateBy("savePersistToDb");
        dbDto.setUpdateDate(new Date());
        //
        return this.saveOrUpdate(dbDto);
    }

    public int saveOrUpdate(RoomTypeStockDto dto) {
        if (null == dto) {
            return -1;
        }

        RoomTypeStock stock = convertToBean(dto);

        if (null == stock.getId()) {
            int result = -1;
            result = this.roomTypeStockMapper.insert(stock);
            dto.setId(stock.getId());
            return result;
        } else {
            return this.roomTypeStockMapper.updateByPrimaryKey(stock);
        }
    }

    private RoomTypeStockDto convertToDto(RoomTypeStock stock) {

        if (null != stock) {
            RoomTypeStockDto dto = new RoomTypeStockDto();
            dto.setId(stock.getId());
            dto.setRoomTypeId(stock.getRoomTypeId());
            dto.setDay(stock.getDay());
            dto.setNumber(stock.getNumber());
            dto.setTotalNumber(stock.getTotalNumber());
            dto.setCreateBy(stock.getCreateBy());
            dto.setCreateDate(stock.getCreateDate());
            dto.setUpdateBy(stock.getUpdateBy());
            dto.setUpdateDate(stock.getUpdateDate());
            dto.setIsValid(stock.getIsValid());
            return dto;
        } else {
            return null;
        }
    }

    private RoomTypeStock convertToBean(RoomTypeStockDto dto) {

        if (null != dto) {
            RoomTypeStock stock = new RoomTypeStock();

            stock.setId(dto.getId());
            stock.setRoomTypeId(dto.getRoomTypeId());
            stock.setDay(dto.getDay());
            stock.setNumber(dto.getNumber());
            stock.setTotalNumber(dto.getTotalNumber());
            stock.setCreateBy(dto.getCreateBy());
            stock.setCreateDate(dto.getCreateDate());
            stock.setUpdateBy(dto.getUpdateBy());
            stock.setUpdateDate(dto.getUpdateDate());
            stock.setIsValid(dto.getIsValid());
            return stock;
        } else {
            return null;
        }
    }


    private void messageToPersist (Long roomTypeId, Date date) {
        if (null == roomTypeId || null == date) {
            return;
        }

        Map<String, Object> messageMap = new HashMap<String, Object>();
        messageMap.put("roomTypeId", roomTypeId.toString());
        messageMap.put("date", date);

        String message = JsonUtils.toJson(messageMap, DateUtils.FORMAT_DATETIME);
        String strDate =  DateUtils.formatDateTime(date);
        try {
            String msgKey = TopicEnum.ROOM_TYPE_STOCK.getName() + System.currentTimeMillis() + roomTypeId + strDate;
            msgProducer.sendMsg(TopicEnum.ROOM_TYPE_STOCK.getName(), "savePersistToDb", msgKey, message);
        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        }
    }

    public RoomTypeStockDto queryFromDb(Long roomTypeId, Date date) {

        //
        RoomTypeStockExample example = new RoomTypeStockExample();
        example.createCriteria().andRoomTypeIdEqualTo(roomTypeId).andDayEqualTo(date).andIsValidEqualTo(ValidEnum.VALID.getCode());
        List<RoomTypeStock> roomTypeStockList = roomTypeStockMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(roomTypeStockList)){
            return null;
        } else if (roomTypeStockList.size() == 1) {
            RoomTypeStock stock = roomTypeStockList.get(0);
            return convertToDto(stock);
        } else {
            throw new MyException("房型库存配置错误,根据房型和时间查到多条配置信息");
        }
    }

    public List<RoomTypeStockDto> queryFromDb(Long roomTypeId, Date fromDate, Date toDate) {
        RoomTypeStockExample example = new RoomTypeStockExample();
        example.createCriteria().andRoomTypeIdEqualTo(roomTypeId).andDayBetween(fromDate, toDate);
        List<RoomTypeStock> list = roomTypeStockMapper.selectByExample(example);

        List<RoomTypeStockDto> dtoList = new ArrayList<RoomTypeStockDto>();
        for (RoomTypeStock bean : list) {
            RoomTypeStockDto dto = this.convertToDto(bean);
            dtoList.add(dto);
        }
        return dtoList;
    }


    @Override
    public RoomTypeStockRedisDto queryFromRedis(Long roomTypeId, Date day) {
        if (null == roomTypeId || null == day ) {
            return null;
        }

        //key
        String totalHashName = RoomTypeStockCacheEnum.getTotalHashName(String.valueOf(roomTypeId));
        String totalPromoHashName = RoomTypeStockCacheEnum.getTotalPromoHashName(String.valueOf(roomTypeId));

        String availableHashName = RoomTypeStockCacheEnum.getAvailableHashName(String.valueOf(roomTypeId));
        String promoHashName = RoomTypeStockCacheEnum.getPromoHashName(String.valueOf(roomTypeId));

        //
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = format.format(day);

        //
        Jedis jedis = null;
        try {
            //
            jedis = jedisConnectionFactory.getJedis();
            //
            Integer totalNum = getValueByRedisKeyName(jedis, totalHashName, strDate);
            Integer totalPromoNum = getValueByRedisKeyName(jedis, totalPromoHashName, strDate);
            Integer availableNum = getValueByRedisKeyName(jedis, availableHashName, strDate);
            Integer promoNum = getValueByRedisKeyName(jedis, promoHashName, strDate);

            //
            RoomTypeStockRedisDto dto = new RoomTypeStockRedisDto();
            dto.setRoomTypeId(roomTypeId);
            dto.setDay(day);
            dto.setTotalNum(totalNum);
            dto.setTotalPromoNum(totalPromoNum);
            dto.setAvailableNum(availableNum);
            dto.setPromoNum(promoNum);

            return dto;

        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }

        return null;
    }

    public List<RoomTypeStockRedisDto> queryFromRedis(Long roomTypeId, Date fromDate, Date toDate) {

        if (null == roomTypeId || null == fromDate || null == toDate) {
            return new ArrayList<RoomTypeStockRedisDto>();
        }

        //
        Date[] dates = DateUtils.getStartEndDate(fromDate, toDate);

        //
        List<RoomTypeStockRedisDto> dtoList = new ArrayList<RoomTypeStockRedisDto>();
        for (Date date : dates) {
            RoomTypeStockRedisDto dto = this.queryFromRedis(roomTypeId, date);
            dtoList.add(dto);
        }
        return dtoList;
    }


    public Map<String, Integer> getPromoStockFromRedis(String roomTypeId, Date from, Date to) {

        if (StringUtils.isBlank(roomTypeId) || null == from || null == to) {
            return new HashMap<String, Integer>();
        }

        Map<String, Integer> result = new LinkedMap();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        //
        Jedis jedis = null;
        //
        Date[] dates = DateUtils.getStartEndDate(from, to);
        try {
            //
            jedis = jedisConnectionFactory.getJedis();
            //hashName
            String hashName = RoomTypeStockCacheEnum.getPromoHashName(roomTypeId);

            //
            for (Date date : dates) {
                String strDate = format.format(date);
                String value = jedis.hget(hashName, strDate);

                if (StringUtils.isBlank(value)) {
                    result.put(strDate, null);
                } else {
                    Integer intVal = Integer.parseInt(value);
                    result.put(strDate, intVal);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }

        return result;
    }

    public Map<String, Integer> getNormalStockFromRedis(String roomTypeId, Date from, Date to) {

        if (StringUtils.isBlank(roomTypeId) || null == from || null == to) {
            return new HashMap<String, Integer>();
        }

        Map<String, Integer> result = new LinkedMap();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        //
        Jedis jedis = null;
        //
        Date[] dates = DateUtils.getStartEndDate(from, to);
        try {
            //
            jedis = jedisConnectionFactory.getJedis();
            //hashName
            String hashName = RoomTypeStockCacheEnum.getAvailableHashName(roomTypeId);

            //
            for (Date date : dates) {
                String strDate = format.format(date);
                String value = jedis.hget(hashName, strDate);

                if (StringUtils.isBlank(value)) {
                    result.put(strDate, null);
                } else {
                    Integer intVal = Integer.parseInt(value);
                    result.put(strDate, intVal);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }

        return result;
    }

    public Integer getAvailableByPromoFromRedis(String roomTypeId, String from, String to) {

        if (StringUtils.isBlank(roomTypeId)
                || StringUtils.isBlank(from) || StringUtils.isBlank(to)) {
            throw new MyException("roomTypeId, from, to 不可为空");
        }
        //form  to  YYMMDD
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date dateFrom = null;
        Date dateTo = null;
        try {
            dateFrom = format.parse(from);
            dateTo = format.parse(to);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new MyException("from, to 格式错误");
        }

        //
        Map<String, Integer> stock = this.getPromoStockFromRedis(roomTypeId, dateFrom, dateTo);

        //取最小数
        TreeSet<Integer> valueSet = new TreeSet();
        for (Integer value : stock.values()) {
            if (null != value) {
                valueSet.add(value);
            }
        }

        //
        if (valueSet.isEmpty()) {
            return null;
        } else {
            return valueSet.first();
        }
    }

    public Integer getAvailableByNormalFromRedis(String roomTypeId, String from, String to) {

        if (StringUtils.isBlank(roomTypeId)
                || StringUtils.isBlank(from) || StringUtils.isBlank(to)) {
            throw new MyException("hotelId, roomTypeId, from, to 不可为空");
        }
        //form  to  YYMMDD
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date dateFrom = null;
        Date dateTo = null;
        try {
            dateFrom = format.parse(from);
            dateTo = format.parse(to);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new MyException("from, to 格式错误");
        }

        //
        Map<String, Integer> stock = this.getNormalStockFromRedis(roomTypeId, dateFrom, dateTo);

        //取最小数
        TreeSet<Integer> valueSet = new TreeSet();
        for (Integer value : stock.values()) {
            if (null != value) {
                valueSet.add(value);
            }
        }

        //
        if (valueSet.isEmpty()) {
            return null;
        } else {
            return valueSet.first();
        }
    }
}
