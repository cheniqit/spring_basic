package com.mk.taskfactory.api.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class OrderRecommendInfoDto {
    private Long id;

    private Long originOrderId;

    private Long newOrderId;

    private Long hotelId;

    private String hotelName;

    private Long roomTypeId;

    private String roomTypeName;

    private BigDecimal orderPrice;

    private String isVisible;

    private String isPromotion;

    private Date createTime;

    private Date updateTime;

    private  String  lastRoomTypeName;

    private  String  hotelContacts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOriginOrderId() {
        return originOrderId;
    }

    public void setOriginOrderId(Long originOrderId) {
        this.originOrderId = originOrderId;
    }

    public Long getNewOrderId() {
        return newOrderId;
    }

    public void setNewOrderId(Long newOrderId) {
        this.newOrderId = newOrderId;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName == null ? null : hotelName.trim();
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName == null ? null : roomTypeName.trim();
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(String isVisible) {
        this.isVisible = isVisible == null ? null : isVisible.trim();
    }

    public String getIsPromotion() {
        return isPromotion;
    }

    public void setIsPromotion(String isPromotion) {
        this.isPromotion = isPromotion == null ? null : isPromotion.trim();
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

    public String getLastRoomTypeName() {
        return lastRoomTypeName;
    }

    public void setLastRoomTypeName(String lastRoomTypeName) {
        this.lastRoomTypeName = lastRoomTypeName;
    }

    public String getHotelContacts() {
        return hotelContacts;
    }

    public void setHotelContacts(String hotelContacts) {
        this.hotelContacts = hotelContacts;
    }
}