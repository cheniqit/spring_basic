package com.mk.pms.roomtype.json;

import java.util.List;

/**
 * Created by kirinli on 16/5/11.
 */
public class RoomType {
    private Long id;

    private String name;

    private Integer area;

    private String bedtype;

    private Integer prepay;

    private Integer breakfast;

    private Integer status;

    private Integer refund;

    private Integer maxroomnum;

    private Integer roomnum;

    private String roomtypepics;

    private List<Picture> roomTypePics;

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

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getBedtype() {
        return bedtype;
    }

    public void setBedtype(String bedtype) {
        this.bedtype = bedtype;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getRoomnum() {
        return roomnum;
    }

    public void setRoomnum(Integer roomnum) {
        this.roomnum = roomnum;
    }

    public String getRoomtypepics() {
        return roomtypepics;
    }

    public void setRoomtypepics(String roomtypepics) {
        this.roomtypepics = roomtypepics;
    }

    public List<Picture> getRoomTypePics() {
        return roomTypePics;
    }

    public void setRoomTypePics(List<Picture> roomTypePics) {
        this.roomTypePics = roomTypePics;
    }
}
