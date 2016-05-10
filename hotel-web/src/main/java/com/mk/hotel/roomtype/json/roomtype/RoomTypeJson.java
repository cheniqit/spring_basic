package com.mk.hotel.roomtype.json.roomtype;

/**
 * Created by huangjie on 16/5/10.
 */
public class RoomTypeJson {
    private Long id;
    private Long hotelid;
    private String name;
    private String area;
    private String bedtype;
    private Long roomnum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHotelid() {
        return hotelid;
    }

    public void setHotelid(Long hotelid) {
        this.hotelid = hotelid;
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

    public String getBedtype() {
        return bedtype;
    }

    public void setBedtype(String bedtype) {
        this.bedtype = bedtype;
    }

    public Long getRoomnum() {
        return roomnum;
    }

    public void setRoomnum(Long roomnum) {
        this.roomnum = roomnum;
    }
}
