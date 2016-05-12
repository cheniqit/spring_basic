package com.mk.pms.roomtype.service.impl;

import com.mk.pms.hoteldetail.json.HotelDetail;
import com.mk.pms.hoteldetail.service.HotelDetailService;
import com.mk.pms.roomtype.json.RoomType;
import com.mk.pms.roomtype.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kirinli on 16/5/12.
 */
@Service
public class RoomTypeServiceImpl implements RoomTypeService {

    @Autowired
    private HotelDetailService hotelDetailService;

    @Override
    public List<RoomType> queryHotelRoomType(Long hotelId) {
        HotelDetail hotelDetail = hotelDetailService.queryHotelDetail(hotelId);
        List<RoomType> roomTypes = new ArrayList<RoomType>();

        if (hotelDetail != null){
            roomTypes = hotelDetail.getRoomtypes();
        }

        return roomTypes;
    }

    public static void main(String[] args) {
        List<RoomType> roomTypes = new RoomTypeServiceImpl().queryHotelRoomType(2807l);

        System.out.println(roomTypes.toString());
    }
}
