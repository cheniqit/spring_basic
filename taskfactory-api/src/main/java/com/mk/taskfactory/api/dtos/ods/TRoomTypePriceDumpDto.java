package com.mk.taskfactory.api.dtos.ods;

import java.math.BigDecimal;
import java.math.BigInteger;

public class TRoomTypePriceDumpDto {
    private BigInteger id;
    private BigInteger hotelId;
    private BigInteger roomTypeId;
    private String hotelName;
    private String roomTypeName;
    private BigDecimal marketPrice;
    private BigDecimal mkPrice;
    private Boolean isPromo;
    private Integer promoId;
    private BigDecimal promoPrice;
    private BigDecimal settlePrice;
    private String statisticDate;
    private String createDate;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getHotelId() {
        return hotelId;
    }

    public void setHotelId(BigInteger hotelId) {
        this.hotelId = hotelId;
    }

    public BigInteger getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(BigInteger roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getMkPrice() {
        return mkPrice;
    }

    public void setMkPrice(BigDecimal mkPrice) {
        this.mkPrice = mkPrice;
    }

    public Boolean getIsPromo() {
        return isPromo;
    }

    public void setIsPromo(Boolean isPromo) {
        this.isPromo = isPromo;
    }

    public Integer getPromoId() {
        return promoId;
    }

    public void setPromoId(Integer promoId) {
        this.promoId = promoId;
    }

    public BigDecimal getPromoPrice() {
        return promoPrice;
    }

    public void setPromoPrice(BigDecimal promoPrice) {
        this.promoPrice = promoPrice;
    }

    public BigDecimal getSettlePrice() {
        return settlePrice;
    }

    public void setSettlePrice(BigDecimal settlePrice) {
        this.settlePrice = settlePrice;
    }

    public String getStatisticDate() {
        return statisticDate;
    }

    public void setStatisticDate(String statisticDate) {
        this.statisticDate = statisticDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
