package com.mk.framework.net;

import com.mk.framework.Constant;
import org.apache.http.Header;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kirinli on 16/5/10.
 */
public class ParamsHeaders implements IRequestParamsHead {
    @Override
    public Header[] getHeaders(Map<String, Object> headers) {

        HeaderConfig headerConfig = HeaderConfig.custom();

        for (Map.Entry<String, Object> entry : headers.entrySet()) {
            String key = entry.getKey();
            Object val = entry.getValue();
            headerConfig.add(key, val.toString());
        }


        return headerConfig.toArray();
    }


    public static Header[] paramHeader() {
        Map<String, Object> headers = new HashMap<>();
        PmsAuthHeader pmsAuthHeader = new PmsAuthHeader();
        headers.put(Constant.PMS_CHANNEL_ID_KEY, pmsAuthHeader.getChannelId());
        headers.put(Constant.PMS_TOKEN_KEY, pmsAuthHeader.getToken());
        headers.put(Constant.PMS_TIMESTAMP_KEY, pmsAuthHeader.getTimestamp());
        return  new ParamsHeaders().getHeaders(headers);
    }

    public static Header[] paramHeader(Map<String, Object> headers) {

        return  new ParamsHeaders().getHeaders(headers);
    }
}
