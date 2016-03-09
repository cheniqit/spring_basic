package com.mk.taskfactory.model.ods;

import java.math.BigDecimal;
import java.util.Date;

public class OnlineHotelRoomType {
    private Long id;

    private Long hotelId;

    private Long roomTypeId;

    private BigDecimal price;

    private String isOtaPrice;

    private Date createTime;

    private Date updateTime;

    private String isVaild;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public String getIsOtaPrice() {
        return isOtaPrice;
    }

    public void setIsOtaPrice(String isOtaPrice) {
        this.isOtaPrice = isOtaPrice;
    }
}