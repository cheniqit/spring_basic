package com.mk.hotel.remote.pms.hotelstock.json;


import com.mk.hotel.remote.pms.common.FbbCommonResponse;

import java.util.Date;
import java.util.List;

/**
 * Created by chenqi on 16/5/10.
 */
public class QueryStockResponse extends FbbCommonResponse {
    private Roominfo data;

    public Roominfo getData() {
        return data;
    }

    public void setData(Roominfo data) {
        this.data = data;
    }

    public class Roominfo{
        private List<Roomtypestocks> roomtypestocks;


        public void setRoomtypestocks(List<Roomtypestocks> roomtypestocks) {
            this.roomtypestocks = roomtypestocks;
        }
        public List<Roomtypestocks> getRoomtypestocks() {
            return roomtypestocks;
        }
    }

    public class Roomtypestocks {

        private int roomtypeid;
        private List<Stockinfos> stockinfos;


        public void setRoomtypeid(int roomtypeid) {
            this.roomtypeid = roomtypeid;
        }
        public int getRoomtypeid() {
            return roomtypeid;
        }


        public void setStockinfos(List<Stockinfos> stockinfos) {
            this.stockinfos = stockinfos;
        }
        public List<Stockinfos> getStockinfos() {
            return stockinfos;
        }

    }


    public class Stockinfos {

        private String date;
        private String num;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setNum(String num) {
            this.num = num;
        }
        public String getNum() {
            return num;
        }

    }
}
