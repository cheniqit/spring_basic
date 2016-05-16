package com.mk.framework;

/**
 * Created by kirinli on 16/5/10.
 */
public class Constant {
    public static final String PMS_CHANNEL_ID_KEY = "channelid";
    public static final String PMS_TOKEN_KEY = "token";
    public static final String PMS_TIMESTAMP_KEY = "timestamp";
    public static final String PMS_REMOTE_URL = UrlUtils.getUrl("pms.remote.url");
    public static final String AMAP_REMOTE_URL = "http://restapi.amap.com";


    /**
     *  根据酒店id查询酒店详情
     */
    public static final String HOTEL_DETAIL_QUERY_API = "/hotel/detail/query";

    public static final String REGIN_INFO_API = "/area/queryprovince";

    public static final String TAG_INFO_API = "/area/querytags";

    public static final String ORDER_CREAT_API = "/order/create";

    public static final String ORDER_CANCEL_API = "/order/cancel";

    public static final String QUERY_ORDER_API = "/order/queryorder";

    public static final String ROOM_TYPE_QUERY_API = "/hotel/roomtype/query";

    public static final String ROOM_TYPE_PRICE_QUERY_API = "/hotel/queryprice";

    public static final String STOCK_QUERY_API = "/stock/roomtype/query";

    public static final String ORDER_FAILED_QUERY_API = "/order/queryfaillist";
    /*远程调用默认每页的返回的数据量**/
    public static final Integer DEFAULT_REMOTE_PAGE_SIZE = 1000;

    public static final String SYSTEM_USER_NAME = "hotel_system";


}
