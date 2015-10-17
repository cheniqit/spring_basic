package com.mk.taskfactory.api.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class TBasePriceDto {
    private Integer id;
    private Integer roomTypeId;
    private BigDecimal price;
    private String subprice;
    private String subper;
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Integer roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSubprice() {
        return subprice;
    }

    public void setSubprice(String subprice) {
        this.subprice = subprice;
    }

    public String getSubper() {
        return subper;
    }

    public void setSubper(String subper) {
        this.subper = subper;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
