package com.mk.common;

/**
 * Created by chenqi on 16/5/9.
 */
public class CommonRequest {
    private String token;
    /*当前页码*/
    private int pageNo = 1;
    /*每页条数*/
    private int pageSize = Constant.DEFAULT_PAGE_SIZE;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
