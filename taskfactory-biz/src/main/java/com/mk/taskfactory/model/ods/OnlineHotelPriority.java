package com.mk.taskfactory.model.ods;

import java.util.Date;

public class OnlineHotelPriority {
    private Long id;

    private Long hotelId;

    private Integer priority;

    private Integer comefromtype;

    private Date createTime;

    private Date updateTime;

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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getComefromtype() {
        return comefromtype;
    }

    public void setComefromtype(Integer comefromtype) {
        this.comefromtype = comefromtype;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}