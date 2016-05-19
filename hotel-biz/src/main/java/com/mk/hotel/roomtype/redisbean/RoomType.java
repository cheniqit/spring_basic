package com.mk.hotel.roomtype.redisbean;

import com.mk.hotel.common.redisbean.PicList;

import java.util.List;

/**
 * Created by huangjie on 16/5/16.
 */
public class RoomType {

    private Long hotelId;

    private Long roomTypeId;

    private String sourceId;

    private String roomTypeName;

    private Integer area;

    private BedType bedType;

    private Integer breakFast;

    private Integer status;

    private Integer roomNum;

    private List<PicList> roomTypePics;

    private String cacheTime;

    private String cacheFrom;

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public BedType getBedType() {
        return bedType;
    }

    public void setBedType(BedType bedType) {
        this.bedType = bedType;
    }

    public Integer getBreakFast() {
        return breakFast;
    }

    public void setBreakFast(Integer breakFast) {
        this.breakFast = breakFast;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    public List<PicList> getRoomTypePics() {
        return roomTypePics;
    }

    public void setRoomTypePics(List<PicList> roomTypePics) {
        this.roomTypePics = roomTypePics;
    }

    public String getCacheTime() {
        return cacheTime;
    }

    public void setCacheTime(String cacheTime) {
        this.cacheTime = cacheTime;
    }

    public String getCacheFrom() {
        return cacheFrom;
    }

    public void setCacheFrom(String cacheFrom) {
        this.cacheFrom = cacheFrom;
    }

}
