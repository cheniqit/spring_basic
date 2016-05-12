package com.mk.pms.roomtype.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mk.framework.JsonUtils;
import com.mk.pms.hoteldetail.service.impl.HotelDetailServiceImpl;
import com.mk.pms.roomtype.json.RoomType;
import com.mk.pms.roomtype.service.RoomTypeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kirinli on 16/5/12.
 */
@Service
public class RoomTypeServiceImpl implements RoomTypeService {

    @Override
    public List<RoomType> queryHotelRoomType(Long hotelId) {
        String roomTypesJson = HotelDetailServiceImpl.getHotelDetailJson(hotelId);
        List<RoomType> roomTypes = new ArrayList<RoomType>();

        if (StringUtils.isNotBlank(roomTypesJson)){
            JSONArray roomTypeList = JSONArray.parseArray(roomTypesJson);

            for (Object o : roomTypeList) {
                JSONObject roomTypeJsonObj = (JSONObject)o;
                RoomType roomType = JsonUtils.fromJson(roomTypeJsonObj.toJSONString(), RoomType.class);

                if (roomType != null){
                    roomTypes.add(roomType);
                }
            }
        }

        return roomTypes;
    }

    public static void main(String[] args) {
        List<RoomType> roomTypes = new RoomTypeServiceImpl().queryHotelRoomType(2807l);

        System.out.println(roomTypes.toString());
    }
}
