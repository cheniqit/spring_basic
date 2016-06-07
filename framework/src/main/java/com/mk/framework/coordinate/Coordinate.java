package com.mk.framework.coordinate;

/**
 * Created by huangjie on 16/6/7.
 */
public class Coordinate {

    private Double lon;
    private Double lat;

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String toString() {

        return String.format("lon:%s lat=:%s", this.lon, this.lat);
    }
}
