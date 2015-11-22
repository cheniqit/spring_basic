package com.mk.taskfactory.biz.cps.mapper;

import com.common.BaseTest;
import com.mk.taskfactory.biz.cps.model.CpsRateConfig;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CpsRateConfigMapperTest extends BaseTest {

    @Autowired
    private  CpsRateConfigMapper cpsRateConfigMapper;

    @Test
    public void selectByPrimaryKeyTest() throws Exception {
        CpsRateConfig cpsRateConfig = cpsRateConfigMapper.selectByPrimaryKey(2);
        Assert.assertNotNull(cpsRateConfig);
        Assert.assertEquals(new Integer(1), cpsRateConfig.getChannelid());
    }
}