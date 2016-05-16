package com.mk.hotel.hotelinfo.json.facility;

/**
 * Created by huangjie on 16/5/16.
 */
public class FacilityJson {
    private Long id;
    private Long taggroupid;
    private String tagname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaggroupid() {
        return taggroupid;
    }

    public void setTaggroupid(Long taggroupid) {
        this.taggroupid = taggroupid;
    }

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }
}
