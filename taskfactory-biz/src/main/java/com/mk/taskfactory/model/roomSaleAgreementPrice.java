package com.mk.taskfactory.model;

import java.math.BigDecimal;
import java.util.Date;

public class RoomSaleAgreementPrice {
    private Integer id;

    private Integer hotelId;

    private String hotelName;

    private Integer roomTypeId;

    private String roomTypeName;

    private String roomNo;

    private BigDecimal settleValue;

    private Integer settleType;

    private BigDecimal meiTuanPrice;

    private Integer saleTypeId;

    private Integer saleConfigInfoId;

    private Integer dealCount;

    private Integer storeCount;

    private String isLeZhu;

    private String valid;

    private Date createTime;

    private Date updateTime;

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

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Integer getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Integer roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
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

    public Integer getSaleTypeId() {
        return saleTypeId;
    }

    public void setSaleTypeId(Integer saleTypeId) {
        this.saleTypeId = saleTypeId;
    }

    public Integer getSaleConfigInfoId() {
        return saleConfigInfoId;
    }

    public void setSaleConfigInfoId(Integer saleConfigInfoId) {
        this.saleConfigInfoId = saleConfigInfoId;
    }

    public Integer getDealCount() {
        return dealCount;
    }

    public void setDealCount(Integer dealCount) {
        this.dealCount = dealCount;
    }

    public Integer getStoreCount() {
        return storeCount;
    }

    public void setStoreCount(Integer storeCount) {
        this.storeCount = storeCount;
    }

    public String getIsLeZhu() {
        return isLeZhu;
    }

    public void setIsLeZhu(String isLeZhu) {
        this.isLeZhu = isLeZhu;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
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

    public BigDecimal getMeiTuanPrice() {
        return meiTuanPrice;
    }

    public void setMeiTuanPrice(BigDecimal meiTuanPrice) {
        this.meiTuanPrice = meiTuanPrice;
    }
}