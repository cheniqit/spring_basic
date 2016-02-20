package com.mk.taskfactory.biz.utils;

import com.mk.taskfactory.common.Constants;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class QiniuUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(QiniuUtils.class);

	/**
	 * 上传,若失败最多尝试3次
	 * @param bytes 内容
	 * @return key
	 */
	public static String uploadAndTry(byte [] bytes, String fileName, String bucket) {

		if(null == bytes) {
			return "";
		}
		if(StringUtils.isEmpty(bucket)) {
			bucket = Constants.QINIU_BUCKET;
		}

		//上传
		String key = null;
		key = QiniuUtils.uploadOnce(bytes, fileName, bucket);

		//try again
		if (null == key) {
			key = QiniuUtils.uploadOnce(bytes, fileName, bucket);
		}

		//try again
		if (null == key) {
			key = QiniuUtils.uploadOnce(bytes,fileName, bucket);
		}
		bytes = null;

		return key;
	}

	/**
	 * 上传1次
	 * @param bytes 内容
	 * @return key
	 */
	private static String uploadOnce(byte [] bytes,String fileName, String bucket) {

		if(StringUtils.isEmpty(bucket)) {
			bucket = Constants.QINIU_BUCKET;
		}
		try {
			HashMap responseInfo = QiniuUtils.upload(bytes,fileName, bucket);
			Object objKey = responseInfo.get("hash");
			if (null != objKey) {
				return (String)objKey;
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static HashMap upload(File file, String bucket) throws IOException {
		if(StringUtils.isEmpty(bucket)) {
			bucket = Constants.QINIU_BUCKET;
		}
		UploadManager uploadManager = new UploadManager();
		String key = Constants.QINIU_ACCESS_KEY;
		if (null == key) {
			throw new IOException("获取七牛上传凭证异常");
		}
		String token = QiniuUtils.generateSimpleUploadTicket(bucket);
		if (null == token) {
			throw new IOException("获取七牛上传凭证异常");
		}

		//
		Response res = uploadManager.put(file, key, token);
		HashMap map = res.jsonToObject(HashMap.class);
		return map;
	}

	public static HashMap upload(byte []bytes,String fileName, String bucket) throws IOException {

		if (StringUtils.isBlank(fileName)) {
			throw new IOException("无法获取保存文件名");
		}
		if(StringUtils.isEmpty(bucket)) {
			bucket = Constants.QINIU_BUCKET;
		}
		UploadManager uploadManager = new UploadManager();
		String key = Constants.QINIU_ACCESS_KEY;
		if (null == key) {
			throw new IOException("获取七牛上传凭证异常");
		}
		String token = QiniuUtils.generateSimpleUploadTicket(bucket);
		if (null == token) {
			throw new IOException("获取七牛上传凭证异常");
		}

		//
		Response res = uploadManager.put(bytes, fileName, token);
		bytes = null;
		HashMap map = res.jsonToObject(HashMap.class);
		map.put("fileName",fileName);
		return map;
	}

	/**
	 * 获取上传凭证key
	 * @return 返回凭证key
	 */
	public static String generateSimpleUploadTicket(String bucket) {
		//api地址：http://developer.qiniu.com/docs/v6/sdk/java-sdk.html
		if(StringUtils.isEmpty(bucket)) {
			bucket = Constants.QINIU_BUCKET;
		}
		try {
			Auth auth = Auth.create(Constants.QINIU_ACCESS_KEY,
					Constants.QINIU_SECRET_KEY);
			return auth.uploadToken(bucket);
		} catch (Exception e) {
			QiniuUtils.logger.error("获取七牛上传凭证异常：" + e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 获取下载凭证
	 * @param key 文件key
	 * @param treatMethod 处理管道
	 * @return 下载凭证
	 */
	public static String generateDownloadTicket(String key, String treatMethod) {
		//七牛api地址：http://developer.qiniu.com/docs/v6/sdk/java-sdk.html#private-download
		try {
			Auth auth = Auth.create(Constants.QINIU_ACCESS_KEY,
					Constants.QINIU_SECRET_KEY);
			return auth.privateDownloadUrl(Constants.QINIU_DOWNLOAD_ADDRESS + key
					+ treatMethod, Long.parseLong(Constants.QINIU_INVALIDATION_TIME));
		} catch (Exception e) {
			QiniuUtils.logger.error("获取七牛下载凭证异常：" + e.getMessage(), e);
		}
		return null;
	}

}
