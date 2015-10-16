package com.mk.taskfactory.biz.utils;


import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServiceUtils {
    public static String post_data(String url, String method, String phone) {
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setDoOutput(true);
            conn.setConnectTimeout(1000);

            conn.setRequestMethod(method);
            conn.setDoOutput(true);
            if (StringUtils.isNotBlank(phone)) {
                StringBuffer params = new StringBuffer();
                params.append("phone").append("=").append(phone);
                byte[] bypes = params.toString().getBytes();
                conn.getOutputStream().write(bypes);// 输入参数

            }
            //返回
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String result = reader.readLine();
            return result.trim();
        } catch (Exception e) {

            return null;
        }
    }
    public static String get_data(String url, String method) {
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setDoOutput(true);
            conn.setConnectTimeout(1000);
            conn.setRequestMethod(method);
            conn.connect();

            //返回
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String result = reader.readLine();
            return result.trim();
        } catch (Exception e) {

            return null;
        }
    }
}