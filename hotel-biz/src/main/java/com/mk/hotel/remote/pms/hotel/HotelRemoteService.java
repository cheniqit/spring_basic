package com.mk.hotel.remote.pms.hotel;

import com.mk.framework.Constant;
import com.mk.framework.FbbRequestHead;
import com.mk.framework.HttpUtils;
import com.mk.framework.JsonUtils;
import com.mk.hotel.remote.pms.hotel.json.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by chenqi on 16/5/11.
 */
@Service
public class HotelRemoteService {
    private final String HOTEL_LIST_QUERY = "/hotel/list/query";
    private final String HOTEL_DETAIL_QUERY = "/hotel/detail/query";
    private final String HOTEL_ROOM_TYPE_QUERY = "/hotel/roomtype/query";
    private final String HOTEL_QUERY_PRICE = "/hotel/queryprice";
    private final String HOTEL_TAG= "/hotel/tags/querytags";

    public HotelQueryListResponse queryHotelList(HotelQueryListRequest hotelQueryListRequest){
        String body = JsonUtils.toJson(hotelQueryListRequest);
        String remoteResult = null;
        try {
            remoteResult = HttpUtils.sendHttpClientPostByString(Constant.PMS_REMOTE_URL + this.HOTEL_LIST_QUERY,
                    new FbbRequestHead(), body);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        HotelQueryListResponse response = JsonUtils.fromJson(remoteResult, HotelQueryListResponse.class);
        return response;
    }

    public HotelQueryDetailResponse queryHotelDetail(HotelQueryDetailRequest hotelQueryDetailRequest){
        String body = JsonUtils.toJson(hotelQueryDetailRequest);
        String remoteResult = null;
        try {
            remoteResult = HttpUtils.sendHttpClientPostByString(Constant.PMS_REMOTE_URL + this.HOTEL_DETAIL_QUERY,
                    new FbbRequestHead(), body);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        HotelQueryDetailResponse response = JsonUtils.fromJson(remoteResult, HotelQueryDetailResponse.class);
        return response;
    }

    public HotelRoomTypeQueryResponse queryRoomType(HotelRoomTypeQueryRequest hotelRoomTypeQueryRequest){
        String body = JsonUtils.toJson(hotelRoomTypeQueryRequest);
        String remoteResult = null;
        try {
            remoteResult = HttpUtils.sendHttpClientPostByString(Constant.PMS_REMOTE_URL + this.HOTEL_ROOM_TYPE_QUERY,
                    new FbbRequestHead(), body);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        HotelRoomTypeQueryResponse response = JsonUtils.fromJson(remoteResult, HotelRoomTypeQueryResponse.class);
        return response;
    }

    public HotelPriceResponse queryHotelPrice(HotelPriceRequest hotelPriceRequest){
        String body = JsonUtils.toJson(hotelPriceRequest);
        String remoteResult = null;
        try {
            remoteResult = HttpUtils.sendHttpClientPostByString(Constant.PMS_REMOTE_URL + this.HOTEL_QUERY_PRICE,
                    new FbbRequestHead(), body);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        HotelPriceResponse response = JsonUtils.fromJson(remoteResult, HotelPriceResponse.class);
        return response;
    }

    public HotelTagResponse queryHotelTags(HotelTagRequest hotelTagRequest){
        String body = JsonUtils.toJson(hotelTagRequest);
        String remoteResult = null;
        try {
            remoteResult = HttpUtils.sendHttpClientPostByString(Constant.PMS_REMOTE_URL + this.HOTEL_TAG,
                    new FbbRequestHead(), body);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HotelTagResponse response = JsonUtils.fromJson(remoteResult, HotelTagResponse.class);
        return response;
    }
}
