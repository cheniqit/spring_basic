package com.mk.hotel.remote.pms.hotel;

import com.mk.framework.DateUtils;
import com.mk.hotel.remote.pms.common.FbbCommonResponse;
import com.mk.hotel.remote.pms.hotel.json.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Created by chenqi on 16/5/11.
 */
public class HotelRemoteServiceTest {
    HotelRemoteService hotelRemoteService = new HotelRemoteService();

    @Test
    public void testQueryHotelList() throws Exception {
        HotelQueryListRequest hotelQueryListRequest = new HotelQueryListRequest();
        hotelQueryListRequest.setPage(1);
        hotelQueryListRequest.setPagesize(10);
        HotelQueryListResponse response = hotelRemoteService.queryHotelList(hotelQueryListRequest);
        Assert.assertNotNull(response.getData());
    }

    @Test
    public void testQueryHotelDetail() throws Exception {
        HotelQueryDetailRequest hotelQueryDetailRequest = new HotelQueryDetailRequest();
        hotelQueryDetailRequest.setHotelid("2807");
        hotelQueryDetailRequest.setChannelid("11");
        HotelQueryDetailResponse response = hotelRemoteService.queryHotelDetail(hotelQueryDetailRequest);
        Assert.assertNotNull(response.getData());
    }

    @Test
    public void testQueryRoomType() throws Exception {
        HotelRoomTypeQueryRequest hotelRoomTypeQueryRequest = new HotelRoomTypeQueryRequest();
        hotelRoomTypeQueryRequest.setHotelid("2807");
        hotelRoomTypeQueryRequest.setChannelid("11");
        HotelRoomTypeQueryResponse response = hotelRemoteService.queryRoomType(hotelRoomTypeQueryRequest);
        Assert.assertNotNull(response.getData());
    }

    @Test
    public void testQueryHotelPrice() throws Exception {
        HotelPriceRequest hotelPriceRequest = new HotelPriceRequest();
        hotelPriceRequest.setHotelid("2807");
        hotelPriceRequest.setChannelid("11");
        hotelPriceRequest.setBegintime(DateUtils.formatDate(new Date()));
        hotelPriceRequest.setEndtime(DateUtils.formatDate(DateUtils.addDays(new Date(), 1)));
        HotelPriceResponse response = hotelRemoteService.queryHotelPrice(hotelPriceRequest);
        Assert.assertNotNull(response.getData());
    }

    @Test
    public void testQueryHotelTags() throws Exception {
        HotelTagRequest request = new HotelTagRequest();
        request.setHotelid("2807");
        HotelTagResponse response = hotelRemoteService.queryHotelTags(request);
        Assert.assertNotNull(response);
    }
}