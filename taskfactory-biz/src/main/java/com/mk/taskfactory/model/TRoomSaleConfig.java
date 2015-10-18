package com.mk.taskfactory.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

public class TRoomSaleConfig {
    private Integer id;
    private Integer hotelId;
    private Integer roomId;
    private Integer roomTypeId;
    private Time startTime;
    private Time endTime;
    private Date startDate;
    private Date endDate;
    private BigDecimal saleValue;
    private Integer num;
    private Integer type;
    private String saleName;
    private Integer saleRoomTypeId;
    private BigDecimal settleValue;
    private Integer settleType;
    private String valid;
    private Integer styleType;
    private String started;

    public String getStarted() {
        return started;
    }

    public void setStarted(String started) {
        this.started = started;
    }


    public Integer getStyleType() {
        return styleType;
    }

    public void setStyleType(Integer styleType) {
        this.styleType = styleType;
    }



    public Integer getSaleRoomTypeId() {
        return saleRoomTypeId;
    }

    public void setSaleRoomTypeId(Integer saleRoomTypeId) {
        this.saleRoomTypeId = saleRoomTypeId;
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

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getSaleValue() {
        return saleValue;
    }

    public void setSaleValue(BigDecimal saleValue) {
        this.saleValue = saleValue;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
}
