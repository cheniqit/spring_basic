package com.mk.hotel.roomtype.model;

import java.util.Date;

public class RoomType {
    private Long id;

    private Long fangId;

    private Long hotelId;

    private String name;

    private Integer area;

    private Integer bedType;

    private String bedSize;

    private Integer roomNum;

    private Integer prepay;

    private Integer breakfast;

    private Integer status;

    private Integer refund;

    private Integer maxRoomNum;

    private Date createDate;

    private String createBy;

    private Date updateDate;

    private String updateBy;

    private String isValid;

    private String picsSign;

    private String roomTypePics;

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

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getBedType() {
        return bedType;
    }

    public void setBedType(Integer bedType) {
        this.bedType = bedType;
    }

    public String getBedSize() {
        return bedSize;
    }

    public void setBedSize(String bedSize) {
        this.bedSize = bedSize;
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
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

    public Integer getMaxRoomNum() {
        return maxRoomNum;
    }

    public void setMaxRoomNum(Integer maxRoomNum) {
        this.maxRoomNum = maxRoomNum;
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
        this.createBy = createBy;
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
        this.updateBy = updateBy;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public String getPicsSign() {
        return picsSign;
    }

    public void setPicsSign(String picsSign) {
        this.picsSign = picsSign;
    }

    public String getRoomTypePics() {
        return roomTypePics;
    }

    public void setRoomTypePics(String roomTypePics) {
        this.roomTypePics = roomTypePics;
    }
}