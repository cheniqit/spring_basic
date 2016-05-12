package com.mk.hotel.remote.hotelstock;


import com.mk.framework.Constant;
import com.mk.framework.FbbRequestHead;
import com.mk.framework.HttpUtils;
import com.mk.framework.JsonUtils;
import com.mk.hotel.remote.common.FbbCommonResponse;
import com.mk.hotel.remote.hotelstock.json.QueryStockRequest;
import com.mk.hotel.remote.hotelstock.json.QueryStockResponse;

/**
 * Created by chenqi on 16/5/10.
 */
public class HotelStockRemoteService {
    private final String HOTEL_QUERY_STOCK = "/stock/roomtype/query";


    public FbbCommonResponse queryStock(QueryStockRequest queryStockRequest){
        String body = JsonUtils.toJson(queryStockRequest);
        String remoteResult = HttpUtils.sendHttpClientPostByString(Constant.PMS_REMOTE_URL + this.HOTEL_QUERY_STOCK,
                new FbbRequestHead(), body);
        FbbCommonResponse<QueryStockResponse> response = JsonUtils.fromJson(remoteResult, FbbCommonResponse.class);
        return response;
    }


}