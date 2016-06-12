package com.mk.hotel.order.controller.json;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

/**
 * Created by chenqi on 16/6/8.
 */
@XStreamAlias(value="PushRoomType")
public class PushRoomType {
    @XStreamAlias(value="list")
    public List<RoomType> RoomType;

    public List<PushRoomType.RoomType> getRoomType() {
        return RoomType;
    }

    public void setRoomType(List<PushRoomType.RoomType> roomType) {
        RoomType = roomType;
    }
    @XStreamAlias(value="RoomType")
    public static class RoomType{
        private String AccountId;
        private String RoomTypeId;
        private String RoomTypeName;
        @XStreamAlias(value="RoomDetails")
        public List<RoomDetail> RoomDetails;

        public String getAccountId() {
            return AccountId;
        }

        public void setAccountId(String accountId) {
            AccountId = accountId;
        }

        public String getRoomTypeId() {
            return RoomTypeId;
        }

        public void setRoomTypeId(String roomTypeId) {
            RoomTypeId = roomTypeId;
        }

        public String getRoomTypeName() {
            return RoomTypeName;
        }

        public void setRoomTypeName(String roomTypeName) {
            RoomTypeName = roomTypeName;
        }

        public List<PushRoomType.RoomDetail> getRoomDetails() {
            return RoomDetails;
        }

        public void setRoomDetails(List<PushRoomType.RoomDetail> roomDetails) {
            RoomDetails = roomDetails;
        }
    }


    @XStreamAlias(value="RoomDetail")
    public static class RoomDetail{
        public String RoomDate;
        public String RoomPrice;
        public String PriRoomPrice;
        public String RoomNum;

        public String getRoomDate() {
            return RoomDate;
        }

        public void setRoomDate(String roomDate) {
            RoomDate = roomDate;
        }

        public String getRoomPrice() {
            return RoomPrice;
        }

        public void setRoomPrice(String roomPrice) {
            RoomPrice = roomPrice;
        }

        public String getPriRoomPrice() {
            return PriRoomPrice;
        }

        public void setPriRoomPrice(String priRoomPrice) {
            PriRoomPrice = priRoomPrice;
        }

        public String getRoomNum() {
            return RoomNum;
        }

        public void setRoomNum(String roomNum) {
            RoomNum = roomNum;
        }
    }
}
