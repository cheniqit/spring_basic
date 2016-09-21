package com.mk.hotel.remote.ots;

import com.dianping.cat.Cat;
import com.mk.framework.HttpUtils;
import com.mk.framework.JsonUtils;
import com.mk.framework.UrlUtils;
import com.mk.hotel.remote.ots.json.City;
import com.mk.hotel.remote.ots.json.CityResponse;
import com.mk.hotel.remote.ots.json.ProvinceDto;
import com.mk.hotel.remote.ots.json.ProvinceResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangjie on 16/6/8.
 */
@Service
public class CityRemoteService {

    private static Logger logger = LoggerFactory.getLogger(CityRemoteService.class);

    private String DOMAIN = UrlUtils.getUrl("ots.domain");
    private final String CITY_QUERY = "/ots/city/querybydist";
    private final String PRO_QUERY = "/ots/city/province/list";

    public City findByDisCode(String disCode){

        String remoteResult = HttpUtils.getData(DOMAIN + CITY_QUERY + "?disCode=" + disCode);
        if(StringUtils.isBlank(remoteResult)){
            return null;
        }
        CityResponse cityResponse = JsonUtils.fromJson(remoteResult, CityResponse.class);
        return cityResponse.getCity();
    }

    public List<ProvinceDto> findProvince() {

        String remoteResult = HttpUtils.doPost(DOMAIN + PRO_QUERY, null);
        if(StringUtils.isBlank(remoteResult)){
            return null;
        }

        try {
            ProvinceResponse provinceResponse = JsonUtils.fromJson(remoteResult, ProvinceResponse.class);
            return provinceResponse.getPro();
        } catch (Exception e) {
            Cat.logError(e);
        }

        return new ArrayList<ProvinceDto>();
    }

}
