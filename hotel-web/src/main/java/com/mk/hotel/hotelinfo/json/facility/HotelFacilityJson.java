package com.mk.hotel.hotelinfo.json.facility;

import java.util.List;

/**
 * Created by huangjie on 16/5/12.
 */
public class HotelFacilityJson {

    private Long hotelid;
    private List<FacilityJson> tags;
    private List<RoomTypeFacilityJson> roomtypeTags;

    public Long getHotelid() {
        return hotelid;
    }

    public void setHotelid(Long hotelid) {
        this.hotelid = hotelid;
    }


    public List<FacilityJson> getTags() {
        return tags;
    }

    public void setTags(List<FacilityJson> tags) {
        this.tags = tags;
    }

    public List<RoomTypeFacilityJson> getRoomtypeTags() {
        return roomtypeTags;
    }

    public void setRoomtypeTags(List<RoomTypeFacilityJson> roomtypeTags) {
        this.roomtypeTags = roomtypeTags;
    }
}
