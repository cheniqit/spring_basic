package com.mk.taskfactory.model;

public class HotelDensity {
    private Integer id;

    private String hotelId;

    private Integer oneKm;

    private String threeKm;

    private String fiveKm;

    private Integer tenKm;

    private Integer hotelCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getOneKm() {
        return oneKm;
    }

    public void setOneKm(Integer oneKm) {
        this.oneKm = oneKm;
    }

    public String getThreeKm() {
        return threeKm;
    }

    public void setThreeKm(String threeKm) {
        this.threeKm = threeKm;
    }

    public String getFiveKm() {
        return fiveKm;
    }

    public void setFiveKm(String fiveKm) {
        this.fiveKm = fiveKm;
    }

    public Integer getTenKm() {
        return tenKm;
    }

    public void setTenKm(Integer tenKm) {
        this.tenKm = tenKm;
    }

    public Integer getHotelCount() {
        return hotelCount;
    }

    public void setHotelCount(Integer hotelCount) {
        this.hotelCount = hotelCount;
    }
}