package com.mk.hotel.remote.fanqielaile.hotel.json.roomtype;

import com.mk.hotel.remote.fanqielaile.hotel.json.FacilitiesMap;
import com.mk.hotel.remote.fanqielaile.hotel.json.ImgList;

import java.util.List;

/**
 * Created by huangjie on 16/6/7.
 */
public class RoomType {
    private String roomTypeName;
    private String roomArea;
    private Integer bedNum;
    private Integer bedLen;
    private Integer bedWid;
    private String floorNum;
    private String bedType;
    private String bedTypeValue;
    private String recommend;
    private String roomInfo;
    private List<FacilitiesMap> facilitiesMap;
    private List<ImgList> imgList;
    private String ratePlanCode;
    private String ratePlanConfig;
    private Integer roomTypeId;

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public String getRoomArea() {
        return roomArea;
    }

    public void setRoomArea(String roomArea) {
        this.roomArea = roomArea;
    }

    public Integer getBedNum() {
        return bedNum;
    }

    public void setBedNum(Integer bedNum) {
        this.bedNum = bedNum;
    }

    public Integer getBedLen() {
        return bedLen;
    }

    public void setBedLen(Integer bedLen) {
        this.bedLen = bedLen;
    }

    public Integer getBedWid() {
        return bedWid;
    }

    public void setBedWid(Integer bedWid) {
        this.bedWid = bedWid;
    }

    public String getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(String floorNum) {
        this.floorNum = floorNum;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public String getBedTypeValue() {
        return bedTypeValue;
    }

    public void setBedTypeValue(String bedTypeValue) {
        this.bedTypeValue = bedTypeValue;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getRoomInfo() {
        return roomInfo;
    }

    public void setRoomInfo(String roomInfo) {
        this.roomInfo = roomInfo;
    }

    public List<FacilitiesMap> getFacilitiesMap() {
        return facilitiesMap;
    }

    public void setFacilitiesMap(List<FacilitiesMap> facilitiesMap) {
        this.facilitiesMap = facilitiesMap;
    }

    public List<ImgList> getImgList() {
        return imgList;
    }

    public void setImgList(List<ImgList> imgList) {
        this.imgList = imgList;
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

    public Integer getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Integer roomTypeId) {
        this.roomTypeId = roomTypeId;
    }
}
