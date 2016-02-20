package com.mk.taskfactory.model.crawer;

import java.math.BigDecimal;
import java.util.Date;

public class QHotelSurround {
    private String hotelId;

    private String surroundType;

    private String surroundName;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private Long distance;

    private Date updateTime;

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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}