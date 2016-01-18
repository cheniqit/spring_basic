package com.mk.taskfactory.model;

import java.math.BigDecimal;

public class TRoomTypePmsDynamicInfo {
    private Integer id;
    private Integer hotelId;
    private Integer roomTypeId;
    private Integer storeCount;
    private BigDecimal dynamicPrice1;
    private BigDecimal dynamicPrice2;
    private BigDecimal dynamicPrice3;
    private BigDecimal phonePrice;
    private String timePoint;
    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Integer roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Integer getStoreCount() {
        return storeCount;
    }

    public void setStoreCount(Integer storeCount) {
        this.storeCount = storeCount;
    }

    public BigDecimal getDynamicPrice1() {
        return dynamicPrice1;
    }

    public void setDynamicPrice1(BigDecimal dynamicPrice1) {
        this.dynamicPrice1 = dynamicPrice1;
    }

    public BigDecimal getDynamicPrice2() {
        return dynamicPrice2;
    }

    public void setDynamicPrice2(BigDecimal dynamicPrice2) {
        this.dynamicPrice2 = dynamicPrice2;
    }

    public BigDecimal getDynamicPrice3() {
        return dynamicPrice3;
    }

    public void setDynamicPrice3(BigDecimal dynamicPrice3) {
        this.dynamicPrice3 = dynamicPrice3;
    }

    public BigDecimal getPhonePrice() {
        return phonePrice;
    }

    public void setPhonePrice(BigDecimal phonePrice) {
        this.phonePrice = phonePrice;
    }

    public String getTimePoint() {
        return timePoint;
    }

    public void setTimePoint(String timePoint) {
        this.timePoint = timePoint;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
