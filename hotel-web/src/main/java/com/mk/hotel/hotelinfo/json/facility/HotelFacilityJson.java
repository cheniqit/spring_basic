package com.mk.hotel.hotelinfo.json.facility;

import java.util.List;

/**
 * Created by huangjie on 16/5/12.
 */
public class HotelFacilityJson {

    private Long hotelid;
    private String tagid;
    private List<RoomTypeFacilityJson> roomtype;

    public Long getHotelid() {
        return hotelid;
    }

    public void setHotelid(Long hotelid) {
        this.hotelid = hotelid;
    }

    public String getTagid() {
        return tagid;
    }

    public void setTagid(String tagid) {
        this.tagid = tagid;
    }

    public List<RoomTypeFacilityJson> getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(List<RoomTypeFacilityJson> roomtype) {
        this.roomtype = roomtype;
    }
}
