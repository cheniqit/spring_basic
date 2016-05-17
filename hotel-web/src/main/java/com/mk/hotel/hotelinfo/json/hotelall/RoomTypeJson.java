
package com.mk.hotel.hotelinfo.json.hotelall;

/**
 * Created by huangjie on 16/5/10.
 */
public class RoomTypeJson {
    private Long id;
    private String name;
    private String area;
    private Integer bedtype;
    private Integer roomnum;
    private String bedsize;
    private String prepay;
    private String breakfast;
    private Integer refund;
    private Integer maxroomnum;
    private String roomtypepics;

    private Integer status;

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

    public Integer getBedtype() {
        return bedtype;
    }

    public void setBedtype(Integer bedtype) {
        this.bedtype = bedtype;
    }

    public Integer getRoomnum() {
        return roomnum;
    }

    public void setRoomnum(Integer roomnum) {
        this.roomnum = roomnum;
    }

    public String getBedsize() {
        return bedsize;
    }

    public void setBedsize(String bedsize) {
        this.bedsize = bedsize;
    }

    public String getPrepay() {
        return prepay;
    }

    public void setPrepay(String prepay) {
        this.prepay = prepay;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
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

    public String getRoomtypepics() {
        return roomtypepics;
    }

    public void setRoomtypepics(String roomtypepics) {
        this.roomtypepics = roomtypepics;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
