package com.mk.hotel.hotelinfo.mapper;

import java.util.List;

import com.mk.hotel.hotelinfo.model.PicResource;
import com.mk.hotel.hotelinfo.model.PicResourceExample;
import org.apache.ibatis.annotations.Param;

public interface PicResourceMapper {
    int countByExample(PicResourceExample example);

    int deleteByExample(PicResourceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PicResource record);

    int insertSelective(PicResource record);

    List<PicResource> selectByExample(PicResourceExample example);

    PicResource selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PicResource record, @Param("example") PicResourceExample example);

    int updateByExample(@Param("record") PicResource record, @Param("example") PicResourceExample example);

    int updateByPrimaryKeySelective(PicResource record);

    int updateByPrimaryKey(PicResource record);
}