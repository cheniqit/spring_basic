package com.mk.hotel.roomtypestock.mapper;

import com.mk.hotel.roomtypestock.model.RoomTypeStockSpecialLog;
import com.mk.hotel.roomtypestock.model.RoomTypeStockSpecialLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoomTypeStockSpecialLogMapper {
    int countByExample(RoomTypeStockSpecialLogExample example);

    int deleteByExample(RoomTypeStockSpecialLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RoomTypeStockSpecialLog record);

    int insertSelective(RoomTypeStockSpecialLog record);

    List<RoomTypeStockSpecialLog> selectByExample(RoomTypeStockSpecialLogExample example);

    RoomTypeStockSpecialLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RoomTypeStockSpecialLog record, @Param("example") RoomTypeStockSpecialLogExample example);

    int updateByExample(@Param("record") RoomTypeStockSpecialLog record, @Param("example") RoomTypeStockSpecialLogExample example);

    int updateByPrimaryKeySelective(RoomTypeStockSpecialLog record);

    int updateByPrimaryKey(RoomTypeStockSpecialLog record);

    /**
     * 批量插入
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<RoomTypeStockSpecialLog> list);
}