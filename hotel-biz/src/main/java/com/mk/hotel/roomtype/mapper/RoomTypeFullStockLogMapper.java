package com.mk.hotel.roomtype.mapper;

import com.mk.hotel.roomtype.model.RoomTypeFullStockLog;
import com.mk.hotel.roomtype.model.RoomTypeFullStockLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoomTypeFullStockLogMapper {
    int countByExample(RoomTypeFullStockLogExample example);

    int deleteByExample(RoomTypeFullStockLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RoomTypeFullStockLog record);

    int insertSelective(RoomTypeFullStockLog record);

    List<RoomTypeFullStockLog> selectByExample(RoomTypeFullStockLogExample example);

    RoomTypeFullStockLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RoomTypeFullStockLog record, @Param("example") RoomTypeFullStockLogExample example);

    int updateByExample(@Param("record") RoomTypeFullStockLog record, @Param("example") RoomTypeFullStockLogExample example);

    int updateByPrimaryKeySelective(RoomTypeFullStockLog record);

    int updateByPrimaryKey(RoomTypeFullStockLog record);
}