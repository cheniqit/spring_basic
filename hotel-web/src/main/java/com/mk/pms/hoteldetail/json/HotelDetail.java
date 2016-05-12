package com.mk.pms.hoteldetail.json;

import com.mk.pms.roomtype.json.Picture;
import com.mk.pms.roomtype.json.RoomType;

import java.util.List;

/**
 * Created by kirinli on 16/5/11.
 */
public class HotelDetail {
    private Long id;

    private String hotelname;

    private String detailaddr;

    private Long provcode;

    private String provincename;


    private Long citycode;

    private String cityname;

    private Long discode;

    private String districtname;

    private List<Picture> hotelpics;

    private Integer hoteltype;

    private String introduction;

    private Double latitude;

    private Double longitude;

    private String retentiontime;

    private String defaultleavetime;

    private List<RoomType> roomtypes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHotelname() {
        return hotelname;
    }

    public void setHotelname(String hotelname) {
        this.hotelname = hotelname;
    }

    public String getDetailaddr() {
        return detailaddr;
    }

    public void setDetailaddr(String detailaddr) {
        this.detailaddr = detailaddr;
    }

    public Long getProvcode() {
        return provcode;
    }

    public void setProvcode(Long provcode) {
        this.provcode = provcode;
    }

    public String getProvincename() {
        return provincename;
    }

    public void setProvincename(String provincename) {
        this.provincename = provincename;
    }

    public Long getCitycode() {
        return citycode;
    }

    public void setCitycode(Long citycode) {
        this.citycode = citycode;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public Long getDiscode() {
        return discode;
    }

    public void setDiscode(Long discode) {
        this.discode = discode;
    }

    public String getDistrictname() {
        return districtname;
    }

    public void setDistrictname(String districtname) {
        this.districtname = districtname;
    }

    public List<Picture> getHotelpics() {
        return hotelpics;
    }

    public void setHotelpics(List<Picture> hotelpics) {
        this.hotelpics = hotelpics;
    }

    public Integer getHoteltype() {
        return hoteltype;
    }

    public void setHoteltype(Integer hoteltype) {
        this.hoteltype = hoteltype;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getRetentiontime() {
        return retentiontime;
    }

    public void setRetentiontime(String retentiontime) {
        this.retentiontime = retentiontime;
    }

    public String getDefaultleavetime() {
        return defaultleavetime;
    }

    public void setDefaultleavetime(String defaultleavetime) {
        this.defaultleavetime = defaultleavetime;
    }

    public List<RoomType> getRoomtypes() {
        return roomtypes;
    }

    public void setRoomtypes(List<RoomType> roomtypes) {
        this.roomtypes = roomtypes;
    }
}
