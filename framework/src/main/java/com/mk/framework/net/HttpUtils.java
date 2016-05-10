package com.mk.framework.net;

import com.mk.framework.proxy.http.CharsetDetector;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtils {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(HttpUtils.class);

    private static ThreadLocal<HttpClientContext> contextThreadLocal = new ThreadLocal<>();

    private static HttpClientContext getContext() {
        if ( contextThreadLocal.get() == null ) {
            contextThreadLocal.set(HttpClientContext.create());
        }

        return contextThreadLocal.get();
    }

    private static void removeContext() {
        contextThreadLocal.remove();
    }



    public static String doGet(String url)  {
        String resp = null;
        try {
            resp = doGet(url, null);
        } catch (IOException e) {
            LOGGER.error("请求出错：", e);
        }
        return resp;
    }



    public static String doGet(String url, Map<String , Object> headers) throws IOException {
        LOGGER.info("发送请求：{}", url);

        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

        try {
            HttpGet httpGet = new HttpGet(url);

            if (headers != null){
                httpGet.setHeaders(ParamsHeaders.paramHeader(headers));
            }else {
                httpGet.setHeaders(ParamsHeaders.paramHeader());
            }

            RequestConfig.Builder builder  = RequestConfig.custom();

            builder.setSocketTimeout(Config.READ_TIMEOUT)
                    .setConnectionRequestTimeout(Config.READ_TIMEOUT)
                    .setConnectTimeout(Config.READ_TIMEOUT);

            httpGet.setConfig(builder.build());

            CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet, getContext());

            int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();

            HttpEntity httpEntity = closeableHttpResponse.getEntity();

            byte[] bytes = EntityUtils.toByteArray(httpEntity);

            String charset = CharsetDetector.guessEncoding(bytes);

            String result = new String(bytes, charset);

            if ( result.length() < 500 ) {
                LOGGER.info("响应内容：{}", result);
            } else {
                LOGGER.info("响应内容：{}", result.substring(0, 499));
            }

            LOGGER.info("响应码为：{}", statusCode);
            if ( statusCode != 200 ) {
                throw new IOException("响应码为：" + statusCode);
            }

            return result;
        } finally {
            if ( closeableHttpClient != null ) {
                closeableHttpClient.close();
            }
        }
    }


    public static String doPost(String url) throws IOException {
        return doPost(url, null, null);
    }

    public static String doPost(String url, Map<String , Object> params) throws IOException {
        return doPost(url, params, null);
    }

    public static String doPost(String url, Map<String , Object> params, Map<String, Object> headers) throws IOException {
        LOGGER.info("发送请求：{}", url);

        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

        try {
            HttpPost httpPost = new HttpPost(url);

            if (headers != null){
                httpPost.setHeaders(ParamsHeaders.paramHeader(headers));
            }else {
                httpPost.setHeaders(ParamsHeaders.paramHeader());
            }


            if (params != null){
                List<NameValuePair> nameValuePairs = new ArrayList<>();
                for (Map.Entry<String, Object> entry : params.entrySet()){
                    String key = entry.getKey();
                    Object val = entry.getValue();

                    nameValuePairs.add((new BasicNameValuePair(key, val.toString())));
                }

                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            }

            RequestConfig.Builder builder  = RequestConfig.custom();

            builder.setSocketTimeout(Config.READ_TIMEOUT)
                    .setConnectionRequestTimeout(Config.READ_TIMEOUT)
                    .setConnectTimeout(Config.READ_TIMEOUT);

            httpPost.setConfig(builder.build());

            CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpPost, getContext());

            int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();

            HttpEntity httpEntity = closeableHttpResponse.getEntity();

            byte[] bytes = EntityUtils.toByteArray(httpEntity);

            String charset = CharsetDetector.guessEncoding(bytes);

            String result = new String(bytes, charset);

            if ( result.length() < 500 ) {
                LOGGER.info("响应内容：{}", result);
            } else {
                LOGGER.info("响应内容：{}", result.substring(0, 499));
            }

            LOGGER.info("响应码为：{}", statusCode);
            if ( statusCode != 200 ) {
                throw new IOException("响应码为：" + statusCode);
            }

            return result;
        } finally {
            if ( closeableHttpClient != null ) {
                closeableHttpClient.close();
            }
        }
    }




    public static String getCookies() throws IOException {

        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

        try {
            HttpGet httpGet = new HttpGet("http://pad.qunar.com");

            RequestConfig.Builder builder  = RequestConfig.custom();


            builder.setSocketTimeout(Config.READ_TIMEOUT)
                    .setConnectionRequestTimeout(Config.READ_TIMEOUT)
                    .setConnectTimeout(Config.READ_TIMEOUT);

            httpGet.setConfig(builder.build());

            CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet, getContext());

            Header[] headers  = closeableHttpResponse.getAllHeaders();
            String cookies =null;
            for (Header header : headers) {
                if (header.getName().equals("Set-Cookie"))
                    if (header.getValue().contains("QN48")){
                        String [] tmp = header.getValue().split(";");

                        cookies= tmp[0];
                        break;
                    }

            }
            return cookies ;
        } finally {
            if ( closeableHttpClient != null ) {
                closeableHttpClient.close();
            }
        }
    }

    public static String urlEncoder(String url) {
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("使用UTF-8编码无法成功对：{}进行编码", url);
        }

        return null;
    }


}
