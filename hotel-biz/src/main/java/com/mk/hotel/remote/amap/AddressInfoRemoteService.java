package com.mk.hotel.remote.amap;

import com.dianping.cat.Cat;
import com.mk.framework.Constant;
import com.mk.framework.HttpUtils;
import com.mk.framework.JsonUtils;
import com.mk.hotel.remote.amap.json.AddressByLocationResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chenqi on 16/5/12.
 */
@Service
public class AddressInfoRemoteService {
    private static Logger logger = LoggerFactory.getLogger(AddressInfoRemoteService.class);

    public static String REGEO = "/v3/geocode/regeo?output=json&location=%s,%s&key=f8f3fd85e51b484bd18de839f1082429&radius=1000&extensions=all";

    public AddressByLocationResponse findAddressByLocation(String latLocation, String longLocation){
        logger.info("call findAddressByLocation params latLocation={},longLocation={}", latLocation, longLocation);
        String remoteResult = HttpUtils.getData(Constant.AMAP_REMOTE_URL + String.format(this.REGEO, longLocation, latLocation));
        if(StringUtils.isBlank(remoteResult)){
            return null;
        }
        remoteResult = remoteResult.replace("[]","\"\"");
        AddressByLocationResponse addressByLocationResponse = JsonUtils.fromJson(remoteResult, AddressByLocationResponse.class);
        return addressByLocationResponse;
    }

    public String findTownCodeByLocation(String latLocation, String longLocation){
        logger.info("call findAddressByLocation params latLocation={},longLocation={}", latLocation, longLocation);
        String remoteResult = HttpUtils.getData(Constant.AMAP_REMOTE_URL + String.format(this.REGEO, longLocation, latLocation));
        if(StringUtils.isBlank(remoteResult)){
            return null;
        }
        if(remoteResult.indexOf("\"towncode\":[]") > 1){
            return null;
        }
        try {
            String townCode = remoteResult.substring(remoteResult.indexOf("towncode"), remoteResult.indexOf("neighborhood"));
            Pattern p = Pattern.compile("(\\d+)");
            Matcher m = p.matcher(townCode);
            if (m.find()) {
                townCode = m.group(1);
                if (StringUtils.isNotBlank(townCode) && townCode.length() >= 9) {
                    return townCode.substring(0, 9);
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        }
        return null;
    }
}
