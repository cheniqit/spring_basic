package com.mk.taskfactory.biz.mapper.ots;

import com.mk.taskfactory.model.Town;
import com.mk.taskfactory.model.TownExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TownMapper {
    int countByExample(TownExample example);

    int deleteByExample(TownExample example);

    int insert(Town record);

    int insertSelective(Town record);

    List<Town> selectByExample(TownExample example);

    int updateByExampleSelective(@Param("record") Town record, @Param("example") TownExample example);

    int updateByExample(@Param("record") Town record, @Param("example") TownExample example);
}