package com.mk.hotel.remote.pms.hotelstock;


import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import com.mk.framework.http.HttpUtils;
import com.mk.framework.json.JsonUtils;
import com.mk.hotel.common.utils.UrlUtils;
import com.mk.hotel.remote.pms.common.FbbRequestHead;
import com.mk.hotel.remote.pms.hotelstock.json.QueryStockRequest;
import com.mk.hotel.remote.pms.hotelstock.json.QueryStockResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenqi on 16/5/10.
 */
@Service
public class HotelStockRemoteService {

    private final String PMS_CHANNEL_ID_KEY = "channelid";
    private final String PMS_TOKEN_KEY = "token";
    private final String PMS_TIMESTAMP_KEY = "timestamp";
    private final String PMS_REMOTE_URL = UrlUtils.getUrl("pms.remote.url");;
    private final String HOTEL_QUERY_STOCK = "/stock/roomtype/query";


    public QueryStockResponse queryStock(QueryStockRequest queryStockRequest){
        String body = JsonUtils.toJson(queryStockRequest);
        String remoteResult = null;

        Transaction t = Cat.newTransaction("queryRemoteStock", "HotelStockRemoteService.queryStock");

        try {
            remoteResult = this.sendHttpClientPostByString(PMS_REMOTE_URL + this.HOTEL_QUERY_STOCK,
                    new FbbRequestHead(), body);
        } catch (IOException e) {
            e.printStackTrace();
            t.setStatus(e);
        } finally {
            t.complete();
        }
        QueryStockResponse response = JsonUtils.fromJson(remoteResult, QueryStockResponse.class);
        return response;
    }


    public QueryStockResponse queryDatStock(QueryStockRequest queryStockRequest){
        queryStockRequest.setFlag(Boolean.FALSE.toString());
        String body = JsonUtils.toJson(queryStockRequest);
        String remoteResult = null;
        try {
            remoteResult = this.sendHttpClientPostByString(PMS_REMOTE_URL + this.HOTEL_QUERY_STOCK,
                    new FbbRequestHead(), body);
        } catch (IOException e) {
            e.printStackTrace();
        }
        QueryStockResponse response = JsonUtils.fromJson(remoteResult, QueryStockResponse.class);
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
