package com.mk.taskfactory.biz.mapper.cps;

import com.mk.taskfactory.biz.cps.model.CpsOrderList;
import com.mk.taskfactory.biz.cps.model.CpsOrderListExample;

import java.util.Date;
import java.util.List;

public interface CpsOrderListMapper {
    int countByExample(CpsOrderListExample example);

    int insert(CpsOrderList record);

    int insertSelective(CpsOrderList record);

    List<CpsOrderList> selectByExample(CpsOrderListExample example);

    int  addCpsOrderListBatch(List<CpsOrderList> cpsOrderList);

    public Date getMaxCheckOutTime();
}