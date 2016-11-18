package com.mk.hotel.roomtype.service.impl;

import com.mk.common.BaseTest;
import com.mk.framework.date.DateUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by chenqi on 16/11/18.
 */
public class RoomTypePriceServiceImplTest extends BaseTest{
@Autowired
private RoomTypePriceServiceImpl roomTypePriceService;
    @Test
    public void testQueryFromRedis() throws Exception {

        Date now = new Date();
        Date[] dates = DateUtils.getStartEndDate(now, now);
        Date[] dates1 = DateUtils.getStartEndDate(now, DateUtils.addDays(now,2));
        roomTypePriceService.queryFromRedis(31919L, now, DateUtils.addDays(now, 0));
    }
}