package com.mk.pms.hoteldetail.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mk.framework.Constant;
import com.mk.framework.JsonUtils;
import com.mk.framework.net.HttpUtils;
import com.mk.pms.hoteldetail.json.HotelDetail;
import com.mk.pms.hoteldetail.service.HotelDetailService;
import com.mk.pms.roomtype.json.Picture;
import com.mk.pms.roomtype.json.RoomType;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

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

                    if (null != hotelDetail){
                        String hotelPicsJson = hotelDetail.getHotelpics();

                        if (StringUtils.isNotBlank(hotelPicsJson)){

                            List<Picture> hotelPics = JSON.parseArray(hotelPicsJson,Picture.class);
                            if (null != hotelPics){
                                hotelDetail.setHotelPics(hotelPics);
                            }
                        }


                        List<RoomType> roomTypes = hotelDetail.getRoomtypes();

                        if (null != roomTypes){

                            for (RoomType roomType : roomTypes) {
                                String roomTypePicsJson = roomType.getRoomtypepics();

                                if (StringUtils.isNotBlank(roomTypePicsJson)){
                                    List<Picture> roomTypePics = JSON.parseArray(roomTypePicsJson,Picture.class);
                                    roomType.setRoomTypePics(roomTypePics);
                                }
                            }
                        }
                    }

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
