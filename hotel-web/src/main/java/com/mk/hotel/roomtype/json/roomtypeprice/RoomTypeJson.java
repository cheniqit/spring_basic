package com.mk.hotel.roomtype.json.roomtypeprice;

import java.util.List;

/**
 * Created by huangjie on 16/5/10.
 */
public class RoomTypeJson {

    private Long roomtypeid;

    private List<PriceInfoJson> priceinfo;

    public Long getRoomtypeid() {
        return roomtypeid;
    }

    public void setRoomtypeid(Long roomtypeid) {
        this.roomtypeid = roomtypeid;
    }

    public List<PriceInfoJson> getPriceinfo() {
        return priceinfo;
    }

    public void setPriceinfo(List<PriceInfoJson> priceinfo) {
        this.priceinfo = priceinfo;
    }
}
