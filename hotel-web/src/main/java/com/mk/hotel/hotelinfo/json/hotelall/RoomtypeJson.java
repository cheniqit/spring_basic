package com.mk.hotel.hotelinfo.json.hotelall;

/**
 * Created by huangjie on 16/5/10.
 */
public class RoomtypeJson {
    private Long id;
    private String name;
    private String area;
    private Long bedtype;
    private Long roomnum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Long getBedtype() {
        return bedtype;
    }

    public void setBedtype(Long bedtype) {
        this.bedtype = bedtype;
    }

    public Long getRoomnum() {
        return roomnum;
    }

    public void setRoomnum(Long roomnum) {
        this.roomnum = roomnum;
    }
}
