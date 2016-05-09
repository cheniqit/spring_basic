package com.mk.hotel.hotelinfo.mapper;

import java.util.List;

import com.mk.hotel.hotelinfo.model.HotelFacility;
import com.mk.hotel.hotelinfo.model.HotelFacilityExample;
import org.apache.ibatis.annotations.Param;

public interface HotelFacilityMapper {
    int countByExample(HotelFacilityExample example);

    int deleteByExample(HotelFacilityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HotelFacility record);

    int insertSelective(HotelFacility record);

    List<HotelFacility> selectByExample(HotelFacilityExample example);

    HotelFacility selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HotelFacility record, @Param("example") HotelFacilityExample example);

    int updateByExample(@Param("record") HotelFacility record, @Param("example") HotelFacilityExample example);

    int updateByPrimaryKeySelective(HotelFacility record);

    int updateByPrimaryKey(HotelFacility record);
}