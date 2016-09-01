package com.mk.hotel.roomtype.service.impl;

import com.dianping.cat.Cat;
import com.mk.framework.DateUtils;
import com.mk.framework.JsonUtils;
import com.mk.framework.excepiton.MyException;
import com.mk.framework.proxy.http.RedisUtil;
import com.mk.hotel.hotelinfo.enums.HotelSourceEnum;
import com.mk.hotel.roomtype.RoomTypePriceService;
import com.mk.hotel.roomtype.RoomTypeService;
import com.mk.hotel.roomtype.dto.RoomTypeDto;
import com.mk.hotel.roomtype.dto.RoomTypePriceDto;
import com.mk.hotel.roomtype.enums.RoomTypePriceCacheEnum;
import com.mk.hotel.roomtype.mapper.RoomTypePriceMapper;
import com.mk.hotel.roomtype.model.RoomTypePrice;
import com.mk.hotel.roomtype.model.RoomTypePriceExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoomTypePriceServiceImpl implements RoomTypePriceService {

    @Autowired
    private RoomTypeService roomTypeService;
    @Autowired
    private RoomTypePriceMapper roomTypePriceMapper;

    public int saveOrUpdateByFangId(List<RoomTypePriceDto> roomTypePriceDtoList, HotelSourceEnum hotelSourceEnum) {

        for (RoomTypePriceDto dto : roomTypePriceDtoList) {
            this.saveOrUpdateByFangId(dto, hotelSourceEnum);
        }

        return 1;
    }

    public int saveOrUpdateByFangId(RoomTypePriceDto roomTypePriceDto, HotelSourceEnum hotelSourceEnum) {

        if (null == roomTypePriceDto) {
            throw new MyException("-99", "-99", "roomTypePriceDto 不可为空");
        }

        //roomType
        RoomTypeDto roomTypeDto =
                this.roomTypeService.selectByFangId(
                        roomTypePriceDto.getFangHotelId(), roomTypePriceDto.getFangRoomTypeId(), hotelSourceEnum);
        if (null == roomTypeDto) {
            throw new MyException("-99", "-99", "roomTypePriceDto.getFangHotelId(), roomTypePriceDto.getFangRoomTypeId() 错误");
        }
        //
        RoomTypePriceExample roomTypePriceExample = new RoomTypePriceExample();
        roomTypePriceExample.createCriteria().andRoomTypeIdEqualTo(roomTypeDto.getId()).andDayEqualTo(roomTypePriceDto.getDay());
        List<RoomTypePrice> roomTypePriceList = this.roomTypePriceMapper.selectByExample(roomTypePriceExample);

        //redis
        this.updateRedisPrice(
                roomTypeDto.getId(), roomTypeDto.getName(),
                roomTypePriceDto.getDay(), roomTypePriceDto.getPrice(), roomTypePriceDto.getCost(),
                "RoomTypePriceService.saveOrUpdateByFangId");

        //db
        if (roomTypePriceList.isEmpty()) {
            RoomTypePrice roomTypePrice = new RoomTypePrice();
            roomTypePrice.setRoomTypeId(roomTypeDto.getId());
            roomTypePrice.setDay(roomTypePriceDto.getDay());
            roomTypePrice.setPrice(roomTypePriceDto.getPrice());
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

            dbRoomTypePrice.setUpdateDate(new Date());
            dbRoomTypePrice.setUpdateBy("hotel_system");
            return this.roomTypePriceMapper.updateByPrimaryKeySelective(dbRoomTypePrice);
        }
    }

    public void updateRedisPrice(Long roomTypeId, String roomTypeName, Date day, BigDecimal price, BigDecimal cost, String cacheFrom) {
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
            jedis = RedisUtil.getJedis();
            String priceHashName = RoomTypePriceCacheEnum.getPriceHashName(String.valueOf(roomTypeId));

            //
            com.mk.hotel.roomtype.redisbean.RoomTypePrice roomTypePrice = new com.mk.hotel.roomtype.redisbean.RoomTypePrice();
            roomTypePrice.setRoomTypeId(roomTypeId);
            roomTypePrice.setRoomTypeName(roomTypeName);
            roomTypePrice.setPrice(price);
            roomTypePrice.setOriginPrice(cost);
            roomTypePrice.setCacheTime(strDateTime);
            roomTypePrice.setCacheFrom(cacheFrom);

            //set
            jedis.hset(priceHashName, strDate, JsonUtils.toJson(roomTypePrice));

//            //
//            Long hotelId = roomTypeService.getHotelIdByRedis(roomTypeId);
//            if (null != hotelId) {
//                //
//                OtsInterface.initHotel(hotelId);
//            }
        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public List<RoomTypePriceDto> getRoomTypePrice(Long roomTypeId, Date fromDate, Date toDate){

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
}
