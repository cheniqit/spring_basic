package com.mk.hotel.base.controller;

import com.mk.framework.FileUpload;
import com.mk.hotel.common.utils.QiniuUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
/**
 * Created by chenqi on 16/5/30.
 */
@RestController
@RequestMapping(value = "/file", method = RequestMethod.POST)
public class FileController {
    private static String UPLOAD_PATH = "";
    Logger logger = LoggerFactory.getLogger(FileController.class);

    @RequestMapping("/upload")
    public void upload(String fileName, HttpServletRequest request) throws IOException{
        try {
            FileUpload.uploadFile(request, UPLOAD_PATH, fileName);
        }catch (Exception e){
            throw new IOException("文件上传失败");
        }
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        File rmFile = new File(rootPath + UPLOAD_PATH + fileName);
        if (!rmFile.exists()) {
            throw new IOException("文件上传失败");
        }
        //QiniuUtils.uploadAndTry(rmFile.get)
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
                FileUpload.getFile(fileName);
            } catch (Exception e) {
                e.printStackTrace();
                throw new IOException(e.getMessage());
            }
            os.write(FileUtils.readFileToByteArray(file));
            os.flush();
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }
}
