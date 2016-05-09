package com.mk.hotel.hotelinfo.mapper;

import java.util.List;

import com.mk.hotel.hotelinfo.model.RoomType;
import com.mk.hotel.hotelinfo.model.RoomTypeExample;
import org.apache.ibatis.annotations.Param;

public interface RoomTypeMapper {
    int countByExample(RoomTypeExample example);

    int deleteByExample(RoomTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RoomType record);

    int insertSelective(RoomType record);

    List<RoomType> selectByExample(RoomTypeExample example);

    RoomType selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RoomType record, @Param("example") RoomTypeExample example);

    int updateByExample(@Param("record") RoomType record, @Param("example") RoomTypeExample example);

    int updateByPrimaryKeySelective(RoomType record);

    int updateByPrimaryKey(RoomType record);
}