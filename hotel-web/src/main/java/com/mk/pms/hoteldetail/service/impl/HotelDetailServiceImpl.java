package com.mk.pms.hoteldetail.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mk.framework.Constant;
import com.mk.framework.JsonUtils;
import com.mk.framework.net.HttpUtils;
import com.mk.pms.hoteldetail.json.HotelDetail;
import com.mk.pms.hoteldetail.service.HotelDetailService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * Created by kirinli on 16/5/12.
 */
@Service
public class HotelDetailServiceImpl implements HotelDetailService{

    public HotelDetail queryHotelDetail(Long hotelId){
        HotelDetail hotelDetail = null;
        String hotelDetailJson = getHotelDetailJson(hotelId);

        if (StringUtils.isNotBlank(hotelDetailJson)){

            JSONObject respObj = JSONObject.parseObject(hotelDetailJson);

            if (null != respObj){
                JSONObject dataObj  =  respObj.getJSONObject("data");

                if (null != dataObj){

                    JSONObject hotelObj = dataObj.getJSONObject("hotel");
                    hotelDetail = JsonUtils.fromJson(hotelObj.toJSONString(), HotelDetail.class);
                }
            }

        }

        return hotelDetail;

    }
    public static String getHotelDetailJson(Long hotelId){
        String url = String.format("%s%s", Constant.PMS_REMOTE_URL, Constant.HOTEL_DETAIL_QUERY_API);

        JSONObject queryHotelDetailJson = new JSONObject();
        queryHotelDetailJson.put("hotelid", hotelId);

        String resp = null;

        try {
            resp = HttpUtils.doPost(url, queryHotelDetailJson.toJSONString());
        }catch (Exception e){
            e.printStackTrace();
        }

        return resp;
    }

    public static void main(String[] args) {
        new HotelDetailServiceImpl().queryHotelDetail(2807l);
    }
}
