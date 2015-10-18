package com.mk.taskfactory.biz.utils;


import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class ServiceUtils {
    public static String getata(String url, String method) {
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


    public static String doPost(String url, Map<String, String> params, int timeout) throws Exception {
        String back = "";
        HttpClient client = new HttpClient();
        client.getHttpConnectionManager().getParams().setConnectionTimeout(timeout * 1000);
        PostMethod method = new PostMethod(url);
        try {
            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(1, false));
            method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
            method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, timeout * 1000);
            if(params != null && params.size() > 0){
                for (String key : params.keySet()) {
                    method.setParameter(key, params.get(key));
                }
            }
            int status = client.executeMethod(method);
            if (status == HttpStatus.SC_OK) {
                back = method.getResponseBodyAsString();
            } else {
                throw new Exception("异常：" + status);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            method.releaseConnection();
        }
        return back;
    }
}