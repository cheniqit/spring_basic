package com.mk.hotel.remote.pms.hotelstock;

import com.mk.framework.DateUtils;
import com.mk.framework.net.PmsAuthHeader;
import com.mk.hotel.remote.pms.common.FbbCommonResponse;
import com.mk.hotel.remote.pms.hotelstock.json.QueryStockRequest;
import com.mk.hotel.remote.pms.hotelstock.json.QueryStockResponse;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Created by chenqi on 16/5/11.
 */
public class HotelStockRemoteServiceTest {
    HotelStockRemoteService hotelStockRemoteService = new HotelStockRemoteService();
    @Test
    public void testQueryStock() throws Exception {
        QueryStockRequest queryStockRequest = new QueryStockRequest();
        PmsAuthHeader pmsAuthHeader = new PmsAuthHeader();
        queryStockRequest.setChannelid(pmsAuthHeader.getChannelId());
        queryStockRequest.setHotelid("2807");
        queryStockRequest.setBegintime(DateUtils.formatDateTime(new Date(), DateUtils.FORMAT_DATE));
        queryStockRequest.setEndtime(DateUtils.formatDate(DateUtils.addDays(new Date(), 5)));
        FbbCommonResponse response = hotelStockRemoteService.queryStock(queryStockRequest);
        Assert.assertNotNull(response);
    }

    @Test
    public void testQueryDatStock() throws Exception {
        QueryStockRequest queryStockRequest = new QueryStockRequest();
        queryStockRequest.setHotelid("2807");
        queryStockRequest.setBegintime(DateUtils.formatDateTime(new Date(), DateUtils.FORMAT_DATE));
        queryStockRequest.setEndtime(DateUtils.formatDate(DateUtils.addDays(new Date(), 1)));
        QueryStockResponse response = hotelStockRemoteService.queryDatStock(queryStockRequest);
        Assert.assertNotNull(response);
    }
}