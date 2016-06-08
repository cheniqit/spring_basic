package com.mk.hotel.remote.ots;

import com.mk.framework.HttpUtils;
import com.mk.framework.JsonUtils;
import com.mk.framework.UrlUtils;
import com.mk.hotel.remote.ots.json.City;
import com.mk.hotel.remote.ots.json.CityResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by huangjie on 16/6/8.
 */
@Service
public class CityRemoteService {

    private static Logger logger = LoggerFactory.getLogger(CityRemoteService.class);

    private String DOMAIN = UrlUtils.getUrl("ots.domain");
    private final String CITY_QUERY = "city/querybydist";

    public City findByDisCode(String disCode){

        String remoteResult = HttpUtils.getData(DOMAIN + CITY_QUERY + "?disCode=" + disCode);
        if(StringUtils.isBlank(remoteResult)){
            return null;
        }
        CityResponse cityResponse = JsonUtils.fromJson(remoteResult, CityResponse.class);
        return cityResponse.getCity();
    }

}
