package com.mk.hotel.base.controller;

import com.alibaba.fastjson.JSONObject;
import com.mk.framework.FileUpload;
import com.mk.hotel.common.Constant;
import com.mk.hotel.common.utils.QiniuUtils;
import com.mk.hotel.hotelinfo.enums.HotelPicTypeEnum;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by chenqi on 16/5/30.
 */
@RestController
@RequestMapping(value = "/file", method = RequestMethod.POST)
public class FileController {
    Logger logger = LoggerFactory.getLogger(FileController.class);

    @RequestMapping("/upload")
    public JSONObject upload(Long hotelId, Long roomTypeId, Long picResourceId, String picType, HttpServletRequest request) throws IOException{
        JSONObject jsonObj = new JSONObject();
        if(hotelId == null){
            jsonObj.put("success", "F");
            jsonObj.put("errMsg", "hotelId参数为空");
            return jsonObj;
        }
        if(StringUtils.isBlank(picType)){
            jsonObj.put("success", "F");
            jsonObj.put("errMsg", "picType参数为空");
            return jsonObj;
        }
        HotelPicTypeEnum hotelPicTypeEnum = HotelPicTypeEnum.getHotelPicTypeEnumByCode(Integer.valueOf(picType));
        if(hotelPicTypeEnum == null){
            jsonObj.put("success", "F");
            jsonObj.put("errMsg", "picType参数错误");
            return jsonObj;
        }
        if(hotelPicTypeEnum.getCode() == HotelPicTypeEnum.roomType.getCode() && roomTypeId == null){
            jsonObj.put("success", "F");
            jsonObj.put("errMsg", "上传房型图片时必传roomTypeId");
            return jsonObj;
        }
        List<String> resultList = new ArrayList<String>();
        try {
            List<String> localNameList = FileUpload.uploadFile(request, Constant.UPLOAD_PATH);
            String rootPath = request.getSession().getServletContext().getRealPath("/");
            for(String fileName : localNameList){
                File rmFile = new File(rootPath + Constant.UPLOAD_PATH + fileName);
                if (!rmFile.exists()) {
                    throw new IOException("文件上传失败");
                }
                String qiNiuFileName = UUID.randomUUID().toString()+ ".jpg";
                InputStream inputStream = null;
                try {
                    inputStream = new FileInputStream(rmFile);
                    QiniuUtils.uploadAndTry(IOUtils.toByteArray(inputStream), qiNiuFileName, Constant.QINIU_BUCKET);
                    String qiNiuUrl = Constant.QINIU_DOWNLOAD_ADDRESS+"/"+qiNiuFileName;
                    resultList.add(qiNiuUrl);
                    //保存图片信息

                }catch (Exception e){
                    jsonObj.put("success", "F");
                    jsonObj.put("errmsg", "文件上传失败,图片服务器异常");
                    return jsonObj;
                }finally {
                    IOUtils.closeQuietly(inputStream);
                }
            }
        }catch (Exception e){
            throw new IOException("文件上传失败");
        }
        jsonObj.put("success", "T");
        jsonObj.put("urlList", resultList);
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
