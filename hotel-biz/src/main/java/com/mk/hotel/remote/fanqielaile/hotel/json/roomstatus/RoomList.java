package com.mk.hotel.remote.fanqielaile.hotel.json.roomstatus;

import java.util.List;

/**
 * Created by huangjie on 16/6/7.
 */
public class RoomList {

    private Integer status;
    private List<RoomDetailList> list;
    private String type;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<RoomDetailList> getList() {
        return list;
    }

    public void setList(List<RoomDetailList> list) {
        this.list = list;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
