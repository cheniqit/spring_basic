package com.mk.taskfactory.biz.cps.mapper;

import com.common.BaseCpsTest;
import com.mk.taskfactory.biz.cps.model.CpsRateConfig;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CpsRateConfigMapperCpsTest extends BaseCpsTest {

    @Autowired
    private  CpsRateConfigMapper cpsRateConfigMapper;

    @Test
    public void selectByPrimaryKeyTest() throws Exception {
        CpsRateConfig cpsRateConfig = cpsRateConfigMapper.selectByPrimaryKey(2);
        Assert.assertNotNull(cpsRateConfig);
        Assert.assertEquals(new Integer(1), cpsRateConfig.getChannelid());
    }
}