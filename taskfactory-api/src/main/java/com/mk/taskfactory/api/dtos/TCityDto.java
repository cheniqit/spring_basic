package com.mk.taskfactory.api.dtos;

import java.math.BigDecimal;
import java.math.BigInteger;

public class TCityDto {
    private Integer cityId;
    private String code;
    private String cityName;
    private Integer proID;
    private Integer citySort;
    private BigDecimal latItude;
    private BigDecimal longItude;
    private String simpleName;
    private String isHotCity;
    private Double range;
    private String isSelect;
    private String queryCityName;
    private Integer level;

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getProID() {
        return proID;
    }

    public void setProID(Integer proID) {
        this.proID = proID;
    }

    public Integer getCitySort() {
        return citySort;
    }

    public void setCitySort(Integer citySort) {
        this.citySort = citySort;
    }

    public BigDecimal getLatItude() {
        return latItude;
    }

    public void setLatItude(BigDecimal latItude) {
        this.latItude = latItude;
    }

    public BigDecimal getLongItude() {
        return longItude;
    }

    public void setLongItude(BigDecimal longItude) {
        this.longItude = longItude;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    public String getIsHotCity() {
        return isHotCity;
    }

    public void setIsHotCity(String isHotCity) {
        this.isHotCity = isHotCity;
    }

    public Double getRange() {
        return range;
    }

    public void setRange(Double range) {
        this.range = range;
    }

    public String getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(String isSelect) {
        this.isSelect = isSelect;
    }

    public String getQueryCityName() {
        return queryCityName;
    }

    public void setQueryCityName(String queryCityName) {
        this.queryCityName = queryCityName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
