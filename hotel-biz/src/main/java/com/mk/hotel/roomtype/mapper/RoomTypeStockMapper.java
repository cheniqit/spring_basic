package com.mk.hotel.roomtype.mapper;

import com.mk.hotel.roomtype.model.RoomTypeStock;
import com.mk.hotel.roomtype.model.RoomTypeStockExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoomTypeStockMapper {
    int countByExample(RoomTypeStockExample example);

    int deleteByExample(RoomTypeStockExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RoomTypeStock record);

    int insertSelective(RoomTypeStock record);

    List<RoomTypeStock> selectByExample(RoomTypeStockExample example);

    RoomTypeStock selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RoomTypeStock record, @Param("example") RoomTypeStockExample example);

    int updateByExample(@Param("record") RoomTypeStock record, @Param("example") RoomTypeStockExample example);

    int updateByPrimaryKeySelective(RoomTypeStock record);

    int updateByPrimaryKey(RoomTypeStock record);
}