package com.mk.framework.manager;

/**
 * Created by 振涛 on 2015/12/25.
 */
public class RedisCacheName {

    /**
     * 存储被加入黑名单的代理IP的SET的KEY
     */
    public static final String CRAWER_BAD_PROXY_SERVER_POOL_SET = "crawer_bad_proxy_server_pool_set";

    /**
     * 待刷新酒店价格所在城市的SET
     */
    public static final String CRAWER_CITY_NAME_SET = "crawer_city_list_and_city_name";

    /**
     * 待刷新酒店价格的酒店ID列表
     */
    public static final String CRAWER_HOTEL_INFO_REFRESH_SET = "crawer_hotel_info_refresh_set";

    public static final String CRAWER_HOTEL_INFO_REFRESH_THREAD_SET = "crawer_hotel_info_refresh_thread_set";

    /**
     * 正在刷新酒店价格的酒店ID列表
     */
    public static final String CRAWER_HOTEL_INFO_REFRESHING_SET = "crawer_hotel_info_refreshing_set";

    /********动态价格-OTA价格***********/
    public static final String DYNAMIC_PRICE_OTA = "DYNAMIC:PRICE:OTA:";
    /********动态价格-协议价***********/
    public static final String DYNAMIC_PRICE_AGREEMENT = "DYNAMIC:PRICE:AGREEMENT:";
    /********动态价格-mike价***********/
    public static final String DYNAMIC_PRICE_MK = "DYNAMIC:PRICE:MK:";
    /********动态价格-门市价***********/
    public static final String DYNAMIC_PRICE_MARKET = "DYNAMIC:PRICE:MARKET:";
    /********动态价格-美团价格***********/
    public static final String DYNAMIC_PRICE_MEITUAN = "DYNAMIC:PRICE:MEITUAN:";
    /********动态协议数量***********/
    public static final String DYNAMIC_DEALCOUNT = "DYNAMIC:DEALCOUNT:";
    /********动态库存数量***********/
    public static final String DYNAMIC_STORECOUNT = "DYNAMIC:STORECOUNT:";

    /**
     * 当前整点电话预定点击率
     */

    public static final String CURRENT_OCLOCK_INTELLIGENT_PHONE_CLICK_RATIO = "CURRENT_OCLOCK_INTELLIGENT_PHONE_CLICK_RATIO_";

    /**
     * 当前整点电话预定点击数
     */

    public static final String CURRENT_OCLOCK_INTELLIGENT_PHONE_CLICK_NUM = "CURRENT_OCLOCK_INTELLIGENT_PHONE_CLICK_NUM_";

    /**
     * 上一个整点电话预定点击率
     */

    public static final String FRONT_OCLOCK_INTELLIGENT_PHONE_CLICK_RATIO = "FRONT_OCLOCK_INTELLIGENT_PHONE_CLICK_RATIO_";

    /**
     * 上一个整点电话预定点击数
     */

    public static final String FRONT_OCLOCK_INTELLIGENT_PHONE_CLICK_NUM = "FRONT_OCLOCK_INTELLIGENT_PHONE_CLICK_NUM_";

    /**
     * 昨日电话预定点击率
     */

    public static final String YESTERDAY_OCLOCK_INTELLIGENT_PHONE_CLICK_RATIO = "YESTERDAY_OCLOCK_INTELLIGENT_PHONE_CLICK_RATIO_";

    /**
     * 昨日电话预定点击数
     */

    public static final String YESTERDAY_OCLOCK_INTELLIGENT_PHONE_CLICK_NUM = "YESTERDAY_OCLOCK_INTELLIGENT_PHONE_CLICK_NUM_";

    /**
     * 电话预定点击率
     *
     */
    public static final String INTELLIGENT_PHONE_CLICK_RATIO = "INTELLIGENT_PHONE_CLICK_RATIO_";

    /**
     * 电话预定点击率
     *
     */
    public static final String INTELLIGENT_PHONE_CLICK_NUM = "INTELLIGENT_PHONE_CLICK_NUM_";

    /**
     * 当前整点酒店拒单率
     */
    public static final String CURRENT_OCLOCK_INTELLIGENT_PHONE_REFUSE_ORDER_RATIO = "CURRENT_OCLOCK_INTELLIGENT_PHONE_REFUSE_ORDER_RATIO_";
    /**
     * 当前整点酒店拒单数
     */
    public static final String CURRENT_OCLOCK_INTELLIGENT_PHONE_REFUSE_ORDER_NUM = "CURRENT_OCLOCK_INTELLIGENT_PHONE_REFUSE_ORDER_NUM_";

    /**
     * 上一个整点酒店拒单率
     */

    public static final String FRONT_OCLOCK_INTELLIGENT_PHONE_REFUSE_ORDER_RATIO = "FRONT_OCLOCK_INTELLIGENT_PHONE_REFUSE_ORDER_RATIO_";

