package com.mk.taskfactory.api.dtos;

import java.math.BigDecimal;

public class TRoomTypeDto {
    private Integer id;
    private Integer thotelId;
    private Integer ehotelId;
    private String name;
    private String pms;
    private Integer bedNum;
    private Integer roomNum;
    private BigDecimal cost;
    private Integer pageIndex; // 开始行数
    private Integer pageSize; // 每页显示数量
    private String hotelName;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getThotelId() {
        return thotelId;
    }

    public void setThotelId(Integer thotelId) {
        this.thotelId = thotelId;
    }

    public Integer getEhotelId() {
        return ehotelId;
    }

    public void setEhotelId(Integer ehotelId) {
        this.ehotelId = ehotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPms() {
        return pms;
    }

    public void setPms(String pms) {
        this.pms = pms;
    }

    public Integer getBedNum() {
        return bedNum;
    }

    public void setBedNum(Integer bedNum) {
        this.bedNum = bedNum;
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
}
