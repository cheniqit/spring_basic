package com.mk.hotel.remote.ots.json;

/**
 * Created by huangjie on 16/6/8.
 */
public class City {
    private String cityName;
    private String cityCode;
    private String provCode;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getProvCode() {
        return provCode;
    }

    public void setProvCode(String provCode) {
        this.provCode = provCode;
    }
}
