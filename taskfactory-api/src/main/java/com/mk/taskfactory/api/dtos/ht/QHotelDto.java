package com.mk.taskfactory.api.dtos.ht;

import java.math.BigDecimal;

public class QHotelDto {
    private Long id;

    private String sourceId;

    private String hotelName;

    private String hotelContactName;

    private String regTime;

    private Integer disId;

    private String detailAddr;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private String openTime;

    private String repairTime;

    private Integer roomNum;

    private String businessLicenseFront;

    private String businessLicenseBack;

    private String pms;

    private String visible;

    private String online;

    private String idCardFront;

    private String idCardBack;

    private String retentionTime;

    private String defaultLeaveTime;

    private String needWait;

    private String hotelPhone;

    private String isNewPms;

    private Integer priority;

    private Integer rulecode;

    private String isthreshold;

    private Long pmsOptTime;

    private Integer provCode;

    private Integer cityCode;

    private Integer disCode;

    private Integer areaCode;

    private String areaName;

    private String qtPhone;

    private Integer hotelType;

    private Integer confidence;

    private String dataSource;

    private String priceValid;

    private String createTime;

    private String updateTime;

    private String introduction;

    private String traffic;

    private String hotelPic;

    private String peripheral;

    private Integer hotelSource;

    private Integer pageIndex;

    private Integer pageSize;

    private String roomTypeValid;

    private BigDecimal dynamicPrice;

    private BigDecimal originPrice;

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic == null ? null : traffic.trim();
    }

    public String getHotelPic() {
        return hotelPic;
    }

    public void setHotelPic(String hotelPic) {
        this.hotelPic = hotelPic == null ? null : hotelPic.trim();
    }

    public String getPeripheral() {
        return peripheral;
    }

    public void setPeripheral(String peripheral) {
        this.peripheral = peripheral == null ? null : peripheral.trim();
    }

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
        this.sourceId = sourceId == null ? null : sourceId.trim();
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName == null ? null : hotelName.trim();
    }

    public String getHotelContactName() {
        return hotelContactName;
    }

    public void setHotelContactName(String hotelContactName) {
        this.hotelContactName = hotelContactName == null ? null : hotelContactName.trim();
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        if(regTime!=null&&regTime.length()>19){
            regTime=regTime.substring(0,19);
        }
        this.regTime = regTime;
    }

    public Integer getDisId() {
        return disId;
    }

    public void setDisId(Integer disId) {
        this.disId = disId;
    }

    public String getDetailAddr() {
        return detailAddr;
    }

    public void setDetailAddr(String detailAddr) {
        this.detailAddr = detailAddr == null ? null : detailAddr.trim();
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
        if(openTime!=null&&openTime.length()>19){
            openTime=openTime.substring(0,19);
        }
        this.openTime = openTime;
    }

    public String getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(String repairTime) {
        if(repairTime!=null&&repairTime.length()>19){
            repairTime=repairTime.substring(0,19);
        }
        this.repairTime = repairTime;
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    public String getBusinessLicenseFront() {
        return businessLicenseFront;
    }

    public void setBusinessLicenseFront(String businessLicenseFront) {
        this.businessLicenseFront = businessLicenseFront == null ? null : businessLicenseFront.trim();
    }

    public String getBusinessLicenseBack() {
        return businessLicenseBack;
    }

    public void setBusinessLicenseBack(String businessLicenseBack) {
        this.businessLicenseBack = businessLicenseBack == null ? null : businessLicenseBack.trim();
    }

    public String getPms() {
        return pms;
    }

    public void setPms(String pms) {
        this.pms = pms == null ? null : pms.trim();
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible == null ? null : visible.trim();
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online == null ? null : online.trim();
    }

    public String getIdCardFront() {
        return idCardFront;
    }

    public void setIdCardFront(String idCardFront) {
        this.idCardFront = idCardFront == null ? null : idCardFront.trim();
    }

    public String getIdCardBack() {
        return idCardBack;
    }

    public void setIdCardBack(String idCardBack) {
        this.idCardBack = idCardBack == null ? null : idCardBack.trim();
    }

    public String getRetentionTime() {
        return retentionTime;
    }

    public void setRetentionTime(String retentionTime) {
        this.retentionTime = retentionTime == null ? null : retentionTime.trim();
    }

    public String getDefaultLeaveTime() {
        return defaultLeaveTime;
    }

    public void setDefaultLeaveTime(String defaultLeaveTime) {
        this.defaultLeaveTime = defaultLeaveTime == null ? null : defaultLeaveTime.trim();
    }

    public String getNeedWait() {
        return needWait;
    }

    public void setNeedWait(String needWait) {
        this.needWait = needWait == null ? null : needWait.trim();
    }

    public String getHotelPhone() {
        return hotelPhone;
    }

    public void setHotelPhone(String hotelPhone) {
        this.hotelPhone = hotelPhone == null ? null : hotelPhone.trim();
    }

    public String getIsNewPms() {
        return isNewPms;
    }

    public void setIsNewPms(String isNewPms) {
        this.isNewPms = isNewPms == null ? null : isNewPms.trim();
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getRulecode() {
        return rulecode;
    }

    public void setRulecode(Integer rulecode) {
        this.rulecode = rulecode;
    }

    public String getIsthreshold() {
        return isthreshold;
    }

    public void setIsthreshold(String isthreshold) {
        this.isthreshold = isthreshold == null ? null : isthreshold.trim();
    }

    public Long getPmsOptTime() {
        return pmsOptTime;
    }

    public void setPmsOptTime(Long pmsOptTime) {
        this.pmsOptTime = pmsOptTime;
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

    public Integer getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(Integer areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public String getQtPhone() {
        return qtPhone;
    }

    public void setQtPhone(String qtPhone) {
        this.qtPhone = qtPhone == null ? null : qtPhone.trim();
    }

    public Integer getHotelType() {
        return hotelType;
    }

    public void setHotelType(Integer hotelType) {
        this.hotelType = hotelType;
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

    public Integer getHotelSource() {
        return hotelSource;
    }

    public void setHotelSource(Integer hotelSource) {
        this.hotelSource = hotelSource;
    }

    public Integer getConfidence() {
        return confidence;
    }

    public void setConfidence(Integer confidence) {
        this.confidence = confidence;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getPriceValid() {
        return priceValid;
    }

    public void setPriceValid(String priceValid) {
        this.priceValid = priceValid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        if(createTime!=null&&createTime.length()>19){
            createTime=createTime.substring(0,19);
        }
        this.createTime = createTime;
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

    public String getRoomTypeValid() {
        return roomTypeValid;
    }

    public void setRoomTypeValid(String roomTypeValid) {
        this.roomTypeValid = roomTypeValid;
    }

    public BigDecimal getDynamicPrice() {
        return dynamicPrice;
    }

    public void setDynamicPrice(BigDecimal dynamicPrice) {
        this.dynamicPrice = dynamicPrice;
    }

    public BigDecimal getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(BigDecimal originPrice) {
        this.originPrice = originPrice;
    }
}