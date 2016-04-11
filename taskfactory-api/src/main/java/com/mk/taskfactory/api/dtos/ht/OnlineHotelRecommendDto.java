package com.mk.taskfactory.api.dtos.ht;

import java.math.BigDecimal;
import java.util.Date;

public class OnlineHotelRecommendDto {
    private Long id;

    private Long hotelId;

    private Long roomTypeId;

    private Long recHotelId;

    private Long recRoomTypeId;

    private BigDecimal recPrice;

    private Integer recSort;

    private String isVaild;

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

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Long getRecHotelId() {
        return recHotelId;
    }

    public void setRecHotelId(Long recHotelId) {
        this.recHotelId = recHotelId;
    }

    public Long getRecRoomTypeId() {
        return recRoomTypeId;
    }

    public void setRecRoomTypeId(Long recRoomTypeId) {
        this.recRoomTypeId = recRoomTypeId;
    }

    public BigDecimal getRecPrice() {
        return recPrice;
    }

    public void setRecPrice(BigDecimal recPrice) {
        this.recPrice = recPrice;
    }

    public Integer getRecSort() {
        return recSort;
    }

    public void setRecSort(Integer recSort) {
        this.recSort = recSort;
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

    public String getIsVaild() {
        return isVaild;
    }

    public void setIsVaild(String isVaild) {
        this.isVaild = isVaild;
    }
}