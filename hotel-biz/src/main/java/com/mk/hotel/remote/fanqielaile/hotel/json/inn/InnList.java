package com.mk.hotel.remote.fanqielaile.hotel.json.inn;

import java.util.List;

/**
 * Created by huangjie on 16/6/7.
 */
public class InnList {
    private Integer status;
    private List<Inn> list;
    private String type;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Inn> getList() {
        return list;
    }

    public void setList(List<Inn> list) {
        this.list = list;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
