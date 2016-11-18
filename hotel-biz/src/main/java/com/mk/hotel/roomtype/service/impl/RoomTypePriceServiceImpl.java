package com.mk.hotel.roomtype.service.impl;

import com.dianping.cat.Cat;
import com.mk.framework.date.DateUtils;
import com.mk.framework.enums.ValidEnum;
import com.mk.framework.excepiton.MyErrorEnum;
import com.mk.framework.excepiton.MyException;
import com.mk.framework.json.JsonUtils;
import com.mk.framework.redis.MkJedisConnectionFactory;
import com.mk.hotel.common.utils.OtsInterface;
import com.mk.hotel.consume.enums.TopicEnum;
import com.mk.hotel.hotelinfo.enums.HotelSourceEnum;
import com.mk.hotel.mq.producer.MsgProducer;
import com.mk.hotel.roomtype.RoomTypePriceService;
import com.mk.hotel.roomtype.RoomTypeService;
import com.mk.hotel.roomtype.dto.RoomTypeDto;
import com.mk.hotel.roomtype.dto.RoomTypePriceDto;
import com.mk.hotel.roomtype.enums.RoomTypePriceCacheEnum;
import com.mk.hotel.roomtype.mapper.RoomTypePriceMapper;
import com.mk.hotel.roomtype.model.RoomTypePrice;
import com.mk.hotel.roomtype.model.RoomTypePriceExample;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RoomTypePriceServiceImpl implements RoomTypePriceService {

    @Autowired
    private MsgProducer msgProducer;
    @Autowired
    private RoomTypeService roomTypeService;
    @Autowired
    private RoomTypePriceMapper roomTypePriceMapper;

    @Autowired
    private MkJedisConnectionFactory jedisConnectionFactory;

    public int saveOrUpdateByFangId(List<RoomTypePriceDto> roomTypePriceDtoList, HotelSourceEnum hotelSourceEnum) {

        for (RoomTypePriceDto dto : roomTypePriceDtoList) {
            this.saveOrUpdateByFangId(dto, hotelSourceEnum);
        }

        return 1;
    }

    public int saveOrUpdateByRoomTypeId(RoomTypePriceDto roomTypePriceDto, String operator) {

        if (null == roomTypePriceDto) {
            throw new MyException("roomTypePriceDto 不可为空");
        }

        //roomType
        RoomTypeDto roomTypeDto =
                this.roomTypeService.selectById(roomTypePriceDto.getRoomTypeId());
        if (null == roomTypeDto) {
            throw new MyException("roomTypePriceDto.getFangHotelId(), roomTypePriceDto.getFangRoomTypeId() 错误");
        }
        //
        RoomTypePriceExample roomTypePriceExample = new RoomTypePriceExample();
        roomTypePriceExample.createCriteria().andRoomTypeIdEqualTo(roomTypeDto.getId()).andDayEqualTo(roomTypePriceDto.getDay());
        List<RoomTypePrice> roomTypePriceList = this.roomTypePriceMapper.selectByExample(roomTypePriceExample);

        //db
        if (roomTypePriceList.isEmpty()) {
            RoomTypePrice roomTypePrice = new RoomTypePrice();
            roomTypePrice.setRoomTypeId(roomTypeDto.getId());
            roomTypePrice.setDay(roomTypePriceDto.getDay());
            roomTypePrice.setPrice(roomTypePriceDto.getPrice());
            roomTypePrice.setCost(roomTypePriceDto.getCost());
            roomTypePrice.setSettlePrice(roomTypePriceDto.getSettlePrice());
            roomTypePrice.setIsValid("T");

            roomTypePrice.setCreateDate(new Date());
            roomTypePrice.setCreateBy(operator);
            roomTypePrice.setUpdateDate(new Date());
            roomTypePrice.setUpdateBy(operator);
            return this.roomTypePriceMapper.insert(roomTypePrice);
        } else {
            RoomTypePrice dbRoomTypePrice = roomTypePriceList.get(0);
            dbRoomTypePrice.setPrice(roomTypePriceDto.getPrice());
            dbRoomTypePrice.setCost(roomTypePriceDto.getCost());
            dbRoomTypePrice.setSettlePrice(roomTypePriceDto.getSettlePrice());
            dbRoomTypePrice.setUpdateDate(new Date());
            dbRoomTypePrice.setUpdateBy(operator);
            return this.roomTypePriceMapper.updateByPrimaryKeySelective(dbRoomTypePrice);
        }
    }

    public int saveOrUpdateByFangId(RoomTypePriceDto roomTypePriceDto, HotelSourceEnum hotelSourceEnum) {

        if (null == roomTypePriceDto) {
            throw MyErrorEnum.ROOM_TYPE_PRICE_DTO_IS_NULL.getMyException();
        }

        //roomType
        RoomTypeDto roomTypeDto =
                this.roomTypeService.selectByFangId(
                        roomTypePriceDto.getFangHotelId(), roomTypePriceDto.getFangRoomTypeId(), hotelSourceEnum);
        if (null == roomTypeDto) {
            throw MyErrorEnum.ROOM_PARAMS_ERROR.getMyException();
        }
        //
        RoomTypePriceExample roomTypePriceExample = new RoomTypePriceExample();
        roomTypePriceExample.createCriteria().andRoomTypeIdEqualTo(roomTypeDto.getId()).andDayEqualTo(roomTypePriceDto.getDay());
        List<RoomTypePrice> roomTypePriceList = this.roomTypePriceMapper.selectByExample(roomTypePriceExample);

        //redis
        this.updateRedisPrice(
                roomTypeDto.getId(), roomTypeDto.getName(),
                roomTypePriceDto.getDay(), roomTypePriceDto.getPrice(), roomTypePriceDto.getCost(), roomTypePriceDto.getSettlePrice(),
                "RoomTypePriceService.saveOrUpdateByFangId");

        //db
        if (roomTypePriceList.isEmpty()) {
            RoomTypePrice roomTypePrice = new RoomTypePrice();
            roomTypePrice.setRoomTypeId(roomTypeDto.getId());
            roomTypePrice.setDay(roomTypePriceDto.getDay());
            roomTypePrice.setPrice(roomTypePriceDto.getPrice());
            roomTypePrice.setCost(roomTypePriceDto.getCost());
            roomTypePrice.setSettlePrice(roomTypePriceDto.getSettlePrice());
            roomTypePrice.setIsValid("T");

            roomTypePrice.setCreateDate(new Date());
            roomTypePrice.setCreateBy("hotel_system");
            roomTypePrice.setUpdateDate(new Date());
            roomTypePrice.setUpdateBy("hotel_system");

            return this.roomTypePriceMapper.insert(roomTypePrice);

        } else {
            RoomTypePrice dbRoomTypePrice = roomTypePriceList.get(0);
            dbRoomTypePrice.setPrice(roomTypePriceDto.getPrice());
            dbRoomTypePrice.setCost(roomTypePriceDto.getCost());
            dbRoomTypePrice.setSettlePrice(roomTypePriceDto.getSettlePrice());
            dbRoomTypePrice.setUpdateDate(new Date());
            dbRoomTypePrice.setUpdateBy("hotel_system");
            return this.roomTypePriceMapper.updateByPrimaryKeySelective(dbRoomTypePrice);
        }
    }

    public void updateRedisPrice(Long roomTypeId, String roomTypeName, Date day, BigDecimal price,BigDecimal cost, BigDecimal settlePrice, String cacheFrom) {
        if (null == roomTypeId || null == day || null == price) {
            return;
        }

        //
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = format.format(day);
        String strDateTime = formatTime.format(new Date());

        //
        Jedis jedis = null;
        try {
            //
            jedis = jedisConnectionFactory.getJedis();
            String priceHashName = RoomTypePriceCacheEnum.getPriceHashName(String.valueOf(roomTypeId));

            //original
            BigDecimal originalPrice = null;
            BigDecimal originalCost = null;
            BigDecimal originalSettlePrice = null;

            String originalJson = jedis.hget(priceHashName, strDate);
            if (StringUtils.isNotBlank(originalJson)) {
                com.mk.hotel.roomtype.redisbean.RoomTypePrice originalRoomTypePrice =
                        JsonUtils.fromJson(originalJson, com.mk.hotel.roomtype.redisbean.RoomTypePrice.class);
                originalPrice = originalRoomTypePrice.getPrice();
                originalCost = originalRoomTypePrice.getOriginPrice();
                originalSettlePrice = originalRoomTypePrice.getSettlePrice();
            }

            //判断是否要持久化. 持久化条件 1-原值有空情况, 2-原值与新值不相同(有变化)
            if (null == originalPrice || null == originalCost || null == originalSettlePrice
                    || originalPrice.compareTo(price) != 0
                    || originalCost.compareTo(cost) != 0
                    || originalSettlePrice.compareTo(settlePrice) != 0) {
                //
                com.mk.hotel.roomtype.redisbean.RoomTypePrice roomTypePrice = new com.mk.hotel.roomtype.redisbean.RoomTypePrice();
                roomTypePrice.setRoomTypeId(roomTypeId);
                roomTypePrice.setRoomTypeName(roomTypeName);
                roomTypePrice.setPrice(price);
                roomTypePrice.setOriginPrice(cost);
                roomTypePrice.setSettlePrice(settlePrice);
                roomTypePrice.setCacheTime(strDateTime);
                roomTypePrice.setCacheFrom(cacheFrom);

                //set
                jedis.hset(priceHashName, strDate, JsonUtils.toJson(roomTypePrice));

                //save to db
                this.messageToPersist(roomTypeId, day);
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


    @Override
    public RoomTypePriceDto queryFromRedis(Long roomTypeId, Date day) {
        if (null == roomTypeId || null == day) {
            return null;
        }

        //
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String strDate = format.format(day);

        //
        Jedis jedis = null;
        try {
            //
            jedis = jedisConnectionFactory.getJedis();
            String priceHashName = RoomTypePriceCacheEnum.getPriceHashName(String.valueOf(roomTypeId));
            //get
            String priceJson = jedis.hget(priceHashName, strDate);

            com.mk.hotel.roomtype.redisbean.RoomTypePrice roomTypePrice =
                    JsonUtils.fromJson(priceJson, com.mk.hotel.roomtype.redisbean.RoomTypePrice.class);

            //
            RoomTypePriceDto dto = new RoomTypePriceDto();
            dto.setRoomTypeId(roomTypeId);
            if (null != roomTypePrice) {
                dto.setPrice(roomTypePrice.getPrice());
                dto.setCost(roomTypePrice.getOriginPrice());
                dto.setSettlePrice(roomTypePrice.getSettlePrice());
            }
            dto.setDay(day);

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

    public RoomTypePriceDto queryFromDb(Long roomTypeId, Date date) {
        if (null == roomTypeId || null == date) {
            return null;
        }
        Date day = DateUtils.roundingDate(date);

        //
        RoomTypePriceExample example = new RoomTypePriceExample();
        example.createCriteria().andDayEqualTo(day).andRoomTypeIdEqualTo(roomTypeId).andIsValidEqualTo(ValidEnum.VALID.getCode());
        List<RoomTypePrice> roomTypePriceList = roomTypePriceMapper.selectByExample(example);

        if (roomTypePriceList.isEmpty()) {
            return null;
        } else {
            RoomTypePrice price = roomTypePriceList.get(0);
            return this.convertToDto(price);
        }
    }
    public List<RoomTypePriceDto> queryFromDb(Long roomTypeId, Date fromDate, Date toDate){

        List<RoomTypePriceDto> roomTypePriceDtoList = new ArrayList<RoomTypePriceDto>();
        if (null == fromDate || null == toDate) {
            return roomTypePriceDtoList;
        }

        //rounding
        fromDate = DateUtils.roundingDate(fromDate);
        toDate = DateUtils.roundingDate(toDate);

        //开始时间晚于结束时间,直接返回
        if (fromDate.after(toDate)) {
            return roomTypePriceDtoList;
        }

        //
        if (fromDate.compareTo(toDate) == 0) {
            //同一天,不处理
        } else {
            //查询减一天
            toDate = DateUtils.addDays(toDate, -1);
        }

        //
        RoomTypePriceExample example = new RoomTypePriceExample();
        example.createCriteria().andDayBetween(fromDate, toDate).andRoomTypeIdEqualTo(roomTypeId);
        List<RoomTypePrice> roomTypePriceList = roomTypePriceMapper.selectByExample(example);

        //convert to dto
        for (RoomTypePrice roomTypePrice : roomTypePriceList) {
            roomTypePriceDtoList.add(this.convertToDto(roomTypePrice));
        }
        return roomTypePriceDtoList;
    }

    public List<RoomTypePriceDto> queryFromRedis(Long roomTypeId, Date fromDate, Date toDate){

        List<RoomTypePriceDto> roomTypePriceDtoList = new ArrayList<RoomTypePriceDto>();
        if (null == fromDate || null == toDate) {
            return roomTypePriceDtoList;
        }

        //rounding
        fromDate = DateUtils.roundingDate(fromDate);
        toDate = DateUtils.roundingDate(toDate);

        //开始时间晚于结束时间,直接返回
        if (fromDate.after(toDate)) {
            return roomTypePriceDtoList;
        }

        //
        if (fromDate.compareTo(toDate) == 0) {
            //同一天,不处理
        } else {
            //查询减一天
            toDate = DateUtils.addDays(toDate, -1);
        }

        //
        Date[] dates = DateUtils.getStartEndDate(fromDate, toDate);

        //convert to dto
        for (Date date : dates) {
            roomTypePriceDtoList.add(this.queryFromRedis(roomTypeId, date));
        }
        return roomTypePriceDtoList;
    }

    private RoomTypePriceDto convertToDto(RoomTypePrice roomTypePrice) {
        if (null == roomTypePrice) {
            return null;
        }

        //
        RoomTypePriceDto dto = new RoomTypePriceDto();

        dto.setId(roomTypePrice.getId());
        dto.setRoomTypeId(roomTypePrice.getRoomTypeId());
        dto.setDay(roomTypePrice.getDay());
        dto.setPrice(roomTypePrice.getPrice());
        dto.setCreateDate(roomTypePrice.getCreateDate());
        dto.setCreateBy(roomTypePrice.getCreateBy());
        dto.setUpdateBy(roomTypePrice.getUpdateBy());
        dto.setUpdateDate(roomTypePrice.getUpdateDate());
        dto.setIsValid(roomTypePrice.getIsValid());
        dto.setCost(roomTypePrice.getCost());
        return dto;
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
            String msgKey = TopicEnum.ROOM_TYPE_PRICE.getName() + System.currentTimeMillis() + roomTypeId + strDate;
            msgProducer.sendMsg(TopicEnum.ROOM_TYPE_PRICE.getName(), "savePersistToDb", msgKey, message);
        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        }
    }

    public int savePersistToDb(Long roomTypeId, Date date) {

        if (null == roomTypeId || null == date) {
            return -1;
        }

        //get from redis
        RoomTypePriceDto redisDto = this.queryFromRedis(roomTypeId, date);
        if (null == redisDto) {
            return -1;
        }

        //get from db
        RoomTypePriceDto dbDto = this.queryFromDb(roomTypeId, date);

        if (null == dbDto) {
            dbDto = new RoomTypePriceDto();
            dbDto.setRoomTypeId(roomTypeId);
            dbDto.setDay(date);
            dbDto.setIsValid(com.mk.hotel.common.enums.ValidEnum.VALID.getCode());
        }

        dbDto.setCost(redisDto.getCost());
        dbDto.setPrice(redisDto.getPrice());
        dbDto.setSettlePrice(redisDto.getSettlePrice());

        int result = this.saveOrUpdateByRoomTypeId(dbDto, "savePersistToDb");

        //
        RoomTypeDto roomTypeDto = this.roomTypeService.selectById(roomTypeId);
        OtsInterface.initHotel(roomTypeDto.getHotelId());
        return result;
    }
}
