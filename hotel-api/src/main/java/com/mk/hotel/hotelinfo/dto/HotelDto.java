package com.mk.hotel.hotelinfo.dto;

import com.mk.hotel.roomtype.dto.RoomTypeDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class HotelDto {
    private Long id;

    private Long fangId;

    private String name;

    private String addr;

    private String phone;

    private BigDecimal lat;

    private BigDecimal lon;

    private String defaultLeaveTime;

    private String hotelType;

    private String retentionTime;

    private String repairTime;

    private String introduction;

    private String provCode;

    private String cityCode;

    private String disCode;

    private Date createDate;

    private String createBy;

    private Date updateDate;

    private String updateBy;

    private String isValid;

    private String townCode;

    private String businessZoneInfo;

    private String airportStationInfo;

    private String scenicSpotsInfo;

    private String hospitalInfo;

    private String collegesInfo;

    private String openTime;

    private String regTime;

    private String pic;

    private Integer sourceType;

    private String pics;

    private List<RoomTypeDto> roomTypeDtoList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFangId() {
        return fangId;
    }

    public void setFangId(Long fangId) {
        this.fangId = fangId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    public String getDefaultLeaveTime() {
        return defaultLeaveTime;
    }

    public void setDefaultLeaveTime(String defaultLeaveTime) {
        this.defaultLeaveTime = defaultLeaveTime == null ? null : defaultLeaveTime.trim();
    }

    public String getHotelType() {
        return hotelType;
    }

    public void setHotelType(String hotelType) {
        this.hotelType = hotelType == null ? null : hotelType.trim();
    }

    public String getRetentionTime() {
        return retentionTime;
    }

    public void setRetentionTime(String retentionTime) {
        this.retentionTime = retentionTime == null ? null : retentionTime.trim();
    }

    public String getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(String repairTime) {
        this.repairTime = repairTime == null ? null : repairTime.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getProvCode() {
        return provCode;
    }

    public void setProvCode(String provCode) {
        this.provCode = provCode == null ? null : provCode.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getDisCode() {
        return disCode;
    }

    public void setDisCode(String disCode) {
        this.disCode = disCode == null ? null : disCode.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }

    public String getTownCode() {
        return townCode;
    }

    public void setTownCode(String townCode) {
        this.townCode = townCode == null ? null : townCode.trim();
    }

    public String getBusinessZoneInfo() {
        return businessZoneInfo;
    }

    public void setBusinessZoneInfo(String businessZoneInfo) {
        this.businessZoneInfo = businessZoneInfo == null ? null : businessZoneInfo.trim();
    }

    public String getAirportStationInfo() {
        return airportStationInfo;
    }

    public void setAirportStationInfo(String airportStationInfo) {
        this.airportStationInfo = airportStationInfo == null ? null : airportStationInfo.trim();
    }

    public String getScenicSpotsInfo() {
        return scenicSpotsInfo;
    }

    public void setScenicSpotsInfo(String scenicSpotsInfo) {
        this.scenicSpotsInfo = scenicSpotsInfo == null ? null : scenicSpotsInfo.trim();
    }

    public String getHospitalInfo() {
        return hospitalInfo;
    }

    public void setHospitalInfo(String hospitalInfo) {
        this.hospitalInfo = hospitalInfo == null ? null : hospitalInfo.trim();
    }

    public String getCollegesInfo() {
        return collegesInfo;
    }

    public void setCollegesInfo(String collegesInfo) {
        this.collegesInfo = collegesInfo == null ? null : collegesInfo.trim();
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime == null ? null : openTime.trim();
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public List<RoomTypeDto> getRoomTypeDtoList() {
        return roomTypeDtoList;
    }

    public void setRoomTypeDtoList(List<RoomTypeDto> roomTypeDtoList) {
        this.roomTypeDtoList = roomTypeDtoList;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }
}