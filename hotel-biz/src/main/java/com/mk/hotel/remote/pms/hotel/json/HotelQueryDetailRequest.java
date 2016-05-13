package com.mk.hotel.remote.pms.hotel.json;

/**
 * Created by chenqi on 16/5/11.
 */
public class HotelQueryDetailRequest {
    private String channelid;
    private String hotelid;

    public String getChannelid() {
        return channelid;
    }

    public void setChannelid(String channelid) {
        this.channelid = channelid;
    }

    public String getHotelid() {
        return hotelid;
    }

    public void setHotelid(String hotelid) {
        this.hotelid = hotelid;
    }
}
