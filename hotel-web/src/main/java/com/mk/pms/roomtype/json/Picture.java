package com.mk.pms.roomtype.json;

import java.util.List;

/**
 * Created by kirinli on 16/5/12.
 */
public class Picture {

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

    class Pic{
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
