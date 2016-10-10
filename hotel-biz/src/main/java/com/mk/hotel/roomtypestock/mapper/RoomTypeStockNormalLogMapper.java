package com.mk.hotel.roomtypestock.mapper;

import com.mk.hotel.roomtypestock.model.RoomTypeStockNormalLog;
import com.mk.hotel.roomtypestock.model.RoomTypeStockNormalLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoomTypeStockNormalLogMapper {
    int countByExample(RoomTypeStockNormalLogExample example);

    int deleteByExample(RoomTypeStockNormalLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RoomTypeStockNormalLog record);

    int insertSelective(RoomTypeStockNormalLog record);

    List<RoomTypeStockNormalLog> selectByExample(RoomTypeStockNormalLogExample example);

    RoomTypeStockNormalLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RoomTypeStockNormalLog record, @Param("example") RoomTypeStockNormalLogExample example);

    int updateByExample(@Param("record") RoomTypeStockNormalLog record, @Param("example") RoomTypeStockNormalLogExample example);

    int updateByPrimaryKeySelective(RoomTypeStockNormalLog record);

    int updateByPrimaryKey(RoomTypeStockNormalLog record);

    /**
     * 批量插入
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<RoomTypeStockNormalLog> list);
}