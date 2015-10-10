package com.mk.channel.api.dtos;

public class MemberToOrderDto {
    private String comefrom;
    private String monthRegisterCount;
    private String monthOrdersCount;

    public String getComefrom() {
        return comefrom;
    }

    public void setComefrom(String comefrom) {
        this.comefrom = comefrom;
    }

    public String getMonthRegisterCount() {
        return monthRegisterCount;
    }

    public void setMonthRegisterCount(String monthRegisterCount) {
        this.monthRegisterCount = monthRegisterCount;
    }

    public String getMonthOrdersCount() {
        return monthOrdersCount;
    }

    public void setMonthOrdersCount(String monthOrdersCount) {
        this.monthOrdersCount = monthOrdersCount;
    }
}
