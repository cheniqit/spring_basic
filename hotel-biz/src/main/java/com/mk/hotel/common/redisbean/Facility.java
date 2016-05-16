package com.mk.hotel.common.redisbean;

/**
 * Created by huangjie on 16/5/16.
 */
public class Facility {
    private Long facId;
    private String facName;
    private Long facType;

    private String cacheTime;

    private String cacheFrom;

    public Long getFacId() {
        return facId;
    }

    public void setFacId(Long facId) {
        this.facId = facId;
    }

    public String getFacName() {
        return facName;
    }

    public void setFacName(String facName) {
        this.facName = facName;
    }

    public Long getFacType() {
        return facType;
    }

    public void setFacType(Long facType) {
        this.facType = facType;
    }

    public String getCacheTime() {
        return cacheTime;
    }

    public void setCacheTime(String cacheTime) {
        this.cacheTime = cacheTime;
    }

    public String getCacheFrom() {
        return cacheFrom;
    }

    public void setCacheFrom(String cacheFrom) {
        this.cacheFrom = cacheFrom;
    }
}
