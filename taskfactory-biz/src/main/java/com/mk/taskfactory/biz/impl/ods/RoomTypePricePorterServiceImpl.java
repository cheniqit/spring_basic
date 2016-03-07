package com.mk.taskfactory.biz.impl.ods;

import com.alibaba.fastjson.JSONObject;
import com.mk.framework.manager.RedisCacheName;
import com.mk.framework.proxy.http.RedisUtil;
import com.mk.taskfactory.api.ods.RoomTypePricePorterService;
import com.mk.taskfactory.biz.mapper.ods.RoomTypeOnlinePriceMapper;
import com.mk.taskfactory.biz.utils.HttpUtils;
import com.mk.taskfactory.biz.utils.JsonUtils;
import com.mk.taskfactory.common.Constants;
import com.mk.taskfactory.model.ods.TRoomTypeOnlinePrice;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by fisher.wu on 16/3/3.
 */
@Service
public class RoomTypePricePorterServiceImpl implements RoomTypePricePorterService {

    private static Logger logger = LoggerFactory.getLogger(RoomTypePricePorterServiceImpl.class);

    private final String url = Constants.OTS_URL + "/dynamicprice/baseprice";

    private static final ThreadPoolExecutor executor;
    private static CloseableHttpClient httpClient;

    private Map<String, String> errorMap = new HashMap<String, String>();

    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(100);
        httpClient = HttpClients.custom().setConnectionManager(cm).build();

