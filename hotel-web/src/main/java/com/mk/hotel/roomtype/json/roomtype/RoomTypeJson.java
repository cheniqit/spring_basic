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

    private Integer prepay;
    private Integer breakfast;
    private Integer refund;
    private Integer maxroomnum;
    private Integer status;
    private String roomtypepics;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRoomtypepics() {
        return roomtypepics;
    }

    public void setRoomtypepics(String roomtypepics) {
        this.roomtypepics = roomtypepics;
    }

    public Integer getPrepay() {
        return prepay;
    }

    public void setPrepay(Integer prepay) {
        this.prepay = prepay;
    }

    public Integer getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(Integer breakfast) {
        this.breakfast = breakfast;
    }

    public Integer getRefund() {
        return refund;
    }

    public void setRefund(Integer refund) {
        this.refund = refund;
    }

    public Integer getMaxroomnum() {
        return maxroomnum;
    }

    public void setMaxroomnum(Integer maxroomnum) {
        this.maxroomnum = maxroomnum;
    }

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
