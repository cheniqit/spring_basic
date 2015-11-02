package com.mk.taskfactory.common;


import com.mk.taskfactory.biz.utils.PropertiesUtil;

/**
 * Created by Thinkpad on 2015/10/18.
 */
public class Constants {

    public static final Integer PROMO_FININSHED = -1;

    public static final Integer PROMOING = 1;

    public static final Integer PROMO_NOT_START = 0;

    public static String token = new PropertiesUtil().getValue("common.properties", "ots.token");
    public static String OTS_URL = new PropertiesUtil().getValue("common.properties", "ots.url");
    //public static final String OTS_URL = "http://ota2test.imike.cn/ots";
    //public static final String OTS_URL = "http://localhost:9010/ots";
}
