package com.mk.hotel.roomtype;

import com.mk.hotel.roomtype.dto.RoomTypeDto;

public interface RoomTypeService {

    int save(RoomTypeDto roomTypeDto);

    RoomTypeDto selectByFangId(Long fangId);

    int update(RoomTypeDto roomTypeDto);

    int saveOrUpdateByFangId(RoomTypeDto roomTypeDto);

    RoomTypeDto selectByFangId(Long fangHotelId, Long fangRoomTypeId);

    void mergeRoomType(int pageNo);
}
