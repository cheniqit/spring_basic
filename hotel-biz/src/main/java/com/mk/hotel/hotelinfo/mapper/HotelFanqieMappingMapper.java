package com.mk.hotel.hotelinfo.mapper;

import com.mk.hotel.hotelinfo.model.HotelFanqieMapping;
import com.mk.hotel.hotelinfo.model.HotelFanqieMappingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HotelFanqieMappingMapper {
    int countByExample(HotelFanqieMappingExample example);

    int deleteByExample(HotelFanqieMappingExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HotelFanqieMapping record);

    int insertSelective(HotelFanqieMapping record);

    List<HotelFanqieMapping> selectByExample(HotelFanqieMappingExample example);

    HotelFanqieMapping selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HotelFanqieMapping record, @Param("example") HotelFanqieMappingExample example);

    int updateByExample(@Param("record") HotelFanqieMapping record, @Param("example") HotelFanqieMappingExample example);

    int updateByPrimaryKeySelective(HotelFanqieMapping record);

    int updateByPrimaryKey(HotelFanqieMapping record);
}