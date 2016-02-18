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
    /***酒店信息***/
    public static final String HOTELSOURCEID = "HOTEL_SOURCE_ID_";
    /***城市酒店信息***/
    public static final String CITYHOTELSET = "CITY_HOTEL_SET_";
}
