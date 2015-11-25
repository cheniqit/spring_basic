package com.mk.taskfactory.biz.cps.mapper;

import com.mk.taskfactory.biz.cps.model.CpsOrderSummaryCollect;
import com.mk.taskfactory.biz.cps.model.CpsOrderSummaryCollectExample;
import com.mk.taskfactory.biz.repository.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface CpsOrderSummaryCollectMapper {
    int countByExample(CpsOrderSummaryCollectExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CpsOrderSummaryCollect record);

    int insertSelective(CpsOrderSummaryCollect record);

    List<CpsOrderSummaryCollect> selectByExample(CpsOrderSummaryCollectExample example);

    CpsOrderSummaryCollect selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CpsOrderSummaryCollect record);

    int updateByPrimaryKey(CpsOrderSummaryCollect record);
}