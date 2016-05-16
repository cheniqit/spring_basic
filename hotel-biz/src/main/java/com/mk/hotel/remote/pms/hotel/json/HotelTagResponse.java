package com.mk.hotel.remote.pms.hotel.json;

import com.google.gson.annotations.SerializedName;
import com.mk.hotel.remote.pms.common.FbbCommonResponse;

import java.util.List;

/**
 * Created by chenqi on 16/5/16.
 */
public class HotelTagResponse extends FbbCommonResponse{
    private TagInfo data;

    public TagInfo getData() {
        return data;
    }

    public void setData(TagInfo data) {
        this.data = data;
    }

    public class TagInfo{
        private int hotelid;
        @SerializedName("roomtypeTags")
        List<RoomTypeFacilityJson> roomtypeTags;
        private List<Tags> tags;


        public void setHotelid(int hotelid) {
            this.hotelid = hotelid;
        }
        public int getHotelid() {
            return hotelid;
        }


        public List<RoomTypeFacilityJson> getRoomtypeTags() {
            return roomtypeTags;
        }

        public void setRoomtypeTags(List<RoomTypeFacilityJson> roomtypeTags) {
            this.roomtypeTags = roomtypeTags;
        }

        public void setTags(List<Tags> tags) {
            this.tags = tags;
        }
        public List<Tags> getTags() {
            return tags;
        }
    }

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

    public class Tags{
        private String createtime;
        private Group group;
        private String id;
        private String taggroupid;
        private String tagname;
        private String updatetime;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public Group getGroup() {
            return group;
        }

        public void setGroup(Group group) {
            this.group = group;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTaggroupid() {
            return taggroupid;
        }

        public void setTaggroupid(String taggroupid) {
            this.taggroupid = taggroupid;
        }

        public String getTagname() {
            return tagname;
        }

        public void setTagname(String tagname) {
            this.tagname = tagname;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }
    }

    public class RoomTypeFacilityJson {
        private Long roomtypeid;
        private List<FacilityJson> tags;

        public Long getRoomtypeid() {
            return roomtypeid;
        }

        public void setRoomtypeid(Long roomtypeid) {
            this.roomtypeid = roomtypeid;
        }

        public List<FacilityJson> getTags() {
            return tags;
        }

        public void setTags(List<FacilityJson> tags) {
            this.tags = tags;
        }
    }


    public class Group{
        private String createtime;
        private String groupname;
        private String id;
        private String updatetime;


        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getGroupname() {
            return groupname;
        }

        public void setGroupname(String groupname) {
            this.groupname = groupname;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }
    }
}
