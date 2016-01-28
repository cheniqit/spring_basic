package com.mk.taskfactory.api.dtos.crawer;

import java.util.Date;

public class TExHotelImageDto {
    private Long id;

    private String cityName;

    private String cityUrl;

    private String hotelSourceId;

    private String hotelName;

    private String hotelAddress;

    private String big;

    private String url;

    private String tag;

    private String src;

    private String ugc;

    private String osrc;

    private String creDate;

    private String title;

    private String author;

    private Long width;

    private Long height;

    private Date createTime;

    private Date updateTime;

    private Integer pageIndex;

    private Integer pageSize;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getCityUrl() {
        return cityUrl;
    }

    public void setCityUrl(String cityUrl) {
        this.cityUrl = cityUrl == null ? null : cityUrl.trim();
    }

    public String getHotelSourceId() {
        return hotelSourceId;
    }

    public void setHotelSourceId(String hotelSourceId) {
        this.hotelSourceId = hotelSourceId == null ? null : hotelSourceId.trim();
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName == null ? null : hotelName.trim();
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress == null ? null : hotelAddress.trim();
    }

    public String getBig() {
        return big;
    }

    public void setBig(String big) {
        this.big = big == null ? null : big.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src == null ? null : src.trim();
    }

    public String getUgc() {
        return ugc;
    }

    public void setUgc(String ugc) {
        this.ugc = ugc == null ? null : ugc.trim();
    }

    public String getOsrc() {
        return osrc;
    }

    public void setOsrc(String osrc) {
        this.osrc = osrc == null ? null : osrc.trim();
    }

    public String getCreDate() {
        return creDate;
    }

    public void setCreDate(String creDate) {
        this.creDate = creDate == null ? null : creDate.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Long getWidth() {
        return width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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