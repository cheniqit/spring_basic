package com.mk.taskfactory.model;

import java.math.BigDecimal;

public class TRoomSaleConfig {
    private Integer id;
    private Integer hotelId;
    private Integer roomId;
    private Integer roomTypeId;
    private Integer saleType;
    private BigDecimal salePrice;
    private BigDecimal costPrice;
    private Integer num;
    private String saleName;
    private BigDecimal settleValue;
    private Integer settleType;
    private String valid;
    private Integer styleType;
    private String started;
    private Integer saleConfigInfoId;
    private String createTime;
    private String createBy;
    private String updateTime;
    private String updateBy;
    private Integer saleRoomTypeId;

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

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Integer roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Integer getSaleType() {
        return saleType;
    }

    public void setSaleType(Integer saleType) {
        this.saleType = saleType;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public BigDecimal getSettleValue() {
        return settleValue;
    }

    public void setSettleValue(BigDecimal settleValue) {
        this.settleValue = settleValue;
    }

    public Integer getSettleType() {
        return settleType;
    }

    public void setSettleType(Integer settleType) {
        this.settleType = settleType;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public Integer getStyleType() {
        return styleType;
    }

    public void setStyleType(Integer styleType) {
        this.styleType = styleType;
    }

    public String getStarted() {
        return started;
    }

    public void setStarted(String started) {
        this.started = started;
    }

    public Integer getSaleConfigInfoId() {
        return saleConfigInfoId;
    }

    public void setSaleConfigInfoId(Integer saleConfigInfoId) {
        this.saleConfigInfoId = saleConfigInfoId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getSaleRoomTypeId() {
        return saleRoomTypeId;
    }

    public void setSaleRoomTypeId(Integer saleRoomTypeId) {
        this.saleRoomTypeId = saleRoomTypeId;
    }
}
