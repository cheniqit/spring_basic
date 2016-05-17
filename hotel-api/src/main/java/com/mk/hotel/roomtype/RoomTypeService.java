package com.mk.hotel.roomtype;

import com.mk.hotel.roomtype.dto.RoomTypeDto;

import java.util.List;

public interface RoomTypeService {

    RoomTypeDto selectByFangId(Long fangId);

    int saveOrUpdateByFangId(RoomTypeDto roomTypeDto);

    void saveOrUpdateByHotelId(Long hotelId, List<RoomTypeDto> roomTypeDtoList);

    RoomTypeDto selectByFangId(Long fangHotelId, Long fangRoomTypeId);

    void mergeRoomType(int pageNo);

    void mergeRoomTypePrice(int pageNo);

    void mergeRoomTypeStock(int pageNo);
}
