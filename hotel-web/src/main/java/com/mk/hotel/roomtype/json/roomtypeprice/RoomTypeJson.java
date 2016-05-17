package com.mk.hotel.roomtype.json.roomtypeprice;

import java.util.List;

/**
 * Created by huangjie on 16/5/10.
 */
public class RoomTypeJson {

    private Long roomtypeid;

    private List<PriceInfoJson> priceinfos;

    public Long getRoomtypeid() {
        return roomtypeid;
    }

    public void setRoomtypeid(Long roomtypeid) {
        this.roomtypeid = roomtypeid;
    }

    public List<PriceInfoJson> getPriceinfos() {
        return priceinfos;
    }

    public void setPriceinfos(List<PriceInfoJson> priceinfos) {
        this.priceinfos = priceinfos;
    }
}
