package com.mk.taskfactory.api.dtos.ods;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by fisher.wu on 16/3/3.
 */
public class TRoomTypeOnlinePriceDto {
    private BigInteger id;
    private BigInteger hotelId;
    private BigInteger roomTypeId;
    private BigDecimal price;
    private Date createTime;
    private Integer pageIndex;
    private Integer pageSize;

    public TRoomTypeOnlinePriceDto(BigInteger hotelId, BigInteger roomTypeId, BigDecimal price) {
        this.hotelId = hotelId;
        this.roomTypeId = roomTypeId;
        this.price = price;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getHotelId() {
        return hotelId;
    }

    public void setHotelId(BigInteger hotelId) {
        this.hotelId = hotelId;
    }

    public BigInteger getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(BigInteger roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
