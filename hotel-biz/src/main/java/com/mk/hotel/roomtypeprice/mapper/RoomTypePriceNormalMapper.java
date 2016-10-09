package com.mk.hotel.roomtypeprice.mapper;

import com.mk.hotel.roomtypeprice.model.RoomTypePriceNormal;
import com.mk.hotel.roomtypeprice.model.RoomTypePriceNormalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoomTypePriceNormalMapper {
    int countByExample(RoomTypePriceNormalExample example);

    int deleteByExample(RoomTypePriceNormalExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RoomTypePriceNormal record);

    int insertSelective(RoomTypePriceNormal record);

    List<RoomTypePriceNormal> selectByExample(RoomTypePriceNormalExample example);

    RoomTypePriceNormal selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RoomTypePriceNormal record, @Param("example") RoomTypePriceNormalExample example);

    int updateByExample(@Param("record") RoomTypePriceNormal record, @Param("example") RoomTypePriceNormalExample example);

    int updateByPrimaryKeySelective(RoomTypePriceNormal record);

    int updateByPrimaryKey(RoomTypePriceNormal record);
}