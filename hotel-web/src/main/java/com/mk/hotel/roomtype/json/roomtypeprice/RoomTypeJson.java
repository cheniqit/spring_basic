package com.mk.hotel.roomtype.json.roomtypeprice;

import java.util.List;

/**
 * Created by huangjie on 16/5/10.
 */
public class RoomTypeJson {

    private Long roomTypeId;

    private List<PriceInfoJson> priceinfo;

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public List<PriceInfoJson> getPriceinfo() {
        return priceinfo;
    }

    public void setPriceinfo(List<PriceInfoJson> priceinfo) {
        this.priceinfo = priceinfo;
    }
}
