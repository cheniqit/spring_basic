package com.mk.taskfactory.biz.cps.mapper;

import com.common.BaseCpsTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CpsOrderListMapperTest extends BaseCpsTest {

    @Autowired
    private CpsOrderListMapper cpsOrderListMapper;

    @Test
    public void testUpdateSummaryDetailId() throws Exception {
        String cpsChannel = "00002";
        Long sid = 1L;
        cpsOrderListMapper.updateSummaryDetailId(cpsChannel, sid);
    }
}