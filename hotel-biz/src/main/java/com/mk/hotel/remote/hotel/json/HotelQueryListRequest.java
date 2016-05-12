package com.mk.hotel.remote.hotel.json;

/**
 * Created by chenqi on 16/5/11.
 */
public class HotelQueryListRequest {
    private Integer page;
    private Integer pagesize;

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
