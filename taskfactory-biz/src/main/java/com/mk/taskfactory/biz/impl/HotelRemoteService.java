package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.biz.utils.ServiceUtils;

import java.util.HashMap;

/**
 * Created by Thinkpad on 2015/10/17.
 */
public class HotelRemoteService {
    private final String otsUrl="http://smlt-ots.imike.cn/ots/";
    private final static String UPDATE_MIKE_PRICE_CACHE = "/hotel/updatemikepricecache";

    private final static String ROOM_STATUS_QUERY_LIST = "/roomstate/querylist";



    public void updateMikePriceCcache(String citycode,String hotelid){
        HashMap params = new HashMap();
        params.put("citycode", citycode);
        params.put("hotelid", hotelid);
        try {
            String jsonStr = ServiceUtils.doPost(otsUrl + this.UPDATE_MIKE_PRICE_CACHE, params , 60);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
