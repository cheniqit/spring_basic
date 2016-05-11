package com.mk.hotel.remote.hotelstock.json;


import com.mk.hotel.remote.common.FbbCommonResponse;

import java.util.List;

/**
 * Created by chenqi on 16/5/10.
 */
public class QueryStockResponse extends FbbCommonResponse {
    private Roominfo roominfo;

    class Roominfo{
        private String roomtypeid;
        private List<StockInfo> stockInfos;

        class StockInfo{
            private String date;
            private Integer num;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public Integer getNum() {
                return num;
            }

            public void setNum(Integer num) {
                this.num = num;
            }
        }

        public String getRoomtypeid() {
            return roomtypeid;
        }

        public void setRoomtypeid(String roomtypeid) {
            this.roomtypeid = roomtypeid;
        }

        public List<StockInfo> getStockInfos() {
            return stockInfos;
        }

        public void setStockInfos(List<StockInfo> stockInfos) {
            this.stockInfos = stockInfos;
        }
    }
}
