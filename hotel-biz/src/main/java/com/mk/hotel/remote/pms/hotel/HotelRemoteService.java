package com.mk.hotel.remote.pms.hotel;

import com.mk.framework.http.HttpUtils;
import com.mk.framework.json.JsonUtils;
import com.mk.hotel.common.utils.UrlUtils;
import com.mk.hotel.remote.pms.common.FbbRequestHead;
import com.mk.hotel.remote.pms.hotel.json.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenqi on 16/5/11.
 */
@Service
public class HotelRemoteService {

    private final String PMS_CHANNEL_ID_KEY = "channelid";
    private final String PMS_TOKEN_KEY = "token";
    private final String PMS_TIMESTAMP_KEY = "timestamp";
    private final String PMS_REMOTE_URL = UrlUtils.getUrl("pms.remote.url");
    private final String HOTEL_LIST_QUERY = "/hotel/list/query";
    private final String HOTEL_DETAIL_QUERY = "/hotel/detail/query";
    private final String HOTEL_ROOM_TYPE_QUERY = "/hotel/roomtype/query";
    private final String HOTEL_QUERY_PRICE = "/hotel/queryprice";
    private final String HOTEL_TAG= "/hotel/tags/querytags";

    private final String HOTEL_CRM = "/hotel/detail/querydetail";

    public HotelQueryListResponse queryHotelList(HotelQueryListRequest hotelQueryListRequest){
        String body = JsonUtils.toJson(hotelQueryListRequest);
        String remoteResult = null;
        try {
            remoteResult = this.sendHttpClientPostByString(PMS_REMOTE_URL + this.HOTEL_LIST_QUERY,
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
            remoteResult = this.sendHttpClientPostByString(PMS_REMOTE_URL + this.HOTEL_DETAIL_QUERY,
                    new FbbRequestHead(), body);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        HotelQueryDetailResponse response = JsonUtils.fromJson(remoteResult, HotelQueryDetailResponse.class);
        return response;
    }

    public HotelQueryDetailResponse queryCrmHotel(HotelQueryDetailRequest hotelQueryDetailRequest){
        String body = JsonUtils.toJson(hotelQueryDetailRequest);
        String remoteResult = null;
        try {
            remoteResult = this.sendHttpClientPostByString(PMS_REMOTE_URL + this.HOTEL_CRM,
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
            remoteResult = this.sendHttpClientPostByString(PMS_REMOTE_URL + this.HOTEL_ROOM_TYPE_QUERY,
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
            remoteResult = this.sendHttpClientPostByString(PMS_REMOTE_URL + this.HOTEL_QUERY_PRICE,
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
            remoteResult = this.sendHttpClientPostByString(PMS_REMOTE_URL + this.HOTEL_TAG,
                    new FbbRequestHead(), body);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HotelTagResponse response = JsonUtils.fromJson(remoteResult, HotelTagResponse.class);
        return response;
    }


    private String sendHttpClientPostByString(String path, FbbRequestHead fbbRequestHead, String body) throws IOException {
        Map<String, String> headParams = new HashMap<String, String>();


        headParams.put(PMS_TOKEN_KEY, fbbRequestHead.getToken());
        headParams.put(PMS_CHANNEL_ID_KEY, fbbRequestHead.getChannelId());
        if(fbbRequestHead.getTimeStamp() != null){
            headParams.put(PMS_TIMESTAMP_KEY, fbbRequestHead.getTimeStamp().toString());
        }
        return HttpUtils.sendHttpClientPostByString(path, headParams, body, 30 * 1000, "UTF-8");
    }
}
