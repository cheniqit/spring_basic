package com.mk.hotel.roomtype.json.roomtypeprice;

import com.mk.hotel.roomtype.model.RoomType;

import java.util.List;

/**
 * Created by huangjie on 16/5/10.
 */
public class RoomTypePriceJson {

    private Long hotelid;

    private List<RoomTypeJson> roomtypeprices;

    public Long getHotelid() {
        return hotelid;
    }

    public void setHotelid(Long hotelid) {
        this.hotelid = hotelid;
    }

    public List<RoomTypeJson> getRoomtypeprices() {
        return roomtypeprices;
    }

    public void setRoomtypeprices(List<RoomTypeJson> roomtypeprices) {
        this.roomtypeprices = roomtypeprices;
    }
}
