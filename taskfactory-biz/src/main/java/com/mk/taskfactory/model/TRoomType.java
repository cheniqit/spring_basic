package com.mk.taskfactory.model;

import java.math.BigDecimal;

public class TRoomType {
    private Integer id;
    private Integer thotelId;
    private Integer ehotelId;
    private String name;
    private String pms;
    private Integer bedNum;
    private Integer roomNum;
    private BigDecimal cost;

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
}
