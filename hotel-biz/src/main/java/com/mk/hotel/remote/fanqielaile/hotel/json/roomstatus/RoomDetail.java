package com.mk.hotel.remote.fanqielaile.hotel.json.roomstatus;

import java.util.Date;

/**
 * Created by huangjie on 16/6/7.
 */
public class RoomDetail {
    private Date roomDate;
    private Integer roomPrice;
    private Integer priRoomPrice;
    private Integer roomNum;

    public Date getRoomDate() {
        return roomDate;
    }

    public void setRoomDate(Date roomDate) {
        this.roomDate = roomDate;
    }

    public Integer getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Integer roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Integer getPriRoomPrice() {
        return priRoomPrice;
    }

    public void setPriRoomPrice(Integer priRoomPrice) {
        this.priRoomPrice = priRoomPrice;
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }
}
