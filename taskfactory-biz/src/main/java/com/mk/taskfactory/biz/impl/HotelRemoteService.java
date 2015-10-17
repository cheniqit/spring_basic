package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.biz.utils.ServiceUtils;

/**
 * Created by Thinkpad on 2015/10/17.
 */
public class HotelRemoteService {
    private final String otsUrl="http://smlt-ots.imike.cn/ots/";
    private final static String UPDATE_MIKE_PRICE_CACHE = "/hotel/updatemikepricecache";

    private final static String ROOM_STATUS_QUERY_LIST = "/roomstate/querylist";

    public void updateMikePriceCcache(String citycode,String hotelid){
        String paramsStr = String.format("?citycode=%s&hotelid=%s", citycode, hotelid);
        String jsonStr = ServiceUtils.postData(otsUrl + this.UPDATE_MIKE_PRICE_CACHE, "POST", paramsStr);
    }


}
