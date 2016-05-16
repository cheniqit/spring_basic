package com.mk.hotel.hotelinfo.json.facility;

import java.util.List;

/**
 * Created by huangjie on 16/5/12.
 */
public class RoomTypeFacilityJson {
    private Long roomtypeid;
    private List<FacilityJson> tags;

    public Long getRoomtypeid() {
        return roomtypeid;
    }

    public void setRoomtypeid(Long roomtypeid) {
        this.roomtypeid = roomtypeid;
    }

    public List<FacilityJson> getTags() {
        return tags;
    }

    public void setTags(List<FacilityJson> tags) {
        this.tags = tags;
    }
}
