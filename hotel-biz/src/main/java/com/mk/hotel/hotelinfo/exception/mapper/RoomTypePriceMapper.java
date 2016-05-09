package com.mk.hotel.hotelinfo.exception.mapper;

import com.mk.hotel.hotelinfo.exception.model.RoomTypePrice;
import com.mk.hotel.hotelinfo.exception.model.RoomTypePriceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoomTypePriceMapper {
    int countByExample(RoomTypePriceExample example);

    int deleteByExample(RoomTypePriceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RoomTypePrice record);

    int insertSelective(RoomTypePrice record);

    List<RoomTypePrice> selectByExample(RoomTypePriceExample example);

    RoomTypePrice selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RoomTypePrice record, @Param("example") RoomTypePriceExample example);

    int updateByExample(@Param("record") RoomTypePrice record, @Param("example") RoomTypePriceExample example);

    int updateByPrimaryKeySelective(RoomTypePrice record);

    int updateByPrimaryKey(RoomTypePrice record);
}