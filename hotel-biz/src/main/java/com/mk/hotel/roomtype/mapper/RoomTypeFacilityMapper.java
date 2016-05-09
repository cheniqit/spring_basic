package com.mk.hotel.roomtype.mapper;

import java.util.List;

import com.mk.hotel.roomtype.model.RoomTypeFacility;
import com.mk.hotel.roomtype.model.RoomTypeFacilityExample;
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