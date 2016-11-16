package com.mk.hotel.remote.hawk;

import com.mk.framework.excepiton.MyException;
import com.mk.framework.http.HttpUtils;
import com.mk.hotel.common.utils.UrlUtils;
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

    private String DOMAIN = UrlUtils.getUrl("hawk.domain");

    private final String PUSH_URL = "/push/push";

    private Logger logger = LoggerFactory.getLogger(HawkRemoteService.class);


    public String hotelOffline(Long hotelId) {

        if (null == hotelId) {
            return null;
        }

        StringBuilder url = new StringBuilder()
                .append(DOMAIN)
                .append(PUSH_URL);

        Map<String, String> param = new HashMap();
        param.put("hotelId", String.valueOf(hotelId));
        param.put("pushType", "HOTEL_DELETE");

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
                .append(PUSH_URL);

        Map<String, String> param = new HashMap();
        param.put("roomTypeId", String.valueOf(roomTypeId));
        param.put("pushType", "ROOMTYPE_DELETE");

        //
        String remoteResult = HttpUtils.doPost(url.toString(), param);

        return remoteResult;
    }


    public String orderNotify(String orderNo) {
        if (StringUtils.isBlank(orderNo)) {
            throw new MyException("orderNo参数错误");
        }

        StringBuilder url = new StringBuilder()
                .append(DOMAIN)
                .append(PUSH_URL);

        Map<String, String> param = new HashMap();
        param.put("orderNo", orderNo);
        param.put("pushType", "ORDER_STATUS_UPDATE");
        String remoteResult = HttpUtils.doPost(url.toString(), param);
        return remoteResult;
    }


}
