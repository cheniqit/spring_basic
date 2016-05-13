package com.mk.hotel.hotelinfo.json.hotelall;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by huangjie on 16/5/10.
 */
public class HotelJson {

    private Long id;
    private Long citycode;
    private String cityname;
    private String defaultleavetime;
    private String detailaddr;
    private Long discode;
    private Long districtname;
    private String hotelname;
    private String hotelpic;
    private String introduction;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String pmstype;
    private Long provcode;
    private String provincename;
    private Long repairtime;
    private String retentiontime;
    private String hoteltype;
    private List<RoomtypeJson> roomtypes;

    public String getHoteltype() {
        return hoteltype;
    }

    public void setHoteltype(String hoteltype) {
        this.hoteltype = hoteltype;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDefaultleavetime() {
        return defaultleavetime;
    }

    public void setDefaultleavetime(String defaultleavetime) {
        this.defaultleavetime = defaultleavetime;
    }

    public String getDetailaddr() {
        return detailaddr;
    }

    public void setDetailaddr(String detailaddr) {
        this.detailaddr = detailaddr;
    }

    public Long getDiscode() {
        return discode;
    }

    public void setDiscode(Long discode) {
        this.discode = discode;
    }

    public Long getDistrictname() {
        return districtname;
    }

    public void setDistrictname(Long districtname) {
        this.districtname = districtname;
    }

    public String getHotelname() {
        return hotelname;
    }

    public void setHotelname(String hotelname) {
        this.hotelname = hotelname;
    }

    public String getHotelpic() {
        return hotelpic;
    }

    public void setHotelpic(String hotelpic) {
        this.hotelpic = hotelpic;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getPmstype() {
        return pmstype;
    }

    public void setPmstype(String pmstype) {
        this.pmstype = pmstype;
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

    public Long getRepairtime() {
        return repairtime;
    }

    public void setRepairtime(Long repairtime) {
        this.repairtime = repairtime;
    }

    public String getRetentiontime() {
        return retentiontime;
    }

    public void setRetentiontime(String retentiontime) {
        this.retentiontime = retentiontime;
    }

    public List<RoomtypeJson> getRoomtypes() {
        return roomtypes;
    }

    public void setRoomtypes(List<RoomtypeJson> roomtypes) {
        this.roomtypes = roomtypes;
    }
}
