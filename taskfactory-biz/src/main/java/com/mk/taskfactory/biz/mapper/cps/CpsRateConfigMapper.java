package com.mk.taskfactory.biz.mapper.cps;

import com.mk.taskfactory.biz.cps.model.CpsRateConfig;
import com.mk.taskfactory.biz.cps.model.CpsRateConfigExample;
import java.util.List;

public interface CpsRateConfigMapper {
    int countByExample(CpsRateConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CpsRateConfig record);

    int insertSelective(CpsRateConfig record);

    List<CpsRateConfig> selectByExample(CpsRateConfigExample example);

    CpsRateConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CpsRateConfig record);

    int updateByPrimaryKey(CpsRateConfig record);
}