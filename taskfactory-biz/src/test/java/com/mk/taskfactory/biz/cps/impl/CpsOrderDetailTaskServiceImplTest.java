package com.mk.taskfactory.biz.cps.impl;

import com.common.BaseTest;
import com.mk.taskfactory.biz.cps.model.CpsChannel;
import com.mk.taskfactory.biz.cps.model.CpsOrderList;
import com.mk.taskfactory.biz.cps.model.CpsRateConfig;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CpsOrderDetailTaskServiceImplTest extends BaseTest {
    @Autowired
    private CpsOrderDetailTaskServiceImpl cpsOrderDetailTaskService;

    @Test
    public void testSaveOrderSummary() throws Exception {
        cpsOrderDetailTaskService.saveOrderSummary();
    }

    @Test
    public void testSaveOrderSummaryByChannelCode() throws Exception {
        String channelCode  = "test1";
        cpsOrderDetailTaskService.saveOrderSummaryByChannelCode(channelCode);
    }

    @Test
    public void testGetCpsOrderListByResultIsNull() throws Exception {
        String channelCode  = "test1--------------";
        String isNew = "1";
        List<CpsOrderList> cpsOrderList = cpsOrderDetailTaskService.getCpsOrderList(channelCode, isNew);
        Assert.assertEquals(0, cpsOrderList.size());
    }

    @Test@Transactional
    public void testGetCpsOrderListByResultIsNotNull() throws Exception {
        String channelCode  = "test1";
        String isNew = "1";
        List<CpsOrderList> cpsOrderList = cpsOrderDetailTaskService.getCpsOrderList(channelCode, isNew);
        Assert.assertNotNull(cpsOrderList);
        Assert.assertEquals(2, cpsOrderList.size());
    }

    @Test
    public void testGetCpsChannelByCode() throws Exception {
        CpsChannel cpsChannel1 = cpsOrderDetailTaskService.getCpsChannelByCode("test4-------------");
        Assert.assertNull(cpsChannel1);
        CpsChannel cpsChannel = cpsOrderDetailTaskService.getCpsChannelByCode("test4");
        Assert.assertNotNull(cpsChannel);
        Assert.assertEquals("名称4",cpsChannel.getChannelname());
    }

    @Test
    public void testGetCpsRateConfigByResultOne() throws Exception {
        Integer channelId = 2;
        List<CpsRateConfig>  cpsRateConfigs  = cpsOrderDetailTaskService.getCpsRateConfig(channelId);
        Assert.assertNotNull(cpsRateConfigs);
        Assert.assertEquals(1, cpsRateConfigs.size());
        Assert.assertEquals(new Integer(1), cpsRateConfigs.get(0).getId());
    }

    @Test
    public void testGetCpsRateConfigByResultMore() throws Exception {
        Integer channelId = 1;
        List<CpsRateConfig>  cpsRateConfigs  = cpsOrderDetailTaskService.getCpsRateConfig(channelId);
        Assert.assertNotNull(cpsRateConfigs);
        Assert.assertEquals(5, cpsRateConfigs.size());
        Assert.assertEquals(22, cpsRateConfigs.get(0).getFirstcpsrate().intValue());
    }
}