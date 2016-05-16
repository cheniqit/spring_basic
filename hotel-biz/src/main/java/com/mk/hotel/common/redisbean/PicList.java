package com.mk.hotel.common.redisbean;

import java.util.List;

/**
 * Created by huangjie on 16/5/16.
 */
public class PicList {
    private String name;

    private List<Pic> pic;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pic> getPic() {
        return pic;
    }

    public void setPic(List<Pic> pic) {
        this.pic = pic;
    }
}
