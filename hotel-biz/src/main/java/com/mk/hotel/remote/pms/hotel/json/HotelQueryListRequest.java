package com.mk.hotel.remote.pms.hotel.json;


/**
 * Created by chenqi on 16/5/11.
 */
public class HotelQueryListRequest {
    private Integer page;
    private Integer pagesize;

    public HotelQueryListRequest(){

    }

    public HotelQueryListRequest(Integer page) {
        this.page = page;
        this.pagesize = 1000;
    }

    public HotelQueryListRequest(Integer page, Integer pagesize) {
        this.page = page;
        this.pagesize = pagesize;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }
}
