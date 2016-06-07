package com.mk.hotel.remote.fanqielaile.hotel.json.inn;

import com.mk.hotel.remote.fanqielaile.hotel.json.FacilitiesMap;
import com.mk.hotel.remote.fanqielaile.hotel.json.ImgList;

import java.util.List;

/**
 * Created by huangjie on 16/6/7.
 */
public class Inn {

    private Integer accountId;
    private String brandName;
    private String innType;
    private String frontPhone;
    private String province;
    private String city;
    private String county;
    private String addr;
    private Double baiduLon;
    private Double baiduLat;
    private Integer roomNum;
    private String innInfo;
    private String openTime;
    private String lastDecorateTime;
    private List<ImgList> imgList;
    private List<FacilitiesMap> facilitiesMap;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getInnType() {
        return innType;
    }

    public void setInnType(String innType) {
        this.innType = innType;
    }

    public String getFrontPhone() {
        return frontPhone;
    }

    public void setFrontPhone(String frontPhone) {
        this.frontPhone = frontPhone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Double getBaiduLon() {
        return baiduLon;
    }

    public void setBaiduLon(Double baiduLon) {
        this.baiduLon = baiduLon;
    }

    public Double getBaiduLat() {
        return baiduLat;
    }

    public void setBaiduLat(Double baiduLat) {
        this.baiduLat = baiduLat;
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    public String getInnInfo() {
        return innInfo;
    }

    public void setInnInfo(String innInfo) {
        this.innInfo = innInfo;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getLastDecorateTime() {
        return lastDecorateTime;
    }

    public void setLastDecorateTime(String lastDecorateTime) {
        this.lastDecorateTime = lastDecorateTime;
    }

    public List<ImgList> getImgList() {
        return imgList;
    }

    public void setImgList(List<ImgList> imgList) {
        this.imgList = imgList;
    }

    public List<FacilitiesMap> getFacilitiesMap() {
        return facilitiesMap;
    }

    public void setFacilitiesMap(List<FacilitiesMap> facilitiesMap) {
        this.facilitiesMap = facilitiesMap;
    }
}
