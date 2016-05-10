package com.mk.hotel.roomtype.json.roomtypeprice;

import com.mk.hotel.roomtype.model.RoomType;

import java.util.List;

/**
 * Created by huangjie on 16/5/10.
 */
public class RoomTypePriceJson {

    private Long hotelid;

    private List<RoomTypeJson> roomtypes;

    public Long getHotelid() {
        return hotelid;
    }

    public void setHotelid(Long hotelid) {
        this.hotelid = hotelid;
    }

    public List<RoomTypeJson> getRoomtypes() {
        return roomtypes;
    }

    public void setRoomtypes(List<RoomTypeJson> roomtypes) {
        this.roomtypes = roomtypes;
    }
}
