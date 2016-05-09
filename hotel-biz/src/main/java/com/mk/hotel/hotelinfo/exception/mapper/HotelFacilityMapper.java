package com.mk.hotel.hotelinfo.exception.mapper;

import com.mk.hotel.hotelinfo.exception.model.HotelFacility;
import com.mk.hotel.hotelinfo.exception.model.HotelFacilityExample;
import java.util.List;
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