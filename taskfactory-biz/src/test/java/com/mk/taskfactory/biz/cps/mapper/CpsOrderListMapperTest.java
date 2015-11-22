package com.mk.taskfactory.biz.cps.mapper;

import com.common.BaseTest;
import com.mk.taskfactory.biz.cps.bean.CpsOrderListSummary;
import com.mk.taskfactory.biz.utils.DateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class CpsOrderListMapperTest extends BaseTest {

    @Autowired
    private CpsOrderListMapper cpsOrderListMapper;
    @Test
    public void testGetCpsOrderListSummary() throws Exception {
        boolean isFirst = true;
        Date payStartDate = DateUtils.parse("2015-11-19", "yyyy-MM-dd");
        Date payEndDate = DateUtils.parse("2015-11-20", "yyyy-MM-dd");
        String channelCode = "test1";
        CpsOrderListSummary cpsOrderListSummary = cpsOrderListMapper.getCpsOrderListSummary(isFirst, payStartDate, payEndDate , channelCode);
        Assert.assertNotNull(cpsOrderListSummary);
        Assert.assertNotNull(cpsOrderListSummary.getUpdateTime());
    }
}