package com.mk.hotel.hotelinfo.bean;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by chenqi on 16/5/16.
 */
public class HotelLandMark {
    private StringBuffer businessZoneInfo = new StringBuffer();

    private StringBuffer airportStationInfo = new StringBuffer();

    private StringBuffer scenicSpotsInfo = new StringBuffer();

    private StringBuffer hospitalInfo = new StringBuffer();

    private StringBuffer collegesInfo = new StringBuffer();

    private List<AreaInfo> businessZoneInfoList = new ArrayList<AreaInfo>();

    private List<AreaInfo> airportStationInfoList = new ArrayList<AreaInfo>();

    private List<AreaInfo> scenicSpotsInfoList = new ArrayList<AreaInfo>();

    private List<AreaInfo> hospitalInfoList = new ArrayList<AreaInfo>();

    private List<AreaInfo> collegesInfoList = new ArrayList<AreaInfo>();

    public static AreaInfo instanceAreaInfo(String areaInfoName, Float distance){
        return new AreaInfo(areaInfoName, distance);
    }

    public static class AreaInfo implements Comparator{
        private String areaInfoName;
        private Float distance;

        public AreaInfo(){

        }
        public AreaInfo(String areaInfoName, Float distance) {
            this.areaInfoName = areaInfoName;
            this.distance = distance;
        }

        public Float getDistance() {
            return distance;
        }

        public void setDistance(Float distance) {
            this.distance = distance;
        }

        public String getAreaInfoName() {
            return areaInfoName;
        }

        public void setAreaInfoName(String areaInfoName) {
            this.areaInfoName = areaInfoName;
        }

        @Override
        public int compare(Object o1, Object o2) {
            AreaInfo a1 = (AreaInfo) o1;
            AreaInfo a2 = (AreaInfo) o2;
            if(a1.distance > a2.getDistance()){
                return 1;
            }else if(a1.distance < a2.getDistance()){
                return -1;
            }
            return 0;
        }
    }

    public StringBuffer getBusinessZoneInfo() {
        if(businessZoneInfo.length() > 0){
            return businessZoneInfo;
        }
        Collections.sort(this.businessZoneInfoList, new AreaInfo());
        for(AreaInfo areaInfo : businessZoneInfoList){
            this.businessZoneInfo.append(",").append(areaInfo.getAreaInfoName());
        }
        return businessZoneInfo;
    }

    public void setBusinessZoneInfo(StringBuffer businessZoneInfo) {
        this.businessZoneInfo = businessZoneInfo;
    }

    public StringBuffer getAirportStationInfo() {
        if(airportStationInfo.length() > 0){
            return airportStationInfo;
        }
        Collections.sort(this.airportStationInfoList, new AreaInfo());
        for(AreaInfo areaInfo : airportStationInfoList){
            this.airportStationInfo.append(",").append(areaInfo.getAreaInfoName());
        }
        return airportStationInfo;
    }

    public void setAirportStationInfo(StringBuffer airportStationInfo) {
        this.airportStationInfo = airportStationInfo;
    }

    public StringBuffer getScenicSpotsInfo() {
        if(scenicSpotsInfo.length() > 0){
            return scenicSpotsInfo;
        }
        Collections.sort(this.scenicSpotsInfoList, new AreaInfo());
        for(AreaInfo areaInfo : scenicSpotsInfoList){
            this.scenicSpotsInfo.append(",").append(areaInfo.getAreaInfoName());
        }
        return scenicSpotsInfo;
    }

    public void setScenicSpotsInfo(StringBuffer scenicSpotsInfo) {
        this.scenicSpotsInfo = scenicSpotsInfo;
    }

    public StringBuffer getHospitalInfo() {
        if(hospitalInfo.length() > 0){
            return hospitalInfo;
        }
        Collections.sort(this.hospitalInfoList, new AreaInfo());
        for(AreaInfo areaInfo : hospitalInfoList){
            this.hospitalInfo.append(",").append(areaInfo.getAreaInfoName());
        }
        return hospitalInfo;
    }

    public void setHospitalInfo(StringBuffer hospitalInfo) {
        this.hospitalInfo = hospitalInfo;
    }

    public StringBuffer getCollegesInfo() {
        if(collegesInfo.length() > 0){
            return collegesInfo;
        }
        Collections.sort(this.collegesInfoList, new AreaInfo());
        for(AreaInfo areaInfo : collegesInfoList){
            this.collegesInfo.append(",").append(areaInfo.getAreaInfoName());
        }
        return collegesInfo;
    }

    public void setCollegesInfo(StringBuffer collegesInfo) {
        this.collegesInfo = collegesInfo;
    }

    public List<AreaInfo> getBusinessZoneInfoList() {
        return businessZoneInfoList;
    }

    public void setBusinessZoneInfoList(List<AreaInfo> businessZoneInfoList) {
        this.businessZoneInfoList = businessZoneInfoList;
    }

    public List<AreaInfo> getAirportStationInfoList() {
        return airportStationInfoList;
    }

    public void setAirportStationInfoList(List<AreaInfo> airportStationInfoList) {
        this.airportStationInfoList = airportStationInfoList;
    }

    public List<AreaInfo> getScenicSpotsInfoList() {
        return scenicSpotsInfoList;
    }

    public void setScenicSpotsInfoList(List<AreaInfo> scenicSpotsInfoList) {
        this.scenicSpotsInfoList = scenicSpotsInfoList;
    }

    public List<AreaInfo> getHospitalInfoList() {
        return hospitalInfoList;
    }

    public void setHospitalInfoList(List<AreaInfo> hospitalInfoList) {
        this.hospitalInfoList = hospitalInfoList;
    }

    public List<AreaInfo> getCollegesInfoList() {
        return collegesInfoList;
    }

    public void setCollegesInfoList(List<AreaInfo> collegesInfoList) {
        this.collegesInfoList = collegesInfoList;
    }
}
