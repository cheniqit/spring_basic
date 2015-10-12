package com.mk.taskfactory.api.dtos;

public class TRoomChangeTypeDto {
    private Integer roomTypeId;
    private Integer oldRoomTypeId;

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
}
