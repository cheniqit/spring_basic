package com.mk.hotel.common.utils;

import com.dianping.cat.Cat;
import com.mk.hotel.common.Constant;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.UUID;

public class QiniuUtils {

	private QiniuUtils(){

	}

	private static class  QiniuUploadManager{
		public static UploadManager instance = new UploadManager();
	}

	public static UploadManager getInstance(){
		return QiniuUploadManager.instance;
	}
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
			bucket = Constant.QINIU_BUCKET;
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
	private static String uploadOnce(byte [] bytes, String fileName, String bucket) {

		if(StringUtils.isEmpty(bucket)) {
			bucket = Constant.QINIU_BUCKET;
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
			bucket = Constant.QINIU_BUCKET;
		}
		UploadManager uploadManager = getInstance();
		String key = Constant.QINIU_ACCESS_KEY;
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

	public static HashMap upload(byte []bytes, String fileName, String bucket) throws IOException {

		if (StringUtils.isBlank(fileName)) {
			throw new IOException("无法获取保存文件名");
		}
		if(StringUtils.isEmpty(bucket)) {
			bucket = Constant.QINIU_BUCKET;
		}
		UploadManager uploadManager = getInstance();
		String key = Constant.QINIU_ACCESS_KEY;
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
			bucket = Constant.QINIU_BUCKET;
		}
		try {
			Auth auth = Auth.create(Constant.QINIU_ACCESS_KEY,
					Constant.QINIU_SECRET_KEY);
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
			Auth auth = Auth.create(Constant.QINIU_ACCESS_KEY,
					Constant.QINIU_SECRET_KEY);
			return auth.privateDownloadUrl(Constant.QINIU_DOWNLOAD_ADDRESS + key
					+ treatMethod, Long.parseLong(Constant.QINIU_INVALIDATION_TIME));
		} catch (Exception e) {
			QiniuUtils.logger.error("获取七牛下载凭证异常：" + e.getMessage(), e);
		}
		return null;
	}



	/**
	 * 根据地址获得数据的字节流
	 * @param strUrl 网络连接地址
	 * @return
	 */
	private static BufferedImage getImageFromNetByUrl(String strUrl){
		InputStream inStream = null;

		try {
			URL url = new URL(strUrl);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			inStream =  conn.getInputStream();//通过输入流获取图片数据
			BufferedImage sourceImg = ImageIO.read(inStream);

			//inStream.close();
			return sourceImg;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (null != inStream){
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static String upload(String url, String bucket) throws IOException {

		String fileName = UUID.randomUUID().toString().replace("-","");
		String address = Constant.QINIU_DOWNLOAD_ADDRESS;
		//check
		if (org.apache.commons.lang3.StringUtils.isBlank(url)) {
			throw new IOException("上传url不可为空");
		}

		if (org.apache.commons.lang3.StringUtils.isBlank(bucket)) {
			throw new IOException("上传空间名不可为空");
		}

		//upload
		ByteArrayOutputStream bao = null;
		try {
			//
			BufferedImage img = getImageFromNetByUrl(url);
			if (null == img) {
				return null;
			}

			bao = new ByteArrayOutputStream();
			if (url.toLowerCase().endsWith("png")) {
				ImageIO.write(img, "png", bao);
				fileName = fileName + ".png";
			} else if (url.toLowerCase().endsWith("jpg")) {
				ImageIO.write(img, "jpg", bao);
				fileName = fileName + ".jpg";
			}

			//
			upload(bao.toByteArray(), fileName, bucket);

			return address + "/" + fileName;
		} catch (Exception e) {
			e.printStackTrace();
			Cat.logError(e);

		} finally {
			if (null != bao) {
				bao.close();
			}
		}

		return null;
	}

	public static void main(String []args) {
		try {
			String s = QiniuUtils.upload("http://img.fanqiele.com/files/wd/1744/1744_0_0.png","hotelpic");
			System.out.println(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
