package com.mk.hotel.remote.ots;

import com.mk.framework.http.HttpUtils;
import com.mk.framework.json.JsonUtils;
import com.mk.hotel.common.utils.UrlUtils;
import com.mk.hotel.remote.ots.json.City;
import com.mk.hotel.remote.ots.json.CityResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by huangjie on 16/6/8.
 */
@Service
public class CityRemoteService {

    private static Logger logger = LoggerFactory.getLogger(CityRemoteService.class);

    private String DOMAIN = UrlUtils.getUrl("ots.domain");
    private final String CITY_QUERY = "/ots/city/querybydist";

    public City findByDisCode(String disCode){

        String remoteResult = null;
        try {
            remoteResult = HttpUtils.doGet(DOMAIN + CITY_QUERY + "?disCode=" + disCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(StringUtils.isBlank(remoteResult)){
            return null;
        }
        CityResponse cityResponse = JsonUtils.fromJson(remoteResult, CityResponse.class);
        return cityResponse.getCity();
    }

}
