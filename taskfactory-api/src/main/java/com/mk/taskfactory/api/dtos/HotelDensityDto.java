package com.mk.taskfactory.api.dtos;

public class HotelDensityDto {
    private Integer id;

    private String hotelId;

    private Integer oneKm;

    private Integer threeKm;

    private Integer fiveKm;

    private Integer tenKm;

    private Integer pageIndex;

    private Integer pageSize;

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