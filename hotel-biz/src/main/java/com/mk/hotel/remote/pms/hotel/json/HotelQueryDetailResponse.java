package com.mk.hotel.remote.pms.hotel.json;

import com.mk.hotel.remote.pms.common.FbbCommonResponse;

import java.util.List;

/**
 * Created by chenqi on 16/5/11.
 */
public class HotelQueryDetailResponse extends FbbCommonResponse{
    private Hotel data;

    public Hotel getData() {
        return data;
    }

    public void setData(Hotel data) {
        this.data = data;
    }

    class Hotel{
        private HotelInfo hotel;

        public HotelInfo getHotel() {
            return hotel;
        }

        public void setHotel(HotelInfo hotel) {
            this.hotel = hotel;
        }
    }

    class HotelInfo{
        private int citycode;
        private String cityname;
        private String defaultleavetime;
        private String detailaddr;
        private int discode;
        private String districtname;
        private String hotelname;
        private String hotelpic;
        private String hotelpics;
        private int hoteltype;
        private int id;
        private String introduction;
        private double latitude;
        private double longitude;
        private int provcode;
        private String provincename;
        private String retentiontime;
        private List<Roomtypes> roomtypes;


        public void setCitycode(int citycode) {
            this.citycode = citycode;
        }
        public int getCitycode() {
            return citycode;
        }


        public void setCityname(String cityname) {
            this.cityname = cityname;
        }
        public String getCityname() {
            return cityname;
        }


        public void setDefaultleavetime(String defaultleavetime) {
            this.defaultleavetime = defaultleavetime;
        }
        public String getDefaultleavetime() {
            return defaultleavetime;
        }


        public void setDetailaddr(String detailaddr) {
            this.detailaddr = detailaddr;
        }
        public String getDetailaddr() {
            return detailaddr;
        }


        public void setDiscode(int discode) {
            this.discode = discode;
        }
        public int getDiscode() {
            return discode;
        }


        public void setDistrictname(String districtname) {
            this.districtname = districtname;
        }
        public String getDistrictname() {
            return districtname;
        }


        public void setHotelname(String hotelname) {
            this.hotelname = hotelname;
        }
        public String getHotelname() {
            return hotelname;
        }


        public void setHotelpic(String hotelpic) {
            this.hotelpic = hotelpic;
        }
        public String getHotelpic() {
            return hotelpic;
        }


        public void setHotelpics(String hotelpics) {
            this.hotelpics = hotelpics;
        }
        public String getHotelpics() {
            return hotelpics;
        }


        public void setHoteltype(int hoteltype) {
            this.hoteltype = hoteltype;
        }
        public int getHoteltype() {
            return hoteltype;
        }


        public void setId(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }


        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }
        public String getIntroduction() {
            return introduction;
        }


        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }
        public double getLatitude() {
            return latitude;
        }


        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
        public double getLongitude() {
            return longitude;
        }


        public void setProvcode(int provcode) {
            this.provcode = provcode;
        }
        public int getProvcode() {
            return provcode;
        }


        public void setProvincename(String provincename) {
            this.provincename = provincename;
        }
        public String getProvincename() {
            return provincename;
        }


        public void setRetentiontime(String retentiontime) {
            this.retentiontime = retentiontime;
        }
        public String getRetentiontime() {
            return retentiontime;
        }


        public void setRoomtypes(List<Roomtypes> roomtypes) {
            this.roomtypes = roomtypes;
        }
        public List<Roomtypes> getRoomtypes() {
            return roomtypes;
        }
    }

    class Roomtypes{
        private int id;
        private String name;
        private int roomnum;


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


        public void setRoomnum(int roomnum) {
            this.roomnum = roomnum;
        }
        public int getRoomnum() {
            return roomnum;
        }
    }
}
