package com.mk.channel.model;

public class TRoomSetting {
    private Integer id;
    private Integer hotelId;
    private Integer roomTypeId;
    private String roomNo;
    private String bedTypeName;
    private String roomDirection;
    private String roomFloor;
    private String isWindow;
    private String isStair;
    private String isElevator;
    private String isStreet;
    private String creteDate;
    private String updateDate;
    private String valid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Integer roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getBedTypeName() {
        return bedTypeName;
    }

    public void setBedTypeName(String bedTypeName) {
        this.bedTypeName = bedTypeName;
    }

    public String getRoomDirection() {
        return roomDirection;
    }

    public void setRoomDirection(String roomDirection) {
        this.roomDirection = roomDirection;
    }

    public String getRoomFloor() {
        return roomFloor;
    }

    public void setRoomFloor(String roomFloor) {
        this.roomFloor = roomFloor;
    }

    public String getIsWindow() {
        return isWindow;
    }

    public void setIsWindow(String isWindow) {
        this.isWindow = isWindow;
    }

    public String getIsStair() {
        return isStair;
    }

    public void setIsStair(String isStair) {
        this.isStair = isStair;
    }

    public String getIsElevator() {
        return isElevator;
    }

    public void setIsElevator(String isElevator) {
        this.isElevator = isElevator;
    }

    public String getIsStreet() {
        return isStreet;
    }

    public void setIsStreet(String isStreet) {
        this.isStreet = isStreet;
    }

    public String getCreteDate() {
        return creteDate;
    }

    public void setCreteDate(String creteDate) {
        this.creteDate = creteDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }
}
