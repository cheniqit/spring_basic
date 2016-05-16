package com.mk.hotel.roomtype.dto;

import java.util.Date;

public class RoomTypeFacilityDto {
    private Long id;

    //for json
    private Long fangHotelId;
    //for json
    private Long fangRoomTypeId;

    private Long roomTypeId;

    private Long facilityId;

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

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Long getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Long facilityId) {
        this.facilityId = facilityId;
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

    public Long getFangRoomTypeId() {
        return fangRoomTypeId;
    }

    public void setFangRoomTypeId(Long fangRoomTypeId) {
        this.fangRoomTypeId = fangRoomTypeId;
    }

    public Long getFangHotelId() {
        return fangHotelId;
    }

    public void setFangHotelId(Long fangHotelId) {
        this.fangHotelId = fangHotelId;
    }
}