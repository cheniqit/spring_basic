package com.mk.hotel.hotelinfo.json.hotel;

import java.math.BigDecimal;

/**
 * Created by huangjie on 16/5/10.
 */
public class HotelJson {
    private Long id;
    private String hotelname;
    private String detailaddr;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String defaultleavetime;
    private String opentime;
    private String hotelphone;
    private Long hoteltype;
    private String retentiontime;
    private String repairtime;
    private String introduction;

    private Long provcode;
    private Long citycode;
    private Long discode;
    private String hotelpic;


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

    public String getDefaultleavetime() {
        return defaultleavetime;
    }

    public void setDefaultleavetime(String defaultleavetime) {
        this.defaultleavetime = defaultleavetime;
    }

    public String getHotelphone() {
        return hotelphone;
    }

    public void setHotelphone(String hotelphone) {
        this.hotelphone = hotelphone;
    }

    public Long getHoteltype() {
        return hoteltype;
    }

    public void setHoteltype(Long hoteltype) {
        this.hoteltype = hoteltype;
    }

    public String getRetentiontime() {
        return retentiontime;
    }

    public void setRetentiontime(String retentiontime) {
        this.retentiontime = retentiontime;
    }

    public String getRepairtime() {
        return repairtime;
    }

    public void setRepairtime(String repairtime) {
        this.repairtime = repairtime;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Long getProvcode() {
        return provcode;
    }

    public void setProvcode(Long provcode) {
        this.provcode = provcode;
    }

    public Long getCitycode() {
        return citycode;
    }

    public void setCitycode(Long citycode) {
        this.citycode = citycode;
    }

    public Long getDiscode() {
        return discode;
    }

    public void setDiscode(Long discode) {
        this.discode = discode;
    }

    public String getHotelpic() {
        return hotelpic;
    }

    public void setHotelpic(String hotelpic) {
        this.hotelpic = hotelpic;
    }

    public String getOpentime() {
        return opentime;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime;
    }
}
