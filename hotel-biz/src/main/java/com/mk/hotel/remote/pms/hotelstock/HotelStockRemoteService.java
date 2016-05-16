package com.mk.hotel.remote.pms.hotelstock;


import com.mk.framework.Constant;
import com.mk.framework.FbbRequestHead;
import com.mk.framework.HttpUtils;
import com.mk.framework.JsonUtils;
import com.mk.hotel.remote.pms.hotelstock.json.QueryStockRequest;
import com.mk.hotel.remote.pms.hotelstock.json.QueryStockResponse;
import org.springframework.stereotype.Service;

/**
 * Created by chenqi on 16/5/10.
 */
@Service
public class HotelStockRemoteService {
    private final String HOTEL_QUERY_STOCK = "/stock/roomtype/query";


    public QueryStockResponse queryStock(QueryStockRequest queryStockRequest){
        String body = JsonUtils.toJson(queryStockRequest);
        String remoteResult = HttpUtils.sendHttpClientPostByString(Constant.PMS_REMOTE_URL + this.HOTEL_QUERY_STOCK,
                new FbbRequestHead(), body);
        QueryStockResponse response = JsonUtils.fromJson(remoteResult, QueryStockResponse.class);
        return response;
    }


}
