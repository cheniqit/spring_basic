package com.mk.taskfactory.biz.mapper.cps;

import com.common.BaseTest;
import com.mk.taskfactory.biz.cps.mapper.CpsOrderSummaryCollectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CpsOrderSummaryCollectMapperTest extends BaseTest {
    @Autowired
    private CpsOrderSummaryCollectMapper cpsOrderSummaryCollectMapper;

    @Test
    public void findByIdTest(){
        Long id = 1L;
        cpsOrderSummaryCollectMapper.selectByPrimaryKey(id);
    }
}