package com.mk.hotel.roomtypeprice.mapper;

import com.mk.hotel.roomtypeprice.model.RoomTypePriceNormalLog;
import com.mk.hotel.roomtypeprice.model.RoomTypePriceNormalLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoomTypePriceNormalLogMapper  {
    int countByExample(RoomTypePriceNormalLogExample example);

    int deleteByExample(RoomTypePriceNormalLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RoomTypePriceNormalLog record);

    int insertSelective(RoomTypePriceNormalLog record);

    List<RoomTypePriceNormalLog> selectByExample(RoomTypePriceNormalLogExample example);

    RoomTypePriceNormalLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RoomTypePriceNormalLog record, @Param("example") RoomTypePriceNormalLogExample example);

    int updateByExample(@Param("record") RoomTypePriceNormalLog record, @Param("example") RoomTypePriceNormalLogExample example);

    int updateByPrimaryKeySelective(RoomTypePriceNormalLog record);

    int updateByPrimaryKey(RoomTypePriceNormalLog record);

    /**
     * 批量插入
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<RoomTypePriceNormalLog> list);
}