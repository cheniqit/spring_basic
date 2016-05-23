package com.mk.hotel.hotelinfo.redisbean;

import com.mk.hotel.common.redisbean.PicList;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by huangjie on 16/5/16.
 */
public class Hotel {
    private Long id;

    private String sourceId;

    private String hotelName;

    private String regTime;

    private String detailAddr;

    private BigDecimal longitude;

    private BigDecimal latitude;

    // 年-月-日
    private String openTime;

    // 年-月-日
    private String repairTime;

//    //? 没
//    private String createTime;
//
//    //? 没
//    private String visible;
//
    //
    private String online;

    //保留时间,格式 180000
    private String retentionTime;

    //默认预离时间,格式 120000
    private String defaultLeaveTime;

    private String hotelPhone;

//    //? 没
//    private Integer priority;

    private Integer provCode;

    private Integer cityCode;

    private Integer disCode;

//    //? 没
//    private Integer areaCode;

    private Integer townCode;

    private String townName;

//    //? 没
//    private String  surroundInfo;
//
//    //? 没
//    private String areaName;

    //
    private Integer hotelType;

    private String introduction;

    private List<PicList> hotelPics;

//    //?
//    private BigDecimal price;
//
//    //?
//    private BigDecimal originPrice;

    //商圈信息以逗号隔开
    private String businessZoneInfo;
    //机场车站信息以逗号隔开
    private String airportStationInfo;
    //景点信息以逗号隔开
    private String scenicSpotsInfo;
    //医院信息以逗号隔开
    private String hospitalInfo;
    //学校信息以逗号隔开
    private String collegesInfo;

    private String cacheTime;

    private String cacheFrom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public String getDetailAddr() {
        return detailAddr;
    }

    public void setDetailAddr(String detailAddr) {
        this.detailAddr = detailAddr;
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

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(String repairTime) {
        this.repairTime = repairTime;
    }

    public String getRetentionTime() {
        return retentionTime;
    }

    public void setRetentionTime(String retentionTime) {
        this.retentionTime = retentionTime;
    }

    public String getDefaultLeaveTime() {
        return defaultLeaveTime;
    }

    public void setDefaultLeaveTime(String defaultLeaveTime) {
        this.defaultLeaveTime = defaultLeaveTime;
    }

    public String getHotelPhone() {
        return hotelPhone;
    }

    public void setHotelPhone(String hotelPhone) {
        this.hotelPhone = hotelPhone;
    }

    public Integer getProvCode() {
        return provCode;
    }

    public void setProvCode(Integer provCode) {
        this.provCode = provCode;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public Integer getDisCode() {
        return disCode;
    }

    public void setDisCode(Integer disCode) {
        this.disCode = disCode;
    }

    public Integer getTownCode() {
        return townCode;
    }

    public void setTownCode(Integer townCode) {
        this.townCode = townCode;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public Integer getHotelType() {
        return hotelType;
    }

    public void setHotelType(Integer hotelType) {
        this.hotelType = hotelType;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public List<PicList> getHotelPics() {
        return hotelPics;
    }

    public void setHotelPics(List<PicList> hotelPics) {
        this.hotelPics = hotelPics;
    }

    public String getBusinessZoneInfo() {
        return businessZoneInfo;
    }

    public void setBusinessZoneInfo(String businessZoneInfo) {
        this.businessZoneInfo = businessZoneInfo;
    }

    public String getAirportStationInfo() {
        return airportStationInfo;
    }

    public void setAirportStationInfo(String airportStationInfo) {
        this.airportStationInfo = airportStationInfo;
    }

    public String getScenicSpotsInfo() {
        return scenicSpotsInfo;
    }

    public void setScenicSpotsInfo(String scenicSpotsInfo) {
        this.scenicSpotsInfo = scenicSpotsInfo;
    }

    public String getHospitalInfo() {
        return hospitalInfo;
    }

    public void setHospitalInfo(String hospitalInfo) {
        this.hospitalInfo = hospitalInfo;
    }

    public String getCollegesInfo() {
        return collegesInfo;
    }

    public void setCollegesInfo(String collegesInfo) {
        this.collegesInfo = collegesInfo;
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

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }
}
