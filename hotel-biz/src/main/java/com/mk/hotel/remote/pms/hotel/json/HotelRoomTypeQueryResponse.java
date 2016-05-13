package com.mk.hotel.remote.pms.hotel.json;



import com.mk.hotel.remote.pms.common.FbbCommonResponse;

import java.util.List;

/**
 * Created by chenqi on 16/5/11.
 */
public class HotelRoomTypeQueryResponse extends FbbCommonResponse {
    private List<HotelRoomType> data;

    public List<HotelRoomType> getData() {
        return data;
    }

    public void setData(List<HotelRoomType> data) {
        this.data = data;
    }

    class HotelRoomType{
        private String area;
        private String bedtype;
        private String breakfast;
        private int id;
        private String name;
        private String prepay;
        private int roomnum;
        private String roomtypepics;


        public void setArea(String area) {
            this.area = area;
        }
        public String getArea() {
            return area;
        }


        public void setBedtype(String bedtype) {
            this.bedtype = bedtype;
        }
        public String getBedtype() {
            return bedtype;
        }


        public void setBreakfast(String breakfast) {
            this.breakfast = breakfast;
        }
        public String getBreakfast() {
            return breakfast;
        }


        public void setId(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }


        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }


        public void setPrepay(String prepay) {
            this.prepay = prepay;
        }
        public String getPrepay() {
            return prepay;
        }


        public void setRoomnum(int roomnum) {
            this.roomnum = roomnum;
        }
        public int getRoomnum() {
            return roomnum;
        }


        public void setRoomtypepics(String roomtypepics) {
            this.roomtypepics = roomtypepics;
        }
        public String getRoomtypepics() {
            return roomtypepics;
        }
    }



    class Roomtypes{
        private String area;
        private String bedtype;
        private String breakfast;
        private int id;
        private String name;
        private String prepay;
        private int roomnum;
        private String roomtypepics;


        public void setArea(String area) {
            this.area = area;
        }
        public String getArea() {
            return area;
        }


        public void setBedtype(String bedtype) {
            this.bedtype = bedtype;
        }
        public String getBedtype() {
            return bedtype;
        }


        public void setBreakfast(String breakfast) {
            this.breakfast = breakfast;
        }
        public String getBreakfast() {
            return breakfast;
        }


        public void setId(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }


        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }


        public void setPrepay(String prepay) {
            this.prepay = prepay;
        }
        public String getPrepay() {
            return prepay;
        }


        public void setRoomnum(int roomnum) {
            this.roomnum = roomnum;
        }
        public int getRoomnum() {
            return roomnum;
        }


        public void setRoomtypepics(String roomtypepics) {
            this.roomtypepics = roomtypepics;
        }
        public String getRoomtypepics() {
            return roomtypepics;
        }
    }
}
