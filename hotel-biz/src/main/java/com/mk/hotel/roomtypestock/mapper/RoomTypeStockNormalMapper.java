package com.mk.hotel.roomtypestock.mapper;

import com.mk.hotel.roomtypestock.model.RoomTypeStockNormal;
import com.mk.hotel.roomtypestock.model.RoomTypeStockNormalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoomTypeStockNormalMapper {
    int countByExample(RoomTypeStockNormalExample example);

    int deleteByExample(RoomTypeStockNormalExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RoomTypeStockNormal record);

    int insertSelective(RoomTypeStockNormal record);

    List<RoomTypeStockNormal> selectByExample(RoomTypeStockNormalExample example);

    RoomTypeStockNormal selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RoomTypeStockNormal record, @Param("example") RoomTypeStockNormalExample example);

    int updateByExample(@Param("record") RoomTypeStockNormal record, @Param("example") RoomTypeStockNormalExample example);

    int updateByPrimaryKeySelective(RoomTypeStockNormal record);

    int updateByPrimaryKey(RoomTypeStockNormal record);
}