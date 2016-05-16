package com.mk.hotel.hotelinfo.service.impl;

import com.dianping.cat.Cat;
import com.mk.framework.JsonUtils;
import com.mk.framework.excepiton.MyException;
import com.mk.framework.proxy.http.RedisUtil;
import com.mk.hotel.common.redisbean.Facility;
import com.mk.hotel.hotelinfo.HotelFacilityService;
import com.mk.hotel.hotelinfo.HotelService;
import com.mk.hotel.hotelinfo.dto.HotelDto;
import com.mk.hotel.hotelinfo.dto.HotelFacilityDto;
import com.mk.hotel.hotelinfo.enums.HotelFacilityCacheEnum;
import com.mk.hotel.hotelinfo.mapper.HotelFacilityMapper;
import com.mk.hotel.hotelinfo.model.HotelFacility;
import com.mk.hotel.hotelinfo.model.HotelFacilityExample;
import com.mk.hotel.roomtype.dto.RoomTypeFacilityDto;
import com.mk.hotel.roomtype.enums.RoomTypeFacilityCacheEnum;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class HotelFacilityServiceImpl implements HotelFacilityService {
    @Autowired
    private HotelFacilityMapper hotelFacilityMapper;

    @Autowired
    private HotelService hotelService;

    public void saveOrUpdateByFangId (List<HotelFacilityDto> hotelFacilityDtoList) {
        if (null == hotelFacilityDtoList || hotelFacilityDtoList.isEmpty()) {
            throw new MyException("-99", "-99", "hotelFacilityDtoList 不可为空");
        }

        HotelFacilityDto hotelFacilityDto = hotelFacilityDtoList.get(0);
        //
        HotelDto hotelDto = this.hotelService.findByFangId(hotelFacilityDto.getFangHotelId());
        if (null == hotelDto) {
            return;
        }

        //redis
        this.updateRedisFacility(hotelDto.getId(), hotelFacilityDtoList, "HotelFacilityService.saveOrUpdateByFangId");

        //db
        HotelFacilityExample example = new HotelFacilityExample();
        example.createCriteria().andHotelIdEqualTo(hotelDto.getId());
        this.hotelFacilityMapper.deleteByExample(example);

        for (HotelFacilityDto dto : hotelFacilityDtoList) {
            dto.setHotelId(hotelDto.getId());
            dto.setIsValid("T");

            HotelFacility facility = new HotelFacility();
            BeanUtils.copyProperties(dto, facility);
            this.hotelFacilityMapper.insert(facility);
        }
    }

    public void updateRedisFacility(Long hotelId, List<HotelFacilityDto> hotelFacilityDtoList, String cacheFrom) {
        if (null == hotelId || null == hotelFacilityDtoList || hotelFacilityDtoList.isEmpty()) {
            return;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = format.format(new Date());

        //
        Jedis jedis = null;
        try {
            //
            jedis = RedisUtil.getJedis();
            String facilityKeyName = HotelFacilityCacheEnum.getFacilityKeyName(String.valueOf(hotelId));

            //redis rem
            Set<String> facilityList =  jedis.smembers(facilityKeyName);
            for (String facilityJson : facilityList) {
                jedis.srem(facilityKeyName,facilityJson);
            }

            //redis add
            for (HotelFacilityDto dto : hotelFacilityDtoList) {
                Facility facility = new Facility();
                facility.setFacId(dto.getId());
                facility.setFacName(dto.getFacilityName());
                facility.setFacType(dto.getFacilityType());
                facility.setCacheTime(strDate);
                facility.setCacheFrom(cacheFrom);
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
