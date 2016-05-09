package com.mk.hotel.hotelinfo.exception.mapper;

import com.mk.hotel.hotelinfo.exception.model.RoomTypeFacility;
import com.mk.hotel.hotelinfo.exception.model.RoomTypeFacilityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoomTypeFacilityMapper {
    int countByExample(RoomTypeFacilityExample example);

    int deleteByExample(RoomTypeFacilityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RoomTypeFacility record);

    int insertSelective(RoomTypeFacility record);

    List<RoomTypeFacility> selectByExample(RoomTypeFacilityExample example);

    RoomTypeFacility selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RoomTypeFacility record, @Param("example") RoomTypeFacilityExample example);

    int updateByExample(@Param("record") RoomTypeFacility record, @Param("example") RoomTypeFacilityExample example);

    int updateByPrimaryKeySelective(RoomTypeFacility record);

    int updateByPrimaryKey(RoomTypeFacility record);
}