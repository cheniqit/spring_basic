package com.mk.hotel.base.controller;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * Created by chenqi on 16/5/30.
 */
public class FileControllerTest {
    @Test
    public void testUpload() throws Exception {
        File f = new File("/Users/chenqi/Desktop/827_151228114203_1.png");
        PostMethod filePost = new PostMethod( "http://localhost:9080/file/upload?picType=1&hotelId=1470&updateBy=1&fileName=11.jpg");
        Part[] parts = { new FilePart("filename", f)
        };
        filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost .getParams()));
        HttpClient clients = new HttpClient();
        int status = clients.executeMethod(filePost);
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(  filePost.getResponseBodyAsStream(), "UTF-8"));
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuffer .append(line);
            }
            rd.close();
            System.out.println("接受到的流是：" + stringBuffer + "—-" + status);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testDownload() throws Exception {

    }
}