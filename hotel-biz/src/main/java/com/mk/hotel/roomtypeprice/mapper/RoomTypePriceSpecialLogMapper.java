package com.mk.hotel.roomtypeprice.mapper;

import com.mk.hotel.roomtypeprice.model.RoomTypePriceSpecialLog;
import com.mk.hotel.roomtypeprice.model.RoomTypePriceSpecialLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoomTypePriceSpecialLogMapper {
    int countByExample(RoomTypePriceSpecialLogExample example);

    int deleteByExample(RoomTypePriceSpecialLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RoomTypePriceSpecialLog record);

    int insertSelective(RoomTypePriceSpecialLog record);

    List<RoomTypePriceSpecialLog> selectByExample(RoomTypePriceSpecialLogExample example);

    RoomTypePriceSpecialLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RoomTypePriceSpecialLog record, @Param("example") RoomTypePriceSpecialLogExample example);

    int updateByExample(@Param("record") RoomTypePriceSpecialLog record, @Param("example") RoomTypePriceSpecialLogExample example);

    int updateByPrimaryKeySelective(RoomTypePriceSpecialLog record);

    int updateByPrimaryKey(RoomTypePriceSpecialLog record);
}