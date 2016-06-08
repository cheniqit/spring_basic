package com.mk.hotel.hotelinfo.bean;

import com.mk.framework.JsonUtils;

import java.util.List;

/**
 * Created by chenqi on 16/6/8.
 */
public class HotelPicInfo {
    private boolean success;
    private List<Data> data;


    public void setSuccess(boolean success) {
        this.success = success;
    }
    public boolean getSuccess() {
        return success;
    }


    public void setData(List<Data> data) {
        this.data = data;
    }
    public List<Data> getData() {
        return data;
    }



    public class Pic {

        private String url;


        public void setUrl(String url) {
            this.url = url;
        }
        public String getUrl() {
            return url;
        }

    }


    public class Data {

        private String name;
        private List<Pic> pic;
        private String roomTypeId;
        private String roomTypeName;
        private List<RoomTypePic> roomTypePic;


        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }


        public void setPic(List<Pic> pic) {
            this.pic = pic;
        }
        public List<Pic> getPic() {
            return pic;
        }


        public String getRoomTypeId() {
            return roomTypeId;
        }

        public void setRoomTypeId(String roomTypeId) {
            this.roomTypeId = roomTypeId;
        }

        public void setRoomTypeName(String roomTypeName) {
            this.roomTypeName = roomTypeName;
        }
        public String getRoomTypeName() {
            return roomTypeName;
        }


        public void setRoomTypePic(List<RoomTypePic> roomTypePic) {
            this.roomTypePic = roomTypePic;
        }
        public List<RoomTypePic> getRoomTypePic() {
            return roomTypePic;
        }

    }

    public class RoomTypePic {

        private String name;
        private List<Pic> pic;


        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }


        public void setPic(List<Pic> pic) {
            this.pic = pic;
        }
        public List<Pic> getPic() {
            return pic;
        }

    }

    public static void main(String[] arg){
        String s = "\n" +
                "{\"success\":true,\"data\":[{\"name\":\"def\",\"pic\":[{\"url\":\"https://dn-imke-pro.qbox.me/d1970ddc-3857-49b7-861b-4cdb2dda8fe8500.jpg\"}]},{\"roomTypeId\":31000000600877,\"roomTypeName\":\"特价房\",\"roomTypePic\":[{\"name\":\"def\",\"pic\":[{}]}]},{\"roomTypeId\":1371957,\"roomTypeName\":\"双人房\",\"roomTypePic\":[{\"name\":\"def\",\"pic\":[{\"url\":\"https://dn-imke-pro.qbox.me/4975799f-9a34-4f59-9692-9f9440ae7f33500.jpg\"}]}]},{\"roomTypeId\":1371956,\"roomTypeName\":\"单人房\",\"roomTypePic\":[{\"name\":\"def\",\"pic\":[{\"url\":\"https://dn-imke-pro.qbox.me/87955dd0-de40-445f-aa89-9f3b21e5d8fc500.jpg\"}]}]}]}";
        HotelPicInfo hotelPicInfo = JsonUtils.fromJson(s, HotelPicInfo.class);
        System.out.print(hotelPicInfo);
    }
}
