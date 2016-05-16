package com.mk.ots.mapper;

import java.util.List;

import com.mk.ots.model.LandMark;
import com.mk.ots.model.LandMarkExample;
import org.apache.ibatis.annotations.Param;

public interface LandMarkMapper {
    int countByExample(LandMarkExample example);

    int deleteByExample(LandMarkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LandMark record);

    int insertSelective(LandMark record);

    List<LandMark> selectByExample(LandMarkExample example);

    LandMark selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LandMark record, @Param("example") LandMarkExample example);

    int updateByExample(@Param("record") LandMark record, @Param("example") LandMarkExample example);

    int updateByPrimaryKeySelective(LandMark record);

    int updateByPrimaryKey(LandMark record);
}