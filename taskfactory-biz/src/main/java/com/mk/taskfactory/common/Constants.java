package com.mk.taskfactory.common;


import com.mk.taskfactory.biz.utils.PropertiesUtil;
/**
 * Created by Thinkpad on 2015/10/18.
 */
public class Constants {

//    public static final String OTS_URL = "http://smlt-ots.imike.cn/ots";
//    public static final String token = "1qaz2wsx";
    //public static final String OTS_URL = "http://ota2test.imike.cn/ots";
    //public static final String OTS_URL = "http://ots.imike.com/ots";
    //public static final String OTS_URL = "http://localhost:9010/ots";

    public static final Integer PROMO_FININSHED = -1;

    public static final Integer PROMOING = 1;

    public static final Integer PROMO_NOT_START = 0;

    public static String token = new PropertiesUtil().getValue("common.properties", "ots.token");
    public static String OTS_URL = new PropertiesUtil().getValue("common.properties", "ots.url");
    public static String OTS_HUIDU = new PropertiesUtil().getValue("common.properties", "ots.huidu");
    //public static final String OTS_URL = "http://ota2test.imike.cn/ots";
    //public static final String OTS_URL = "http://localhost:9010/ots";
    /**七牛文件下载路径key*/
    public static final String QINIU_DOWNLOAD_ADDRESS = new PropertiesUtil().getValue("common.properties", "qiniuDownloadAddress");

    /**七牛文件下载路径*/
    public static final String QINIU_RESOURCE_DOWNLOAD_ADDRESS=new PropertiesUtil().getValue("common.properties", "qiniuResourceDownloadAddress");
    /**七牛安全公钥key*/
    public static final String QINIU_ACCESS_KEY = new PropertiesUtil().getValue("common.properties", "qiniuAccessKey");
    public static final String QINIU_SECRET_KEY = new PropertiesUtil().getValue("common.properties", "qiniuSecretKey");
    /**七牛下载凭证时间key*/
    public static final String QINIU_INVALIDATION_TIME = new PropertiesUtil().getValue("common.properties", "qiniuInvalidationTime");
    /**七牛空间名称key*/
    public static final String QINIU_BUCKET = new PropertiesUtil().getValue("common.properties", "qiniuBucket");
    public static final String QINIU_RESOURCE_BUCKET=new PropertiesUtil().getValue("common.properties", "resourceBucket");

}
