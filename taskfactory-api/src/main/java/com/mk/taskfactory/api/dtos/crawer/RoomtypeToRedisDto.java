package com.mk.taskfactory.api.dtos.crawer;

public class RoomtypeToRedisDto {
    private Long id;

    private Long roomTypeId;

    private Long hotelId;

    private String hotelSourceId;

    private String roomtypeKey;

    private String roomtypeName;

    private String roomArea;

    private String rtBedtype;

    private String bedType;

    private String bedSize;

    private String bathroomType;

    private String wifi;

    private String imageUrl;

    private String smallImageUrl;

    private String isPromo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelSourceId() {
        return hotelSourceId;
    }

    public void setHotelSourceId(String hotelSourceId) {
        this.hotelSourceId = hotelSourceId;
    }

    public String getRoomtypeKey() {
        return roomtypeKey;
    }

    public void setRoomtypeKey(String roomtypeKey) {
        this.roomtypeKey = roomtypeKey;
    }

    public String getRoomtypeName() {
        return roomtypeName;
    }

    public void setRoomtypeName(String roomtypeName) {
        this.roomtypeName = roomtypeName;
    }

    public String getRoomArea() {
        return roomArea;
    }

    public void setRoomArea(String roomArea) {
        this.roomArea = roomArea;
    }

    public String getRtBedtype() {
        return rtBedtype;
    }

    public void setRtBedtype(String rtBedtype) {
        this.rtBedtype = rtBedtype;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public String getBedSize() {
        return bedSize;
    }

    public void setBedSize(String bedSize) {
        this.bedSize = bedSize;
    }

    public String getBathroomType() {
        return bathroomType;
    }

    public void setBathroomType(String bathroomType) {
        this.bathroomType = bathroomType;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSmallImageUrl() {
        return smallImageUrl;
    }

    public void setSmallImageUrl(String smallImageUrl) {
        this.smallImageUrl = smallImageUrl;
    }

    public String getIsPromo() {
        return isPromo;
    }

    public void setIsPromo(String isPromo) {
        this.isPromo = isPromo;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }
}