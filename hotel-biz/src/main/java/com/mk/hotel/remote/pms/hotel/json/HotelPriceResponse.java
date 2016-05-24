package com.mk.hotel.remote.pms.hotel.json;

import com.mk.framework.JsonUtils;
import com.mk.hotel.remote.pms.common.FbbCommonResponse;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Created by chenqi on 16/5/11.
 */
public class HotelPriceResponse extends FbbCommonResponse{
    private HotelPrice data;

    public HotelPrice getData() {
        return data;
    }

    public void setData(HotelPrice data) {
        this.data = data;
    }

    public class HotelPrice{
        private List<Roomtypeprices> roomtypeprices;


        public void setRoomtypeprices(List<Roomtypeprices> roomtypeprices) {
            this.roomtypeprices = roomtypeprices;
        }
        public List<Roomtypeprices> getRoomtypeprices() {
            return roomtypeprices;
        }
    }

    public class Roomtypeprices {

        private List<Priceinfos> priceinfos;
        private int roomtypeid;


        public void setPriceinfos(List<Priceinfos> priceinfos) {
            this.priceinfos = priceinfos;
        }
        public List<Priceinfos> getPriceinfos() {
            return priceinfos;
        }


        public void setRoomtypeid(int roomtypeid) {
            this.roomtypeid = roomtypeid;
        }
        public int getRoomtypeid() {
            return roomtypeid;
        }

    }

    public class Priceinfos {

        private String cost;
        private String price;
        private String date;


        public void setCost(String cost) {
            this.cost = cost;
        }
        public String getCost() {
            return cost;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setDate(String date) {
            this.date = date;
        }
        public String getDate() {
            return date;
        }

        public Cost getCostObject(){
            if(StringUtils.isNotBlank(cost)){
                return JsonUtils.fromJson(this.getCost(), Cost.class);
            }
            return null;
        }

    }

}
