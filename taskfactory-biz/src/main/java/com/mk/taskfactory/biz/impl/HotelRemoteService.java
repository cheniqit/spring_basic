package com.mk.taskfactory.biz.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mk.taskfactory.biz.utils.JsonUtils;
import com.mk.taskfactory.biz.utils.ServiceUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by Thinkpad on 2015/10/17.
 */
@Service
public class HotelRemoteService {
    private final String otsUrl="http://smlt-ots.imike.cn/ots/";
    private final static String UPDATE_MIKE_PRICE_CACHE = "/hotel/updatemikepricecache";

    private final static String ROOM_STATUS_QUERY_LIST = "/roomstate/querylist";

    public boolean updateMikePriceCache(String hotelid){
        HashMap params = new HashMap();
        params.put("hotelid", hotelid);
        String jsonStr = "";
        try {
            jsonStr = ServiceUtils.doPost(otsUrl + this.UPDATE_MIKE_PRICE_CACHE, params , 60);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        JSONObject jsonObject = JsonUtils.parseObject(jsonStr);
        boolean successFlag = jsonObject.getBoolean("success");
        return successFlag;
    }


}
