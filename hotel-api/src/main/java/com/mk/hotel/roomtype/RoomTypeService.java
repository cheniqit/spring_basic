package com.mk.hotel.roomtype;

import com.mk.hotel.roomtype.dto.RoomTypeDto;

public interface RoomTypeService {

    RoomTypeDto selectByFangId(Long fangId);

    int saveOrUpdateByFangId(RoomTypeDto roomTypeDto);

    RoomTypeDto selectByFangId(Long fangHotelId, Long fangRoomTypeId);

    void mergeRoomType(int pageNo);

    void mergeRoomTypePrice(int pageNo);

    void mergeRoomTypeStock(int pageNo);
}
