package com.mk.taskfactory.model;

import java.util.Date;

public class HotelDayClick {
    private Long id;

    private Long hotelId;

    private Long roomTypeId;

    private Integer viewCount;

    private Integer telClickCount;

    private Integer telCount;

    private Integer rejectCount;

    private String time;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getTelClickCount() {
        return telClickCount;
    }

    public void setTelClickCount(Integer telClickCount) {
        this.telClickCount = telClickCount;
    }

    public Integer getTelCount() {
        return telCount;
    }

    public void setTelCount(Integer telCount) {
        this.telCount = telCount;
    }

    public Integer getRejectCount() {
        return rejectCount;
    }

    public void setRejectCount(Integer rejectCount) {
        this.rejectCount = rejectCount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}