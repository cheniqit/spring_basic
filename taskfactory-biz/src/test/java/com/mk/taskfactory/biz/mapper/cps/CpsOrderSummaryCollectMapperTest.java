package com.mk.taskfactory.biz.mapper.cps;

import com.common.BaseTest;
import com.mk.taskfactory.biz.cps.mapper.CpsOrderSummaryCollectMapper;
import com.mk.taskfactory.biz.cps.model.CpsOrderSummaryCollect;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

public class CpsOrderSummaryCollectMapperTest extends BaseTest {
    @Autowired
    private CpsOrderSummaryCollectMapper cpsOrderSummaryCollectMapper;

    @Test
    public void findByIdTest(){
        Long id = 1L;
        cpsOrderSummaryCollectMapper.selectByPrimaryKey(id);
    }

    @Test
    public  void insertTest(){
        CpsOrderSummaryCollect record = new CpsOrderSummaryCollect();
        record.setTotalprice(new BigDecimal("1"));
        record.setDeductrate(2D);
        record.setCreatetime(new Date());
        record.setCpsratefirst(3D);
        record.setCpsrate(4D);
        record.setSumorderpricerate(new BigDecimal("5"));
        record.setChannelcode("6");
        record.setChanneltype(7);
        record.setChannelname("8");
        record.setSumorder(9);
        record.setOrderdate(new Date());
        record.setRuleupdateby("10");
        record.setRuleupdatetime(new Date());
        record.setOrderdate(new Date());
        record.setSumorder(11);
        record.setSumorderfirst(12);
        record.setDeductratefirst(13D);
        record.setDeductrate(14D);
        record.setSumorderprice(new BigDecimal("15"));
        record.setSumorderpricefirst(new BigDecimal("16"));
        int insertNum = cpsOrderSummaryCollectMapper.insert(record);
        Assert.assertEquals(1, insertNum);
        Assert.assertNotNull(record.getId());
    }
}