package com.mk.taskfactory.biz.utils;


import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceUtils {
    private static final Logger logger = LoggerFactory.getLogger(ServiceUtils.class);
    private static String charset = "UTF-8";

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

    /**
     * @param address
     * @param params
     * @return
     * @throws java.io.IOException
     */
    public static String doPost(String address, Map<String, String> params, int timeout) throws IOException {
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httpPost = new HttpPost(address);
        // 设置超时
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout*1000).setConnectTimeout(timeout*1000).build();
        httpPost.setConfig(requestConfig);
        // 创建参数队列
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        // 追加参数
        if(params != null && params.size() > 0){
            for (String s : params.keySet()) {
                formparams.add(new BasicNameValuePair(s, params.get(s)));
            }
        }
        UrlEncodedFormEntity uefEntity = null;
        CloseableHttpResponse response = null;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, charset);
            httpPost.setEntity(uefEntity);

            response = httpclient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String entityStr = EntityUtils.toString(entity, charset);
                    return entityStr;
                }
            }
        } catch (UnsupportedEncodingException e) {
            logger.error("http请求异常(UnsupportedEncodingException)：" + e.getMessage(), e);
        } catch (ClientProtocolException e) {
            logger.error("http请求异常(ClientProtocolException)：" + e.getMessage(), e);
        } catch (ParseException e) {
            logger.error("http请求异常(ParseException)：" + e.getMessage(), e);
        } catch (IOException e) {
            logger.error("http请求异常(IOException)：" + e.getMessage(), e);
        } finally {
            response.close();
            httpclient.close();
        }
        return "";
    }
}