package com.mk.pms.roomtype.service;

import com.mk.pms.roomtype.json.RoomType;

import java.util.List;

/**
 * Created by kirinli on 16/5/12.
 */
public interface RoomTypeService {
    List<RoomType> queryHotelRoomType(Long hotelId);
}
