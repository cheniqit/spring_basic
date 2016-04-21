package com.mk.taskfactory.model.crawer;

public class HotelDensity {
    private Integer id;

    private String hotelId;

    private Integer oneKm;

    private Integer threeKm;

    private Integer fiveKm;

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

    public Integer getThreeKm() {
        return threeKm;
    }

    public void setThreeKm(Integer threeKm) {
        this.threeKm = threeKm;
    }

    public Integer getFiveKm() {
        return fiveKm;
    }

    public void setFiveKm(Integer fiveKm) {
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