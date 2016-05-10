package com.mk.hotel.roomtype.mapper;

import com.mk.hotel.roomtype.model.RoomTypeStock;
import com.mk.hotel.roomtype.model.RoomTypeStockExample;
import java.util.List;

public interface RoomTypeStockMapper {
    int countByExample(RoomTypeStockExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RoomTypeStock record);

    int insertSelective(RoomTypeStock record);

    List<RoomTypeStock> selectByExample(RoomTypeStockExample example);

    RoomTypeStock selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoomTypeStock record);

    int updateByPrimaryKey(RoomTypeStock record);
}