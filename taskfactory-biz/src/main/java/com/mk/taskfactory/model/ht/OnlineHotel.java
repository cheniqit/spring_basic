package com.mk.taskfactory.model.ht;

import java.util.Date;

public class OnlineHotel {
    private Long id;

    private Long hotelId;

    private Integer comefromtype;

    private Date createTime;

    private Date updateTime;

    private String isVaild;

    private Integer cityCode;

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

    public String getIsVaild() {
        return isVaild;
    }

    public void setIsVaild(String isVaild) {
        this.isVaild = isVaild == null ? null : isVaild.trim();
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }
}