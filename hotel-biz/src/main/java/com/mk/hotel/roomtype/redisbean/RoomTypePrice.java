package com.mk.hotel.roomtype.redisbean;

import java.math.BigDecimal;

/**
 * Created by huangjie on 16/5/16.
 */
public class RoomTypePrice {
    private Long roomTypeId;

    private String roomTypeName;

    private BigDecimal price;

    private BigDecimal originPrice;

    private Integer promoOption;

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(BigDecimal originPrice) {
        this.originPrice = originPrice;
    }

    public Integer getPromoOption() {
        return promoOption;
    }

    public void setPromoOption(Integer promoOption) {
        this.promoOption = promoOption;
    }
}
