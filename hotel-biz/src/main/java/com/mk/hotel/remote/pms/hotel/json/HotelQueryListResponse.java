package com.mk.hotel.remote.pms.hotel.json;

import com.mk.hotel.remote.pms.common.FbbCommonResponse;

import java.util.List;

/**
 * Created by chenqi on 16/5/11.
 */
public class HotelQueryListResponse extends FbbCommonResponse{
    private hotels data;

    public hotels getData() {
        return data;
    }

    public void setData(hotels data) {
        this.data = data;
    }

    class hotels{
        private List<HotelInfo> hotels;

        public List<HotelInfo> getHotels() {
            return hotels;
        }

        public void setHotels(List<HotelInfo> hotels) {
            this.hotels = hotels;
        }
    }

    class HotelInfo{
        private int citycode;
        private String cityname;
        private String detailaddr;
        private int discode;
        private String districtname;
        private String hotelname;
        private String hotelpics;
        private int hoteltype;
        private int id;
        private double latitude;
        private double longitude;
        private int provcode;
        private String provincename;


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
    }

}
