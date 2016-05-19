package com.mk.hotel.common.utils;

import com.mk.framework.HttpUtils;
import com.mk.framework.UrlUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by huangjie on 16/5/19.
 */
public class OtsInterface {

    public static void initHotel(Long hotelId) {
        String url = UrlUtils.getUrl("ots.interface.initHotel.url");
        String token = UrlUtils.getUrl("ots.interface.initHotel.token");

        //
        Map<String, String> param = new HashMap<String, String>();
        param.put("hotelId", String.valueOf(hotelId));
        param.put("token", token);

        HttpUtils.doPost(url, param);
    }
}
