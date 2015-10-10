package com.mk.channel.api.dtos;

public class UMemberDto {
    private Long mid;
    private String loginName;
    private String phone;
    private String comefrom;
    private String unionid;
    private String createtime;
    private int monthRegisterCount;
    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getComefrom() {
        return comefrom;
    }

    public void setComefrom(String comefrom) {
        this.comefrom = comefrom;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public int getMonthRegisterCount() {
        return monthRegisterCount;
    }

    public void setMonthRegisterCount(int monthRegisterCount) {
        this.monthRegisterCount = monthRegisterCount;
    }
}
