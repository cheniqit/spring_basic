package com.mk.hotel.base.controller;

import com.alibaba.fastjson.JSONObject;
import com.mk.framework.FileUpload;
import com.mk.hotel.common.Constant;
import com.mk.hotel.common.utils.QiniuUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * Created by chenqi on 16/5/30.
 */
@RestController
@RequestMapping(value = "/file", method = RequestMethod.POST)
public class FileController {
    Logger logger = LoggerFactory.getLogger(FileController.class);

    @RequestMapping("/upload")
    public JSONObject upload(String fileName, HttpServletRequest request) throws IOException{
        JSONObject jsonObj = new JSONObject();
        try {
            FileUpload.uploadFile(request, Constant.UPLOAD_PATH, fileName);
        }catch (Exception e){
            throw new IOException("文件上传失败");
        }
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        File rmFile = new File(rootPath + Constant.UPLOAD_PATH + fileName);
        if (!rmFile.exists()) {
            throw new IOException("文件上传失败");
        }
        String qiNiuFileName = UUID.randomUUID().toString()+ ".jpg";
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(rmFile);
            QiniuUtils.uploadAndTry(IOUtils.toByteArray(inputStream), qiNiuFileName, Constant.QINIU_BUCKET);
        }catch (Exception e){
            jsonObj.put("success", "F");
            jsonObj.put("errmsg", "文件上传失败,图片服务器异常");
            return jsonObj;
        }finally {
            IOUtils.closeQuietly(inputStream);
        }
        String qiNiuUrl = Constant.QINIU_DOWNLOAD_ADDRESS+"/"+qiNiuFileName;
        jsonObj.put("success", "T");
        jsonObj.put("url", qiNiuUrl);
        return jsonObj;
    }

    @RequestMapping("/download")
    public void download(String fileName, HttpServletResponse response) throws IOException {
        OutputStream os = response.getOutputStream();
        try {
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.setContentType("image/jpeg; charset=utf-8");
            File file = null;
            try {
                file = FileUpload.getFile(fileName);
            } catch (Exception e) {
                e.printStackTrace();
                throw new IOException(e.getMessage());
            }
            os.write(FileUtils.readFileToByteArray(file));
            os.flush();
        } finally {
           IOUtils.closeQuietly(os);
        }
    }
}
