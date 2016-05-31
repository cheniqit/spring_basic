package com.mk.hotel.hotelinfo.mapper;

import com.mk.hotel.hotelinfo.model.HotelPic;
import com.mk.hotel.hotelinfo.model.HotelPicExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HotelPicMapper {
    int countByExample(HotelPicExample example);

    int deleteByExample(HotelPicExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HotelPic record);

    int insertSelective(HotelPic record);

    List<HotelPic> selectByExample(HotelPicExample example);

    HotelPic selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HotelPic record, @Param("example") HotelPicExample example);

    int updateByExample(@Param("record") HotelPic record, @Param("example") HotelPicExample example);

    int updateByPrimaryKeySelective(HotelPic record);

    int updateByPrimaryKey(HotelPic record);
}