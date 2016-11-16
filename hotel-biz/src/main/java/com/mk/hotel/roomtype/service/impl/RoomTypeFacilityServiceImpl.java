package com.mk.hotel.roomtype.service.impl;

import com.dianping.cat.Cat;
import com.mk.framework.excepiton.MyErrorEnum;
import com.mk.framework.json.JsonUtils;
import com.mk.framework.redis.MkJedisConnectionFactory;
import com.mk.hotel.common.redisbean.Facility;
import com.mk.hotel.hotelinfo.enums.HotelSourceEnum;
import com.mk.hotel.roomtype.RoomTypeFacilityService;
import com.mk.hotel.roomtype.RoomTypeService;
import com.mk.hotel.roomtype.dto.RoomTypeDto;
import com.mk.hotel.roomtype.dto.RoomTypeFacilityDto;
import com.mk.hotel.roomtype.enums.RoomTypeFacilityCacheEnum;
import com.mk.hotel.roomtype.mapper.RoomTypeFacilityMapper;
import com.mk.hotel.roomtype.model.RoomTypeFacility;
import com.mk.hotel.roomtype.model.RoomTypeFacilityExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class RoomTypeFacilityServiceImpl implements RoomTypeFacilityService {

    @Autowired
    private RoomTypeService roomTypeService;

    @Autowired
    private RoomTypeFacilityMapper roomTypeFacilityMapper;

    @Autowired
    private MkJedisConnectionFactory jedisConnectionFactory;

    public void saveOrUpdateByFangid(List<RoomTypeFacilityDto> roomTypeFacilityDtoList, HotelSourceEnum hotelSourceEnum) {
        if (null == roomTypeFacilityDtoList || roomTypeFacilityDtoList.isEmpty()) {
            throw MyErrorEnum.ROOM_TYPE_FACILITY_DTO_LIST_IS_NULL.getMyException();
        }

        //判断队列中的房型id是否相同
        Long sameFangRoomTypeId = null;
        for (RoomTypeFacilityDto dto : roomTypeFacilityDtoList) {
            if(null == dto.getFangRoomTypeId()) {
                throw MyErrorEnum.ROOM_FANG_ID_IS_NULL.getMyException();
            }

            if (null == sameFangRoomTypeId) {
                sameFangRoomTypeId = dto.getFangRoomTypeId();
            } else {
                if (!sameFangRoomTypeId.equals(dto.getFangRoomTypeId())) {
                    throw MyErrorEnum.ROOM_MUTLI_FANG_ID.getMyException();
                }
            }

        }
        //
        RoomTypeFacilityDto roomTypeFacilityDto = roomTypeFacilityDtoList.get(0);
        Long fangHotelId = roomTypeFacilityDto.getFangHotelId();
        Long fangRoomTypeId = roomTypeFacilityDto.getFangRoomTypeId();

        //
        RoomTypeDto roomTypeDto = this.roomTypeService.selectByFangId(fangHotelId,fangRoomTypeId, hotelSourceEnum);
        if (null == roomTypeDto) {
            throw MyErrorEnum.ROOM_PARAMS_ERROR.getMyException();
        }

        //redis
        this.updateRedisFacility(roomTypeDto.getId(), roomTypeFacilityDtoList, "RoomTypeFacilityService.saveOrUpdateByFangid");

        //db
        RoomTypeFacilityExample example = new RoomTypeFacilityExample();
        example.createCriteria().andRoomTypeIdEqualTo(roomTypeDto.getId());
        this.roomTypeFacilityMapper.deleteByExample(example);

        for (RoomTypeFacilityDto dto : roomTypeFacilityDtoList) {
            Long facilityId =  dto.getFacilityId();

            RoomTypeFacility facility = new RoomTypeFacility();
            facility.setRoomTypeId(roomTypeDto.getId());
            facility.setFacilityId(facilityId);
            facility.setIsValid("T");
            facility.setCreateBy("hotel_system");
            facility.setCreateDate(new Date());
            facility.setUpdateBy("hotel_system");
            facility.setUpdateDate(new Date());
            this.roomTypeFacilityMapper.insert(facility);
        }
    }


    public void updateRedisFacility(Long roomTypeId, List<RoomTypeFacilityDto> roomTypeFacilityDtoList, String cacheFrom) {
        if (null == roomTypeId || null == roomTypeFacilityDtoList || roomTypeFacilityDtoList.isEmpty()) {
            return;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = format.format(new Date());

        //
        Jedis jedis = null;
        try {
            //
            jedis = jedisConnectionFactory.getJedis();
            String facilityKeyName = RoomTypeFacilityCacheEnum.getFacilityKeyName(String.valueOf(roomTypeId));

            //redis rem
            Set<String> facilityList = jedis.smembers(facilityKeyName);
            for (String facilityJson : facilityList) {
                jedis.srem(facilityKeyName, facilityJson);
            }

            //redis add
            for (RoomTypeFacilityDto dto : roomTypeFacilityDtoList) {
                Long facId = dto.getFacilityId();

                //
                Facility facility = new Facility();
                facility.setFacId(facId);
                facility.setFacName(dto.getFacilityName());
                facility.setFacType(dto.getFacilityType());
                facility.setCacheTime(strDate);
                facility.setCacheFrom(cacheFrom);
                //
                jedis.sadd(facilityKeyName, JsonUtils.toJson(facility));
            }

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
}
