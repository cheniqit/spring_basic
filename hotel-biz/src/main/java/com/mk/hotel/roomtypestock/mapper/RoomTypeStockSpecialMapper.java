package com.mk.hotel.roomtypestock.mapper;

import com.mk.hotel.roomtypestock.model.RoomTypeStockSpecial;
import com.mk.hotel.roomtypestock.model.RoomTypeStockSpecialExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoomTypeStockSpecialMapper {
    int countByExample(RoomTypeStockSpecialExample example);

    int deleteByExample(RoomTypeStockSpecialExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RoomTypeStockSpecial record);

    int insertSelective(RoomTypeStockSpecial record);

    List<RoomTypeStockSpecial> selectByExample(RoomTypeStockSpecialExample example);

    RoomTypeStockSpecial selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RoomTypeStockSpecial record, @Param("example") RoomTypeStockSpecialExample example);

    int updateByExample(@Param("record") RoomTypeStockSpecial record, @Param("example") RoomTypeStockSpecialExample example);

    int updateByPrimaryKeySelective(RoomTypeStockSpecial record);

    int updateByPrimaryKey(RoomTypeStockSpecial record);

    /**
     * 批量插入
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<RoomTypeStockSpecial> list);
}