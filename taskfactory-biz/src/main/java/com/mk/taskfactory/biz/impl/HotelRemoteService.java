package com.mk.taskfactory.biz.impl;

import com.alibaba.fastjson.JSONObject;
import com.mk.taskfactory.biz.utils.HttpUtils;
import com.mk.taskfactory.biz.utils.JsonUtils;
import com.mk.taskfactory.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thinkpad on 2015/10/17.
 */
@Service
public class HotelRemoteService {
    private static Logger logger = LoggerFactory.getLogger(ValidRateTaskLogicServiceImpl.class);

    private final static String UPDATE_MIKE_PRICE_CACHE = "/hotel/updatemikepricecache";

    public boolean updateMikePriceCache(String hotelid){
        logger.info(String.format("remote url [%s] begin params hotelid[%s]", this.UPDATE_MIKE_PRICE_CACHE, hotelid));
        HashMap params = new HashMap();
        params.put("hotelid", hotelid);
        String jsonStr = "";
        try {
            jsonStr = HttpUtils.doPost(Constants.OTS_URL + this.UPDATE_MIKE_PRICE_CACHE, params);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        logger.info(String.format("remote url [%s] end params result[%s]", this.UPDATE_MIKE_PRICE_CACHE, jsonStr));
        JSONObject jsonObject = JsonUtils.parseObject(jsonStr);
        boolean successFlag = true;
        if(jsonObject.getBoolean("success") == null){
            successFlag = false;
        }
        return successFlag;
    }

    public String hotelInit(String token, String cityId, String hotelId){
        Map<String, String> params=new HashMap<String, String>();
        params.put("token", token);
        params.put("cityid", cityId);
        params.put("hotelid", hotelId);
        String postResult=HttpUtils.doPost(Constants.OTS_URL + "/hotel/init", params);
        return postResult;
    }
    public String updatemikeprices(String token, String hotelId){
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
        }
        Map<String, String> params=new HashMap<String, String>();
        params.put("token", token);
        params.put("hotelid", hotelId);
        String postResult=HttpUtils.doPost(Constants.OTS_URL + "/hotel/updatemikeprices", params);
        return postResult;
    }

}
