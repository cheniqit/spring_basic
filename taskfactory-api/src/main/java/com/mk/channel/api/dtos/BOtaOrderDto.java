package com.mk.channel.api.dtos;

public class BOtaOrderDto {
    public  Long   mid;
    public  int  monthOrdersCount;
    public  String  createTime;

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public int getMonthOrdersCount() {
        return monthOrdersCount;
    }

    public void setMonthOrdersCount(int monthOrdersCount) {
        this.monthOrdersCount = monthOrdersCount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