        executor = new ThreadPoolExecutor(4, 100, 10L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(200));
    }

    @Autowired
    private RoomTypeOnlinePriceMapper roomTypeOnlinePriceMapper;

    @Override
    public Map<String, Object> doExecute() {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        Map<String, List<String>> map = buildHotelRoomTypeIdMap();
        if (map.isEmpty()) {
            resultMap.put("message", "hotelId/roomTypeId size is 0");
            resultMap.put("SUCCESS", false);
            return resultMap;
        }

        Integer delNum = roomTypeOnlinePriceMapper.deleteByCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        logger.info("delete " + delNum + " rows before insert.");

        Set<Map.Entry<String, List<String>>> entrySet = map.entrySet();
        for (Map.Entry<String, List<String>> entry : entrySet) {
            final String hotelId = entry.getKey();

            List<String> roomTypeIdList = entry.getValue();
            for (final String roomTypeId : roomTypeIdList) {
                while (true) {
                    try {
                        executor.execute(new Runnable() {
                            @Override
                            public void run() {
                                String jsonStr = null;
                                int i = 3;
                                while (i > 0) {
                                    try {
                                        jsonStr = httpPost(url, hotelId, roomTypeId);
                                        break;
                                    } catch (Exception e) {
                                        logger.error("[error] http post exception - hotelId=" + hotelId + ", roomTypeId=" + roomTypeId, e);
                                        i--;
                                    }
                                }

                                if (StringUtils.isNotBlank(jsonStr)) {
                                    BigDecimal price = JsonUtils.parseObject(jsonStr).getBigDecimal("price");
                                    try {
                                        roomTypeOnlinePriceMapper.insert(new TRoomTypeOnlinePrice(new BigInteger(hotelId), new BigInteger(roomTypeId), price));
                                    } catch (Exception e) {
                                        logger.info("insert exception. hotelId=" + hotelId + ", roomTypeId=" + roomTypeId + ", price=" + price, e);
                                    }
                                } else {
                                    errorMap.put(hotelId, roomTypeId);
                                }
                            }
                        });

                        break;
                    } catch (RejectedExecutionException e) {
                        logger.info("workqueue reject new task...");
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e1) {
                            break;
                        }
                    }
                }
            }
        }

        while (true) {
            int queueSize = executor.getQueue().size();
            if (queueSize > 0) {
                logger.info("thread pool queue size is:" + queueSize);
                try {
                    Thread.sleep(60 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                if (!errorMap.isEmpty()) {
                    logger.warn("[warning] errorMap is not empty. hotelId/roomTypeId pair size=" + errorMap.size());

                    Set<Map.Entry<String, String>> set = errorMap.entrySet();
                    int num = 0;
                    StringBuffer sb = new StringBuffer();
                    for (Map.Entry<String, String> entry : set) {
                        if (++num >= 1000) {
                            break;
                        }

                        sb.append(entry.getKey()).append("/").append(entry.getValue()).append(",");
                    }

                    logger.info("[info] error http post hotelId/roomTypeId: " + sb.toString());
                }

                break;
            }
        }

        resultMap.put("message", "");
        resultMap.put("SUCCESS", true);
        return resultMap;
    }

    private Map<String, List<String>> buildHotelRoomTypeIdMap() {
        Map<String, List<String>> map = new HashMap<String, List<String>>();

        Jedis jedis = RedisUtil.getJedis();
        Set<String> cityInfoSet = jedis.smembers(RedisCacheName.CITY_INFO_SET);
        if (CollectionUtils.isEmpty(cityInfoSet)) {
            logger.warn("city_info_set size is 0.");
            return map;
        }

        logger.info("cityInfoSet size is:" + cityInfoSet.size());
        try {
            for (String cityInfo : cityInfoSet) {
                Map<String, String> cityMap = JsonUtils.jsonToMap(cityInfo);
                logger.info("cityId=" + cityMap.get("id") + ", cityCode=" + cityMap.get("cityCode")
                        + ", cityName=" + cityMap.get("cityName"));

                String cityCode = cityMap.get("cityCode");
                Set<String> hotelInfoSet = jedis.smembers(RedisCacheName.CITYHOTELSET + cityCode);
                if (CollectionUtils.isEmpty(hotelInfoSet)) {
                    continue;
                }

                for (String hotelInfo : hotelInfoSet) {
                    String hotelId = JsonUtils.jsonToMap(hotelInfo).get("id");
//                    logger.info("cityId=" + cityMap.get("id") + ", hotelId=" + hotelId);
                    Set<String> roomTypeSet;
                    try {
                        roomTypeSet = jedis.smembers(RedisCacheName.HOTELROOMTYPEINFOSET + hotelId);
                    } catch (Exception e) {
                        logger.error("smembers roomTypeSet exception - cityId=" + cityMap.get("id") + ", hotelId=" + hotelId, e);
                        continue;
                    }

                    if (CollectionUtils.isEmpty(roomTypeSet)) {
                        continue;
                    }

                    List<String> roomTypeIdList = new ArrayList<String>();
                    for (String roomType : roomTypeSet) {
                        String roomTypeId = JsonUtils.jsonToMap(roomType).get("id");
                        roomTypeIdList.add(roomTypeId);
                    }
                    map.put(hotelId, roomTypeIdList);
                }
            }
        } finally {
            jedis.close();
        }

        return map;
    }

    private String httpPost(String url, String hotelId, String roomTypeId) throws IOException {
        HttpPost httpPost = new HttpPost(url);

        List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
        pairs.add(new BasicNameValuePair("hotelid", hotelId));
        pairs.add(new BasicNameValuePair("roomtypeid", roomTypeId));

        httpPost.setEntity(new UrlEncodedFormEntity(pairs));

        ResponseHandler<String> rh = new ResponseHandler<String>() {
            @Override
            public String handleResponse(HttpResponse response) throws IOException {
                StatusLine statusLine = response.getStatusLine();
                HttpEntity entity = response.getEntity();
                if (statusLine.getStatusCode() >= 300) {
                    throw new HttpResponseException(
                            statusLine.getStatusCode(),
                            statusLine.getReasonPhrase());
                }
                if (entity == null) {
                    throw new ClientProtocolException("Response contains no content");
                }

                BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), ContentType.getOrDefault(entity).getCharset()));
                String content = "", readLine;
                while ((readLine = reader.readLine()) != null) {
                    content += readLine;
                }
                return content;
            }
        };

        return httpClient.execute(httpPost, rh);
    }
}
