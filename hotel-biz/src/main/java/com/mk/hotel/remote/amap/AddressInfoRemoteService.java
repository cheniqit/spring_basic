package com.mk.hotel.remote.amap;

import com.mk.framework.Constant;
import com.mk.framework.HttpUtils;
import com.mk.framework.JsonUtils;
import com.mk.hotel.remote.amap.json.AddressByLocationResponse;
import org.apache.commons.lang.StringUtils;

/**
 * Created by chenqi on 16/5/12.
 */
public class AddressInfoRemoteService {
    public static String REGEO = "/v3/geocode/regeo?output=json&location=%s,%s&key=f8f3fd85e51b484bd18de839f1082429&radius=1000&extensions=all";

    public AddressByLocationResponse findAddressByLocation(String latLocation, String longLocation){
        String remoteResult = HttpUtils.getData(Constant.AMAP_REMOTE_URL + String.format(this.REGEO, latLocation, longLocation));
        if(StringUtils.isBlank(remoteResult)){
            return null;
        }
        remoteResult = remoteResult.replace("[]","\"\"");
        AddressByLocationResponse addressByLocationResponse = JsonUtils.fromJson(remoteResult, AddressByLocationResponse.class);
        return addressByLocationResponse;
    }
}
