package com.mk.hotel.common;

import com.mk.framework.PropertiesUtil;

/**
 * Created by chenqi on 16/5/30.
 */
public class Constant {
    public static final String UPLOAD_PATH = "upload/";

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
