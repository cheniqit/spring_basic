package com.mk.taskfactory.model;

public class TRoomTypeBed {

    private Long id;
    private Long roomTypeId;
    private Integer num;
    private Long bedType;
    private String otherInfo;

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getBedType() {
        return bedType;
    }

    public void setBedType(Long bedType) {
        this.bedType = bedType;
    }

}