    /**
     * 上一个整点酒店拒单数
     */

    public static final String FRONT_OCLOCK_INTELLIGENT_PHONE_REFUSE_ORDER_NUM = "FRONT_OCLOCK_INTELLIGENT_PHONE_REFUSE_ORDER_NUM_";

    /**
     * 昨日酒店拒单率
     */

    public static final String YESTERDAY_OCLOCK_INTELLIGENT_PHONE_REFUSE_ORDER_RATIO = "YESTERDAY_OCLOCK_INTELLIGENT_PHONE_REFUSE_ORDER_RATIO_";

    /**
     * 昨日酒店拒单数
     */

    public static final String YESTERDAY_OCLOCK_INTELLIGENT_PHONE_REFUSE_ORDER_NUM = "YESTERDAY_OCLOCK_INTELLIGENT_PHONE_REFUSE_ORDER_NUM_";
    /**
     * 酒店拒单率
     */

    public static final String INTELLIGENT_PHONE_REFUSE_ORDER_RATIO = "INTELLIGENT_PHONE_REFUSE_ORDER_RATIO_";

    /**
     * 酒店拒单数
     */

    public static final String INTELLIGENT_PHONE_REFUSE_ORDER_NUM = "INTELLIGENT_PHONE_REFUSE_ORDER_NUM_";
    /**
     * 酒店点击数量
     */

    public static final String HOTEL_CLICK_NUM = "HOTEL_CLICK_NUML_";
    /***电话预定数量***/
    public static final String INTELLIGENT_PHONE_ORDER_NUM = "INTELLIGENT_PHONE_ORDER_NUM_";
    /***去哪儿酒店信息***/
    public static final String QHOTE = "QHOTE_";
    /***酒店来源***/
    public static final String HOTEL_SOURCE = "HOTEL_SOURCE_";
    /***酒店信息***/
    public static final String HOTELJSONINFO= "HOTEL_JSON_INFO_";
    /***酒店评价***/
    //public static final String HOTELCOMMENTINFO= "HOTEL_COMMENT_INFO_";
    /***酒店评价详情***/
    //public static final String HOTELCOMMENTDETAILINFO= "HOTEL_COMMENT_DETAIL_INFO_";
    /***设施基础数据***/
    public static final String FACILITYINFO= "FACILITY_INFO_";
    /***酒店图片***/
    public static final String HOTEL_PICTURE_INFOS_SET= "HOTEL_PICTURE_INFO_SET_";
    /***酒店设施***/
    public static final String HOTELFACILITYINFOSET= "HOTEL_FACILITY_INFO_SET_";
    /***酒店房型***/
    public static final String HOTELROOMTYPEINFO= "HOTEL_ROOMTYPE_INFO_";
    /***酒店房型***/
    public static final String HOTELROOMTYPEINFOSET= "HOTEL_ROOMTYPE_INFO_SET_";
    /***酒店周边***/
    //public static final String HOTELSURROUNDINFOSET= "HOTEL_SURROUND_INFO_SET_";
    /***城市酒店信息***/
    public static final String CITYHOTELSET = "CITY_HOTEL_SET_";
    /***乐住设施基础信息***/
    public static final String LEZHUFACILITY= "LEZHU_FACILITY_";
    /***乐住有效价格酒店***/
    //public static final String LEZHU_VAILD_PRICE_HOTEL_INFO= "LEZHU_VAILD_PRICE_HOTEL_INFO_";
    /***乐住有效价格房型***/
    //public static final String LEZHU_VAILD_PRICE_ROOMTYPE_INFO= "LEZHU_VAILD_PRICE_ROOMTYPE_INFO_";
    /***酒店评分***/
    public static final String HOTEL_SCORE_INFO= "HOTEL_SCORE_INFO_";
    /***酒店房型最低价***/
    //public static final String HOTEL_ROOMTYPE_MIN_PRICE= "HOTEL_ROOMTYPE_MIN_PRICE_";
    /***酒店房型OTA价格***/
    //public static final String HOTEL_ROOMTYPE_OTA_PRICE= "HOTEL_ROOMTYPE_OTA_PRICE_";
    /***酒店房型动态价格***/
    public static final String HOTEL_ROOMTYPE_DYNAMIC_PRICE= "HOTEL_ROOMTYPE_DYNAMIC_PRICE_";
    /***城市信息***/
    //public static final String CITY_INFO= "CITY_INFO_";
    /***城市信息SET集合***/
    public static final String CITY_INFO_SET= "CITY_INFO_SET";
    /***酒店权重***/
    public static final String HOTEL_PRIORITY= "HOTEL_PRIORITY_";
    /***房型图片mapping***/
    public static final String roomType_pic_mapping= "ROOMTYPE_PIC_MAPPING_";
    /***乐住房型上线标识***/
    public static final String LEZHU_ONLINE_ROOMTYPE= "LEZHU_ONLINE_ROOMTYPE_";
}

