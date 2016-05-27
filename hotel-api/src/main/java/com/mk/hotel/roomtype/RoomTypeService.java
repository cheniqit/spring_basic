package com.mk.hotel.roomtype;

import com.mk.hotel.roomtype.dto.RoomTypeDto;

import java.util.List;

public interface RoomTypeService {

    RoomTypeDto selectByFangId(Long fangId);

    RoomTypeDto selectById(Long roomTypeId);

    RoomTypeDto selectByName(Long hotelId, String name);

    int saveOrUpdateByFangId(RoomTypeDto roomTypeDto);

    void saveOrUpdateByHotelId(Long hotelId, List<RoomTypeDto> roomTypeDtoList);

    RoomTypeDto selectByFangId(Long fangHotelId, Long fangRoomTypeId);

    void mergeRoomType(int pageNo);

    void mergeRoomTypePrice(int pageNo);

    void mergeRoomTypeStock(int pageNo);

    Long getHotelIdByRedis (Long roomTypeId);

    void deleteByHotelId(Long hotelId, List<Long> idList);
    void updateOnlineByHotelId(Long hotelId, List<Long> idList);

    void mergeRoomTypeStockByHotel(Long hotelId);

    void mergeRoomTypeDayStockByHotel(Long hotelId);

    void mergeRoomTypeDayStock(Integer pageNo);

    void mergeRoomTypeByHotelId(Long hotelId);
}
