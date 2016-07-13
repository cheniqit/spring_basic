package com.mk.hotel.remote.dog;

import com.google.gson.Gson;
import com.mk.hotel.common.Constant;
import com.mk.framework.HttpUtils;
import com.mk.framework.excepiton.MyException;
import com.mk.hotel.remote.dog.common.HotelCommonResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenqi on 16/5/24.
 */
@Service
public class TaskFactoryRemoteService {
    private  static String saveHotelPic = "/hoteltoredis/saveHotelPic";
    private  static String updateHotelPrice = "/hoteltoredis/updateHotelPrice";

    public void saveHotelPic(String hotelId, String hotelPicInfo){
        if(StringUtils.isBlank(hotelId) || StringUtils.isBlank(hotelPicInfo)){
            throw new MyException("hotelId信息错误");
        }

        Map<String, String> paramsMap = new HashMap();
        paramsMap.put("hotelId", hotelId.toString());
        paramsMap.put("hotelPicInfo", hotelPicInfo);

        String resultJson = HttpUtils.doPost(Constant.TASK_FACTORY_REMOTE_URL+this.saveHotelPic, paramsMap);
        if(StringUtils.isBlank(resultJson)){
            throw new MyException("保存图片信息错误");
        }
        HotelCommonResponse response = new Gson().fromJson(resultJson, HotelCommonResponse.class);
        if(StringUtils.isBlank(response.getSuccess()) || !response.getSuccess().equals("T")){
            throw new MyException("保存图片信息错误");
        }
    }

    public void updateHotelPrice(String hotelId, String roomTypeId, String price) {
        if(StringUtils.isBlank(hotelId) || StringUtils.isBlank(roomTypeId)|| StringUtils.isBlank(price)){
            throw new MyException("参数错误");
        }

        Map<String, String> paramsMap = new HashMap();
        paramsMap.put("hotelId", hotelId.toString());
        paramsMap.put("roomTypeId", roomTypeId);
        paramsMap.put("price", price);

        String resultJson = HttpUtils.doPost(Constant.TASK_FACTORY_REMOTE_URL+ this.updateHotelPrice, paramsMap);
        HotelCommonResponse response = new Gson().fromJson(resultJson, HotelCommonResponse.class);
        if(response == null || StringUtils.isBlank(response.getSuccess()) || !response.getSuccess().equals("T")){
            throw new MyException("更新价格信息错误");
        }
    }
}