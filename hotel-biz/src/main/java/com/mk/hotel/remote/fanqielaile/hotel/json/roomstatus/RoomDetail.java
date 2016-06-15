package com.mk.hotel.remote.fanqielaile.hotel.json.roomstatus;

import java.math.BigDecimal;

/**
 * Created by huangjie on 16/6/7.
 */
public class RoomDetail {
    private String roomDate;
    private BigDecimal roomPrice;
    private BigDecimal priRoomPrice;
    private Integer roomNum;

    public String getRoomDate() {
        return roomDate;
    }

    public void setRoomDate(String roomDate) {
        this.roomDate = roomDate;
    }

    public BigDecimal getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(BigDecimal roomPrice) {
        this.roomPrice = roomPrice;
    }

    public BigDecimal getPriRoomPrice() {
        return priRoomPrice;
    }

    public void setPriRoomPrice(BigDecimal priRoomPrice) {
        this.priRoomPrice = priRoomPrice;
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }
}
