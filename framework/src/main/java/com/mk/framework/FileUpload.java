package com.mk.framework;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileUpload {
	private static final Logger logger = Logger
	.getLogger(FileUpload.class);
	/**
	 *@Description:文件上传
	 *@author：康小龙
	 * @date：2015-9-6 下午4:10:16
	 */
	@SuppressWarnings("unused")
	public static List<String> uploadFile(HttpServletRequest request, String saveFilePath) throws Exception {
		// 创建一个通用的多部分解析器
		List<String> resultList = new ArrayList();
		try {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		String rootPath= request.getSession().getServletContext().getRealPath("/");
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					// 取得当前上传文件的文件名称
					String myFileName = file.getOriginalFilename();
					resultList.add(myFileName);
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (StringUtils.isNotBlank(myFileName)) {
						// 检查上传路径是否存在
						File sendDir= new File(rootPath+saveFilePath+myFileName);
						if (!sendDir.exists()) {
							sendDir.mkdirs();
						}
						File sendFile = new File(rootPath+saveFilePath+myFileName);
							// 上传附件目录
							String sendFilePath = sendFile.getAbsolutePath();
							if (sendFile.exists() && sendFile.isFile())
								sendFile.delete();
							file.transferTo(sendFile);
							logger.debug("upload file:" + sendFilePath);
						} else
							logger.debug("upload msg not exists or not file");
						
					}
				}
			} else {
				logger.debug("no file upload");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}
	
	
	
	
	/**
	 * 
	 * @Description:文件上传
	 * @author:康小龙
	 * @创建时间：2015-5-25 上午11:50:50
	 */
	@SuppressWarnings("unused")
	public static MultipartFile getUploadFile(HttpServletRequest request, String saveFilePath, String fileName) throws Exception {
		// 创建一个通用的多部分解析器
		MultipartFile file = null;
		try {
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());
			// 判断 request 是否有文件上传,即多部分请求
			String rootPath= request.getSession().getServletContext().getRealPath("/");
			
			if (multipartResolver.isMultipart(request)) {
				// 转换成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				// 取得request中的所有文件名
				Iterator<String> iter = multiRequest.getFileNames();
				
				while (iter.hasNext()) {
					// 取得上传文件
					file = multiRequest.getFile(iter.next());
					return file;
					
					/*
					if (file != null) {
						// 取得当前上传文件的文件名称
						String myFileName = file.getOriginalFilename();
						// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
						if (StringUtils.isNotBlank(myFileName)) {
							// 检查上传路径是否存在
							File sendDir= new File(rootPath+saveFilePath);
							if (!sendDir.exists()) {
								sendDir.mkdirs();
							}
							File sendFile = new File(rootPath+saveFilePath+fileName);
								// 上传附件目录
								String sendFilePath = sendFile.getAbsolutePath();
								if (sendFile.exists() && sendFile.isFile())
									sendFile.delete();
								file.transferTo(sendFile);
								logger.debug("upload file:" + sendFilePath);
							} else
								logger.debug("upload msg not exists or not file");
							
						}*/
				}
					
			} else {
				logger.debug("no file upload");	
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}

	public static File getFile(String fileName) throws Exception {
		File file = new File(fileName);
		if(!file.exists()){
			throw new Exception("文件不存在");
		}
		return file;
	}
}
