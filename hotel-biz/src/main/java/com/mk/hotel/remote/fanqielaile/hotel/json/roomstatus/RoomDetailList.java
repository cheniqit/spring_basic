package com.mk.hotel.remote.fanqielaile.hotel.json.roomstatus;

import java.util.List;

/**
 * Created by huangjie on 16/6/7.
 */
public class RoomDetailList {
    private String roomTypeName;
    private List<RoomDetail> roomDetail;
    private String ratePlanCode;
    private String ratePlanConfig;
    private int roomTypeId;

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public List<RoomDetail> getRoomDetail() {
        return roomDetail;
    }

    public void setRoomDetail(List<RoomDetail> roomDetail) {
        this.roomDetail = roomDetail;
    }

    public String getRatePlanCode() {
        return ratePlanCode;
    }

    public void setRatePlanCode(String ratePlanCode) {
        this.ratePlanCode = ratePlanCode;
    }

    public String getRatePlanConfig() {
        return ratePlanConfig;
    }

    public void setRatePlanConfig(String ratePlanConfig) {
        this.ratePlanConfig = ratePlanConfig;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }
}
