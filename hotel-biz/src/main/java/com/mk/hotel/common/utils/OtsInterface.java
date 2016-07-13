package com.mk.hotel.common.utils;

import com.mk.framework.HttpUtils;
import com.mk.framework.UrlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by huangjie on 16/5/19.
 */
public class OtsInterface {
    private static Logger logger = LoggerFactory.getLogger(OtsInterface.class);
    private static String addHotelIds = "/indexerjob/addhotelids";
    private static String updateOrderStatusByPms = "/pmsorder/updateOrderStatusByPms";
    private static String otsUrl = UrlUtils.getUrl("ots.interface.initHotel.url");

    public static void initHotel(Long hotelId) {
        String url = otsUrl + addHotelIds;
        String token = UrlUtils.getUrl("ots.interface.initHotel.token");

        //
        Map<String, String> param = new HashMap<String, String>();
        param.put("hotelId", String.valueOf(hotelId));
        param.put("token", token);

        String s = HttpUtils.doPost(url, param);
        logger.info(s);
    }

    public static void updateOrderStatusByPms(String thirdPartyOrderNo, Integer pmsOrderStatus) {
        String url = otsUrl + updateOrderStatusByPms;
        //
        Map<String, String> param = new HashMap<String, String>();
        String token = UrlUtils.getUrl("ots.interface.initHotel.token");
        param.put("token", token);
        param.put("thirdPartyOrderNo", String.valueOf(thirdPartyOrderNo));
        param.put("pmsOrderStatus", pmsOrderStatus.toString());
        String s = HttpUtils.doPost(url, param);
        logger.info(s);
    }



}
