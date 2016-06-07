package com.mk.hotel.remote.fanqielaile.hotel.json.roomtype;

import java.util.List;

/**
 * Created by huangjie on 16/6/7.
 */
public class RoomTypeList {

    private Integer status;
    private List<RoomType> list;
    private String type;


    public void setStatus(int status) {
        this.status = status;
    }
    public int getStatus() {
        return status;
    }


    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<RoomType> getList() {
        return list;
    }

    public void setList(List<RoomType> list) {
        this.list = list;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }

}
