package com.mk.taskfactory.api.dtos.ht;

import java.math.BigDecimal;

public class QHotelSurroundDto {
    private String hotelId;

    private String surroundType;

    private String surroundName;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private Long distance;

    private String updateTime;

    private Integer pageIndex;

    private Integer pageSize;

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId == null ? null : hotelId.trim();
    }

    public String getSurroundType() {
        return surroundType;
    }

    public void setSurroundType(String surroundType) {
        this.surroundType = surroundType == null ? null : surroundType.trim();
    }

    public String getSurroundName() {
        return surroundName;
    }

    public void setSurroundName(String surroundName) {
        this.surroundName = surroundName == null ? null : surroundName.trim();
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
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