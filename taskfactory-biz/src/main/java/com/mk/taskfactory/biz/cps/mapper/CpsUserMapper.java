package com.mk.taskfactory.biz.cps.mapper;

import com.mk.taskfactory.biz.cps.model.CpsUser;
import com.mk.taskfactory.biz.cps.model.CpsUserExample;
import java.util.List;

public interface CpsUserMapper {
    int countByExample(CpsUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CpsUser record);

    int insertSelective(CpsUser record);

    List<CpsUser> selectByExample(CpsUserExample example);

    CpsUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CpsUser record);

    int updateByPrimaryKey(CpsUser record);
}