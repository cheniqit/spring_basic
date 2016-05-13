package com.mk.framework;


import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.xml.ws.http.HTTPException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpUtils {
    private static Logger log = LoggerFactory.getLogger(HttpUtils.class);
    public static final int timeout = 30 * 1000;
    private static final String charset = "UTF-8";
    private static final String GET = "GET";

    public static String doPost(String reqURL, Map<String, String> params){
        return sendHttpClientPost(reqURL, params, timeout, charset);
    }

    public static String sendHttpClientPostByString(String path, FbbRequestHead fbbRequestHead, String body){
        Map<String, String> headParams = new HashMap<>();
        headParams.put(Constant.PMS_TOKEN_KEY, fbbRequestHead.getToken());
        headParams.put(Constant.PMS_CHANNEL_ID_KEY, fbbRequestHead.getChannelId());
        if(fbbRequestHead.getTimeStamp() != null){
            headParams.put(Constant.PMS_TIMESTAMP_KEY, fbbRequestHead.getTimeStamp().toString());
        }
        return sendHttpClientPostByString(path, headParams, body, timeout, charset);
    }

    public static String sendHttpClientPostByString(String path, Map<String, String> headParams, String body){
        return sendHttpClientPostByString(path, headParams, body, timeout, charset);
    }

    //用apache接口实现http的post提交数据
    public static String sendHttpClientPostByString(String path, Map<String, String> headParams, String body, int timeout, String encode) {
        try {
            // 使用post方式提交数据
            HttpPost httpPost = new HttpPost(path);
            StringEntity stringEntity = new StringEntity(body);
            httpPost.setEntity(stringEntity);

            StringBuffer paramsStr = new StringBuffer();
            if (headParams != null && !headParams.isEmpty()) {
                for (Map.Entry<String, String> entry : headParams.entrySet()) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }

            log.info(String.format("=========remote path[%s]====headParams[%s]======", path, paramsStr.toString()));
            log.info(String.format("=========remote path[%s]====body[%s]======", path, body));

            // 执行post请求，并获取服务器端的响应HttpResponse
            DefaultHttpClient client = new DefaultHttpClient();
            client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout);
            client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, timeout);


            HttpResponse httpResponse = client.execute(httpPost);
            //获取服务器端返回的状态码和输入流，将输入流转换成字符串
            String result = "";
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                InputStream inputStream = httpResponse.getEntity().getContent();
                result  = changeInputStream(inputStream, encode);
            }else{
                InputStream inputStream = httpResponse.getEntity().getContent();
                result = changeInputStream(inputStream, encode);
            }
            log.info(String.format("=========remote path[%s] result[%s]==========", path, result));
            return result;
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";

    }

    //用apache接口实现http的post提交数据
    public static String sendHttpClientPost(String path,
                                            Map<String, String> params, int timeout, String encode) {
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        StringBuffer paramsStr = new StringBuffer();
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry
                        .getValue()));
                paramsStr.append(entry.getKey()).append("[").append(entry.getValue()).append("]");
            }
        }
        log.info(String.format("=========remote path[%s]====params[%s]======", path, paramsStr.toString()));
        try {
            // 实现将请求的参数封装到表单中，即请求体中
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, encode);
            // 使用post方式提交数据
            HttpPost httpPost = new HttpPost(path);
            httpPost.setEntity(entity);
            // 执行post请求，并获取服务器端的响应HttpResponse
            DefaultHttpClient client = new DefaultHttpClient();
            client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout);
            client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, timeout);
            HttpResponse httpResponse = client.execute(httpPost);
            //获取服务器端返回的状态码和输入流，将输入流转换成字符串
            String result = "";
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                InputStream inputStream = httpResponse.getEntity().getContent();
                result  = changeInputStream(inputStream, encode);
            }else{
                InputStream inputStream = httpResponse.getEntity().getContent();
                result = changeInputStream(inputStream, encode);
            }
            log.info(String.format("=========remote path[%s] result[%s]==========", path, result));
            return result;
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "";

    }

    /*
     * // 把从输入流InputStream按指定编码格式encode变成字符串String
     */
    public static String changeInputStream(InputStream inputStream,
                                           String encode) {

        // ByteArrayOutputStream 一般叫做内存流
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        String result = "";
        if (inputStream != null) {

            try {
                while ((len = inputStream.read(data)) != -1) {
                    byteArrayOutputStream.write(data, 0, len);

                }
                result = new String(byteArrayOutputStream.toByteArray(), encode);

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        return result;
    }
	/**
	 * 发送请求
	 *
	 * @param url
	 * @param send
	 * @return
	 * @throws Exception
	 */
	public static String dopostjson(String url, String send) throws Exception {
		log.info("请求地址：url:{},timeout:{}", url, timeout);
		return HttpUtils.postJsonAsHttps(url, send, timeout, 3);
	}


	/**
	 * @param url
	 * @param sendParamStr
	 * @param timeout
	 * @param tryTime
	 * @return
	 * @throws Exception
	 */
	public static String postJsonAsHttps(String url, String sendParamStr, int timeout, int tryTime) throws Exception {

		Transaction t = Cat.newTransaction("OtsHttpsPost", url);
		CloseableHttpClient httpclient = null;
		int code = 0;
		try {
			HttpPost post = new HttpPost(url);

			StringEntity entity = new StringEntity(sendParamStr, ContentType.create("application/json", "UTF-8"));
			post.setEntity(entity);
			httpclient = createSSLClientDefault(null);

			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();// 设置请求和传输超时时间
			post.setConfig(requestConfig);
			CloseableHttpResponse response = httpclient.execute(post);
			code = response.getStatusLine().getStatusCode();
			if (code == 200) {
				t.setStatus(Transaction.SUCCESS);
				return EntityUtils.toString(response.getEntity());
			} else {
				throw new HTTPException(code);
			}
		} catch (Exception e) {
			t.setStatus(e);
			throw e;
		} finally {
			if (httpclient != null) {
				httpclient.close();
			}
			t.complete();
		}
	}

	public static CloseableHttpClient createSSLClientDefault(CookieStore cookieStore) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {

		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
			// 信任所有
			public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				return true;
			}
		}).build();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
		if (cookieStore != null) {
			return HttpClients.custom().setSSLSocketFactory(sslsf).setDefaultCookieStore(cookieStore).build();
		} else {
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		}
	}

    public static String getData(String url) {
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setDoOutput(true);
            conn.setConnectTimeout(10000);
            conn.setRequestMethod(GET);
            conn.setRequestProperty("Content-type", "text/html");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("contentType", "utf-8");
            conn.connect();
            StringBuffer result = new StringBuffer();
            //返回
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                result.append(tempString);
            }
            return result.toString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String path = "http://smlt-ots.imike.cn/ots/roomstate/querylist";
        Map<String, String> params = new HashMap<String, String>();
        params.put("isShowAllRoom","T");
        params.put("hotelid", String.valueOf(2187));
        params.put("roomtypeid", String.valueOf(7229));
        params.put("startdateday", "20151021");
        params.put("enddateday", "20151022");
        String result = sendHttpClientPost(path, params, timeout, "utf-8");
        System.out.println("-result->>" + result);

    }

}