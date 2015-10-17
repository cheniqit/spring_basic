package com.mk.taskfactory.api.dtos;

public class TRoomSaleConfigDto {
    private Integer id;
    private Integer hotelId;
    private Integer roomId;
    private Integer roomTypeId;
    private String startTime;
    private String endTime;
    private String startDate;
    private String endDate;
    private Double saleValue;
    private Integer num;
    private Integer type;
    private String saleName;
    private  String  saleRoomTypeId;
    private  String  settleValue;
    private  String  settleType;
    private  String  valid;

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

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Integer roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Double getSaleValue() {
        return saleValue;
    }

    public void setSaleValue(Double saleValue) {
        this.saleValue = saleValue;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public String getSaleRoomTypeId() {
        return saleRoomTypeId;
    }

    public void setSaleRoomTypeId(String saleRoomTypeId) {
        this.saleRoomTypeId = saleRoomTypeId;
    }

    public String getSettleValue() {
        return settleValue;
    }

    public void setSettleValue(String settleValue) {
        this.settleValue = settleValue;
    }

    public String getSettleType() {
        return settleType;
    }

    public void setSettleType(String settleType) {
        this.settleType = settleType;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }
}
