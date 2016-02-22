package com.mk.taskfactory.api.dtos.crawer;

public class QHotelRoomtypeMinPriceDto {
    private Long id;
    private String qnHotelId;
    private String hotelName;
    private String roomtypeId;
    private String roomtypeKey;
    private String roomtypeName;
    private String minPrice;
    private String updateTime;
    private Integer pageIndex;

    private Integer pageSize;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQnHotelId() {
        return qnHotelId;
    }

    public void setQnHotelId(String qnHotelId) {
        this.qnHotelId = qnHotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getRoomtypeId() {
        return roomtypeId;
    }

    public void setRoomtypeId(String roomtypeId) {
        this.roomtypeId = roomtypeId;
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

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        if(updateTime!=null&&updateTime.length()>19){
            updateTime=updateTime.substring(0,19);
        }
        this.updateTime = updateTime;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}