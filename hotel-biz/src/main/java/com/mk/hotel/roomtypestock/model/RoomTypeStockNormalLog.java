package com.mk.hotel.roomtypestock.model;

import java.util.Date;

public class RoomTypeStockNormalLog {
    private Long id;

    private Long roomTypeId;

    private Long monTotalNumber;

    private Long tueTotalNumber;

    private Long wedTotalNumber;

    private Long thuTotalNumber;

    private Long friTotalNumber;

    private Long satTotalNumber;

    private Long sunTotalNumber;

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

    public Long getMonTotalNumber() {
        return monTotalNumber;
    }

    public void setMonTotalNumber(Long monTotalNumber) {
        this.monTotalNumber = monTotalNumber;
    }

    public Long getTueTotalNumber() {
        return tueTotalNumber;
    }

    public void setTueTotalNumber(Long tueTotalNumber) {
        this.tueTotalNumber = tueTotalNumber;
    }

    public Long getWedTotalNumber() {
        return wedTotalNumber;
    }

    public void setWedTotalNumber(Long wedTotalNumber) {
        this.wedTotalNumber = wedTotalNumber;
    }

    public Long getThuTotalNumber() {
        return thuTotalNumber;
    }

    public void setThuTotalNumber(Long thuTotalNumber) {
        this.thuTotalNumber = thuTotalNumber;
    }

    public Long getFriTotalNumber() {
        return friTotalNumber;
    }

    public void setFriTotalNumber(Long friTotalNumber) {
        this.friTotalNumber = friTotalNumber;
    }

    public Long getSatTotalNumber() {
        return satTotalNumber;
    }

    public void setSatTotalNumber(Long satTotalNumber) {
        this.satTotalNumber = satTotalNumber;
    }

    public Long getSunTotalNumber() {
        return sunTotalNumber;
    }

    public void setSunTotalNumber(Long sunTotalNumber) {
        this.sunTotalNumber = sunTotalNumber;
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