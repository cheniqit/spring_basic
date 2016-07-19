package com.mk.hotel.remote.fanqielaile.hotel;

import com.mk.framework.DateUtils;
import org.apache.commons.httpclient.util.DateUtil;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by chenqi on 16/7/18.
 */
public class FanqielaileRemoteServiceTest {
    FanqielaileRemoteService fanqielaileRemoteService = new FanqielaileRemoteService();

    @Test
    public void testQueryRoomStatus() throws Exception {
        fanqielaileRemoteService.queryRoomStatus(44895L, DateUtils.addDays(new Date(), 0), DateUtils.addDays(new Date(), 1));
    }
}