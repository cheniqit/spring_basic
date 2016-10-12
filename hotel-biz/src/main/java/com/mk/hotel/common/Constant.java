package com.mk.hotel.common;

import com.mk.framework.UrlUtils;

/**
 * Created by chenqi on 16/5/30.
 */
public class Constant {
    public static final String UPLOAD_PATH = "upload/";

    /**七牛文件下载路径key*/
    public static final String QINIU_DOWNLOAD_ADDRESS = UrlUtils.getUrl("qiniuDownloadAddress");

    /**七牛文件下载路径*/
    public static final String QINIU_RESOURCE_DOWNLOAD_ADDRESS= UrlUtils.getUrl("qiniuResourceDownloadAddress");
    /**七牛安全公钥key*/
    public static final String QINIU_ACCESS_KEY = UrlUtils.getUrl("qiniuAccessKey");
    public static final String QINIU_SECRET_KEY = UrlUtils.getUrl("qiniuSecretKey");
    /**七牛下载凭证时间key*/
    public static final String QINIU_INVALIDATION_TIME = UrlUtils.getUrl("qiniuInvalidationTime");
    /**七牛空间名称key*/
    public static final String QINIU_BUCKET = UrlUtils.getUrl("qiniuBucket");
    public static final String QINIU_RESOURCE_BUCKET=UrlUtils.getUrl("resourceBucket");

    public static final String PUSH_INFO_SET = "PUSH_INFO_SET";
    public static final String TASK_FACTORY_REMOTE_URL = UrlUtils.getUrl("task.factory.remote.url");


    public static final String CONSUMER_GROUP_NAME = "hotelConsumerGroupName";


    public static final int MSG_KEY_LOCK_EXPIRE_TIME = 24 * 60 * 60;
}
