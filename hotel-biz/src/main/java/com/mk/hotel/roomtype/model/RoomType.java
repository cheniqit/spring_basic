package com.mk.hotel.roomtype.model;

import java.util.Date;

public class RoomType {
    private Long id;

    private Long fangId;

    private Long hotelId;

    private String name;

    private String area;

    private Short bedType;

    private Short roomNum;

    private Short prepay;

    private Short breakfast;

    private Short refund;

    private Short maxRoomNum;

    private Short roomTypePics;

    private Date createDate;

    private String createBy;

    private Date updateDate;

    private String updateBy;

    private String isValid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFangId() {
        return fangId;
    }

    public void setFangId(Long fangId) {
        this.fangId = fangId;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public Short getBedType() {
        return bedType;
    }

    public void setBedType(Short bedType) {
        this.bedType = bedType;
    }

    public Short getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Short roomNum) {
        this.roomNum = roomNum;
    }

    public Short getPrepay() {
        return prepay;
    }

    public void setPrepay(Short prepay) {
        this.prepay = prepay;
    }

    public Short getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(Short breakfast) {
        this.breakfast = breakfast;
    }

    public Short getRefund() {
        return refund;
    }

    public void setRefund(Short refund) {
        this.refund = refund;
    }

    public Short getMaxRoomNum() {
        return maxRoomNum;
    }

    public void setMaxRoomNum(Short maxRoomNum) {
        this.maxRoomNum = maxRoomNum;
    }

    public Short getRoomTypePics() {
        return roomTypePics;
    }

    public void setRoomTypePics(Short roomTypePics) {
        this.roomTypePics = roomTypePics;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }
}