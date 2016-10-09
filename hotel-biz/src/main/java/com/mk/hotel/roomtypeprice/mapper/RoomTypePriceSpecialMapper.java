package com.mk.hotel.roomtypeprice.mapper;

import com.mk.hotel.roomtypeprice.model.RoomTypePriceSpecial;
import com.mk.hotel.roomtypeprice.model.RoomTypePriceSpecialExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoomTypePriceSpecialMapper {
    int countByExample(RoomTypePriceSpecialExample example);

    int deleteByExample(RoomTypePriceSpecialExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RoomTypePriceSpecial record);

    int insertSelective(RoomTypePriceSpecial record);

    List<RoomTypePriceSpecial> selectByExample(RoomTypePriceSpecialExample example);

    RoomTypePriceSpecial selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RoomTypePriceSpecial record, @Param("example") RoomTypePriceSpecialExample example);

    int updateByExample(@Param("record") RoomTypePriceSpecial record, @Param("example") RoomTypePriceSpecialExample example);

    int updateByPrimaryKeySelective(RoomTypePriceSpecial record);

    int updateByPrimaryKey(RoomTypePriceSpecial record);
}