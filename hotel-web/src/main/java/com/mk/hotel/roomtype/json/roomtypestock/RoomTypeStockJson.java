package com.mk.hotel.roomtype.json.roomtypestock;

import java.util.List;

/**
 * Created by huangjie on 16/5/10.
 */
public class RoomTypeStockJson {

    private Long roomtypeid;
    private List<StockInfoJson> stockInfos;

    public Long getRoomtypeid() {
        return roomtypeid;
    }

    public void setRoomtypeid(Long roomtypeid) {
        this.roomtypeid = roomtypeid;
    }

    public List<StockInfoJson> getStockInfos() {
        return stockInfos;
    }

    public void setStockInfos(List<StockInfoJson> stockInfos) {
        this.stockInfos = stockInfos;
    }
}
