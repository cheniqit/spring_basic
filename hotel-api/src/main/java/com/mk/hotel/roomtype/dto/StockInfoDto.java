package com.mk.hotel.roomtype.dto;

import java.io.Serializable;

/**
 * Created by huangjie on 16/6/28.
 */
public class StockInfoDto implements Serializable {

    private String date;
    private String num;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getNum() {
        return num;
    }

}
