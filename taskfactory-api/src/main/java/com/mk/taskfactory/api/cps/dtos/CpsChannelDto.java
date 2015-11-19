package com.mk.taskfactory.api.cps.dtos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2015/10/17.
 */
public class CpsChannelDto {
    private Integer hotelId;
    private String hotelName;
    private BigDecimal price;
    private BigDecimal pmsPrice;
    private List<Integer> roomIdList = new ArrayList<Integer>();

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPmsPrice() {
        return pmsPrice;
    }

    public void setPmsPrice(BigDecimal pmsPrice) {
        this.pmsPrice = pmsPrice;
    }

    public List<Integer> getRoomIdList() {
        return roomIdList;
    }

    public void setRoomIdList(List<Integer> roomIdList) {
        this.roomIdList = roomIdList;
    }

}
