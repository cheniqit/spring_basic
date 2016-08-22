package com.mk.hotel.roomtype.model;

import java.util.Date;

public class RoomTypeFullStockLog {
    private Long id;

    private Long roomTypeId;

    private Date day;

    private Long totalNumber;

    private Long totalPromoNumber;

    private Long normalNumber;

    private Long promoNumber;

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

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Long getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Long totalNumber) {
        this.totalNumber = totalNumber;
    }

    public Long getTotalPromoNumber() {
        return totalPromoNumber;
    }

    public void setTotalPromoNumber(Long totalPromoNumber) {
        this.totalPromoNumber = totalPromoNumber;
    }

    public Long getNormalNumber() {
        return normalNumber;
    }

    public void setNormalNumber(Long normalNumber) {
        this.normalNumber = normalNumber;
    }

    public Long getPromoNumber() {
        return promoNumber;
    }

    public void setPromoNumber(Long promoNumber) {
        this.promoNumber = promoNumber;
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