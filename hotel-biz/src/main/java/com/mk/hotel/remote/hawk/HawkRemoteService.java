package com.mk.hotel.remote.hawk;

import com.mk.framework.HttpUtils;
import com.mk.framework.JsonUtils;
import com.mk.framework.UrlUtils;
import com.mk.hotel.remote.fanqielaile.hotel.json.inn.InnList;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by huangjie on 16/6/27.
 */
@Service
public class HawkRemoteService {

    private String DOMAIN = UrlUtils.getUrl("fanqielaile.domain");

    private final String HOTEL_OFFLINE = "/hotelcallback/hoteloffline";
    private final String ROOMTYPE_OFFLINE = "/hotelcallback/roomtypeoffline";

    private Logger logger = LoggerFactory.getLogger(HawkRemoteService.class);


    public String hotelOffline(Long hotelId) {

        if (null == hotelId) {
            return null;
        }

        StringBuilder url = new StringBuilder()
                .append(DOMAIN)
                .append(HOTEL_OFFLINE);

        Map<String, String> param = new HashMap();
        param.put("hotelId", String.valueOf(hotelId));

        //
        String remoteResult = HttpUtils.doPost(url.toString(), param);

        return remoteResult;
    }

    public String roomTypeOffline(Long roomTypeId) {

        if (null == roomTypeId) {
            return null;
        }

        StringBuilder url = new StringBuilder()
                .append(DOMAIN)
                .append(ROOMTYPE_OFFLINE);

        Map<String, String> param = new HashMap();
        param.put("roomTypeId", String.valueOf(roomTypeId));

        //
        String remoteResult = HttpUtils.doPost(url.toString(), param);

        return remoteResult;
    }
}
