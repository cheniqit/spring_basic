package com.mk.hotel.hotelinfo.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by chenqi on 16/10/24.
 */
public class UpdatePriceAndStock{
    private String userId;
    private String token;
    private Long hotelId;
    private Long roomTypeId;
    private List<DateList> dateList;

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

    public void setDateList(List<DateList> dateList) {
        this.dateList = dateList;
    }
    public List<DateList> getDateList() {
        return dateList;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static class DateList {
        private Date day;
        private Long number;
        private BigDecimal settlementPrice;
        private BigDecimal prePayPrice;
        private BigDecimal price;


        public void setDay(Date day) {
            this.day = day;
        }
        public Date getDay() {
            return day;
        }

        public Long getNumber() {
            return number;
        }

        public void setNumber(Long number) {
            this.number = number;
        }

        public BigDecimal getSettlementPrice() {
            return settlementPrice;
        }

        public void setSettlementPrice(BigDecimal settlementPrice) {
            this.settlementPrice = settlementPrice;
        }

        public BigDecimal getPrePayPrice() {
            return prePayPrice;
        }

        public void setPrePayPrice(BigDecimal prePayPrice) {
            this.prePayPrice = prePayPrice;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }
    }
}