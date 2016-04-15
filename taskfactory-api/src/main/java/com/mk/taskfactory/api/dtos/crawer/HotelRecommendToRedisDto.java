package com.mk.taskfactory.api.dtos.crawer;

import java.math.BigDecimal;

public class HotelRecommendToRedisDto {
    private Long hotelId;
    private Long roomTypeId;
    private Long recHotelId;
    private Long recRoomTypeId;
    private BigDecimal recPrice;
    private Integer recSort;

    private String isValid;

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Long getRecHotelId() {
        return recHotelId;
    }

    public void setRecHotelId(Long recHotelId) {
        this.recHotelId = recHotelId;
    }

    public Long getRecRoomTypeId() {
        return recRoomTypeId;
    }

    public void setRecRoomTypeId(Long recRoomTypeId) {
        this.recRoomTypeId = recRoomTypeId;
    }

    public BigDecimal getRecPrice() {
        return recPrice;
    }

    public void setRecPrice(BigDecimal recPrice) {
        this.recPrice = recPrice;
    }

    public Integer getRecSort() {
        return recSort;
    }

    public void setRecSort(Integer recSort) {
        this.recSort = recSort;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }
}