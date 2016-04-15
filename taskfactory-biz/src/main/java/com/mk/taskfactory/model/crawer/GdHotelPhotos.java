package com.mk.taskfactory.model.crawer;

import java.util.Date;

public class GdHotelPhotos {
    private Long id;
    private String hotelSourceId;
    private String title;
    private String url;
    private String urlKey;
    private String isCover;
    private Integer srcHeight;
    private Integer weight;
    private String srcType;
    private Integer srcWidth;
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

    public String getHotelSourceId() {
        return hotelSourceId;
    }

    public void setHotelSourceId(String hotelSourceId) {
        this.hotelSourceId = hotelSourceId == null ? null : hotelSourceId.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getUrlKey() {
        return urlKey;
    }

    public void setUrlKey(String urlKey) {
        this.urlKey = urlKey;
    }

    public String getIsCover() {
        return isCover;
    }

    public void setIsCover(String isCover) {
        this.isCover = isCover;
    }

    public Integer getSrcHeight() {
        return srcHeight;
    }

    public void setSrcHeight(Integer srcHeight) {
        this.srcHeight = srcHeight;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getSrcType() {
        return srcType;
    }

    public void setSrcType(String srcType) {
        this.srcType = srcType;
    }

    public Integer getSrcWidth() {
        return srcWidth;
    }

    public void setSrcWidth(Integer srcWidth) {
        this.srcWidth = srcWidth;
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