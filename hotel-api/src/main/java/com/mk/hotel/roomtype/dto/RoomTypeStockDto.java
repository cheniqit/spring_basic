package com.mk.hotel.roomtype.dto;

import java.util.Date;

/**
 * Created by huangjie on 16/8/15.
 */
public class RoomTypeStockDto {
    //签约总房量
    private Integer totalNum;
    //计划特价房量
    private Integer totalPromoNum;
    //可销售普通房数量
    private Integer availableNum ;
    //可销售特价房数量
    private Integer promoNum;

    private Long roomTypeId;

    private Date day;

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getTotalPromoNum() {
        return totalPromoNum;
    }

    public void setTotalPromoNum(Integer totalPromoNum) {
        this.totalPromoNum = totalPromoNum;
    }

    public Integer getAvailableNum() {
        return availableNum;
    }

    public void setAvailableNum(Integer availableNum) {
        this.availableNum = availableNum;
    }

    public Integer getPromoNum() {
        return promoNum;
    }

    public void setPromoNum(Integer promoNum) {
        this.promoNum = promoNum;
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
}
