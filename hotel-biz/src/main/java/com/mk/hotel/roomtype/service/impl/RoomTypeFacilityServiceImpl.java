package com.mk.hotel.roomtype.service.impl;

import com.dianping.cat.Cat;
import com.mk.framework.JsonUtils;
import com.mk.framework.excepiton.MyException;
import com.mk.framework.proxy.http.RedisUtil;
import com.mk.hotel.common.redisbean.Facility;
import com.mk.hotel.roomtype.RoomTypeFacilityService;
import com.mk.hotel.roomtype.RoomTypeService;
import com.mk.hotel.roomtype.dto.RoomTypeDto;
import com.mk.hotel.roomtype.dto.RoomTypeFacilityDto;
import com.mk.hotel.roomtype.enums.RoomTypeFacilityCacheEnum;
import com.mk.hotel.roomtype.enums.RoomTypePriceCacheEnum;
import com.mk.hotel.roomtype.mapper.RoomTypeFacilityMapper;
import com.mk.hotel.roomtype.model.RoomTypeFacility;
import com.mk.hotel.roomtype.model.RoomTypeFacilityExample;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
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

    public void saveOrUpdateByFangid(List<RoomTypeFacilityDto> roomTypeFacilityDtoList) {
        if (null == roomTypeFacilityDtoList || roomTypeFacilityDtoList.isEmpty()) {
            throw new MyException("-99", "-99", "roomTypeFacilityDtoList 不可为空");
        }

        //判断队列中的房型id是否相同
        Long sameFangRoomTypeId = null;
        for (RoomTypeFacilityDto dto : roomTypeFacilityDtoList) {
            if(null == dto.getFangRoomTypeId()) {
                throw new MyException("-99", "-99", "FangRoomTypeId 不能为空");
            }

            if (null == sameFangRoomTypeId) {
                sameFangRoomTypeId = dto.getFangRoomTypeId();
            } else {
                if (!sameFangRoomTypeId.equals(dto.getFangRoomTypeId())) {
                    throw new MyException("-99", "-99", "FangRoomTypeId 有不相同的存在");
                }
            }

        }
        //
        RoomTypeFacilityDto roomTypeFacilityDto = roomTypeFacilityDtoList.get(0);
        Long fangHotelId = roomTypeFacilityDto.getFangHotelId();
        Long fangRoomTypeId = roomTypeFacilityDto.getFangRoomTypeId();

        //
        RoomTypeDto roomTypeDto = this.roomTypeService.selectByFangId(fangHotelId,fangRoomTypeId);
        if (null == roomTypeDto) {
            return;
        }

        //
        RoomTypeFacilityExample example = new RoomTypeFacilityExample();
        example.createCriteria().andRoomTypeIdEqualTo(roomTypeDto.getId());
        this.roomTypeFacilityMapper.deleteByExample(example);

        for (RoomTypeFacilityDto dto : roomTypeFacilityDtoList) {
            Long facilityId =  dto.getFacilityId();

            RoomTypeFacility facility = new RoomTypeFacility();
            facility.setRoomTypeId(roomTypeDto.getId());
            facility.setFacilityId(facilityId);
            facility.setIsValid("T");
            this.roomTypeFacilityMapper.insert(facility);
        }
    }


    public void updateRedisFacility(Long roomTypeId, List<RoomTypeFacilityDto> roomTypeFacilityDtoList, String cacheFrom) {
        if (null == roomTypeId || null == roomTypeFacilityDtoList || roomTypeFacilityDtoList.isEmpty()) {
            return;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strDate = format.format(new Date());

        //
        Jedis jedis = null;
        try {
            //
            jedis = RedisUtil.getJedis();
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
                facility.setFacName(null);
                facility.setFacType(null);
                facility.setCacheTime(strDate);
                facility.setCacheFrom(cacheFrom);
                //
                jedis.sadd(facilityKeyName, JsonUtils.toJson(facility));
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
