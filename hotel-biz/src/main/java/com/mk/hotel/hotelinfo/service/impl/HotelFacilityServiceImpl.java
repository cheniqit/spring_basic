package com.mk.hotel.hotelinfo.service.impl;

import com.mk.framework.excepiton.MyException;
import com.mk.hotel.hotelinfo.HotelFacilityService;
import com.mk.hotel.hotelinfo.HotelService;
import com.mk.hotel.hotelinfo.dto.HotelDto;
import com.mk.hotel.hotelinfo.dto.HotelFacilityDto;
import com.mk.hotel.hotelinfo.mapper.HotelFacilityMapper;
import com.mk.hotel.hotelinfo.model.HotelFacility;
import com.mk.hotel.hotelinfo.model.HotelFacilityExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

        //
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
}
