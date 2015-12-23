package com.mk.taskfactory.model.ods;

import java.math.BigDecimal;
import java.math.BigInteger;

public class TRoomPriceContrast {
    private BigInteger id;
    private BigInteger hotelId;
    private BigInteger roomTypeId;
    private String hotelName;
    private String roomTypeName;
    private BigDecimal oldMarketPrice;
    private BigDecimal newMarketPrice;
    private BigDecimal oldMkPrice;
    private BigDecimal newMkPrice;
    private Boolean isPromo;
    private Integer promoId;
    private BigDecimal oldPromoPrice;
    private BigDecimal newPromoPrice;
    private BigDecimal oldSettlePrice;
    private BigDecimal newSettlePrice;
    private String contrastDate;
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

    public BigDecimal getOldMarketPrice() {
        return oldMarketPrice;
    }

    public void setOldMarketPrice(BigDecimal oldMarketPrice) {
        this.oldMarketPrice = oldMarketPrice;
    }

    public BigDecimal getNewMarketPrice() {
        return newMarketPrice;
    }

    public void setNewMarketPrice(BigDecimal newMarketPrice) {
        this.newMarketPrice = newMarketPrice;
    }

    public BigDecimal getOldMkPrice() {
        return oldMkPrice;
    }

    public void setOldMkPrice(BigDecimal oldMkPrice) {
        this.oldMkPrice = oldMkPrice;
    }

    public BigDecimal getNewMkPrice() {
        return newMkPrice;
    }

    public void setNewMkPrice(BigDecimal newMkPrice) {
        this.newMkPrice = newMkPrice;
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

    public BigDecimal getOldPromoPrice() {
        return oldPromoPrice;
    }

    public void setOldPromoPrice(BigDecimal oldPromoPrice) {
        this.oldPromoPrice = oldPromoPrice;
    }

    public BigDecimal getNewPromoPrice() {
        return newPromoPrice;
    }

    public void setNewPromoPrice(BigDecimal newPromoPrice) {
        this.newPromoPrice = newPromoPrice;
    }

    public BigDecimal getOldSettlePrice() {
        return oldSettlePrice;
    }

    public void setOldSettlePrice(BigDecimal oldSettlePrice) {
        this.oldSettlePrice = oldSettlePrice;
    }

    public BigDecimal getNewSettlePrice() {
        return newSettlePrice;
    }

    public void setNewSettlePrice(BigDecimal newSettlePrice) {
        this.newSettlePrice = newSettlePrice;
    }

    public String getContrastDate() {
        return contrastDate;
    }

    public void setContrastDate(String contrastDate) {
        this.contrastDate = contrastDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
