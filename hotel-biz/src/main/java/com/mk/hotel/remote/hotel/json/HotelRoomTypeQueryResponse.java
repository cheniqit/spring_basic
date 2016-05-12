package com.mk.hotel.remote.hotel.json;

import com.mk.hotel.remote.common.FbbCommonResponse;

import java.util.List;

/**
 * Created by chenqi on 16/5/11.
 */
public class HotelRoomTypeQueryResponse extends FbbCommonResponse{
    private List<RoomType> data;


    public List<RoomType> getData() {
        return data;
    }

    public void setData(List<RoomType> data) {
        this.data = data;
    }

    class RoomType{
        private int id;
        private String name;
        private int roomnum;


        public void setId(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }


        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }


        public void setRoomnum(int roomnum) {
            this.roomnum = roomnum;
        }
        public int getRoomnum() {
            return roomnum;
        }
    }
}
