package com.mk.taskfactory.biz.mapper.cps;

import com.mk.taskfactory.biz.cps.model.CpsChannel;
import com.mk.taskfactory.biz.cps.model.CpsChannelExample;
import com.mk.taskfactory.biz.repository.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface CpsChannelMapper {
    int countByExample(CpsChannelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CpsChannel record);

    int insertSelective(CpsChannel record);

    List<CpsChannel> selectByExample(CpsChannelExample example);

    CpsChannel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CpsChannel record);

    int updateByPrimaryKey(CpsChannel record);
}