package com.mk.taskfactory.model;

public class TRoomSale {
    private Integer id;
    private Integer roomTypeId;
    private Integer oldRoomTypeId;
    private String name;
    private String pms;
    private String createDate;
    private String salePrice;
    private String costPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Integer roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Integer getOldRoomTypeId() {
        return oldRoomTypeId;
    }

    public void setOldRoomTypeId(Integer oldRoomTypeId) {
        this.oldRoomTypeId = oldRoomTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPms() {
        return pms;
    }

    public void setPms(String pms) {
        this.pms = pms;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(String costPrice) {
        this.costPrice = costPrice;
    }
}
