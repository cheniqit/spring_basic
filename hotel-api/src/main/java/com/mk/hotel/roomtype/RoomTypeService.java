package com.mk.hotel.roomtype;

import com.mk.hotel.hotelinfo.enums.HotelSourceEnum;
import com.mk.hotel.roomtype.dto.RoomTypeDto;

import java.util.List;

public interface RoomTypeService {

    RoomTypeDto selectByFangId(Long fangId, Long hotelId);

    RoomTypeDto selectById(Long roomTypeId);

    RoomTypeDto selectByName(Long hotelId, String name);

    int saveOrUpdateByFangId(RoomTypeDto roomTypeDto, HotelSourceEnum hotelSourceEnum);

    void saveOrUpdateByHotelId(Long hotelId, List<RoomTypeDto> roomTypeDtoList, HotelSourceEnum hotelSourceEnum);

    RoomTypeDto selectByFangId(Long fangHotelId, Long fangRoomTypeId, HotelSourceEnum hotelSourceEnum);

    void mergeRoomType(int pageNo);

    void mergeRoomTypePrice(int pageNo);

    void mergeRoomTypePriceByHotelId(Long hotelId);

    void mergeRoomTypeStock(int pageNo);

    Long getHotelIdByRedis (Long roomTypeId);

    void deleteByHotelId(Long hotelId, List<Long> idList);
    void updateOnlineByHotelId(Long hotelId, List<Long> idList);

    void mergeRoomTypeStockByHotel(Long hotelId);

    void mergeRoomTypeDayStockByHotel(Long hotelId);

    void mergeRoomTypeDayStock(Integer pageNo);

    void mergeRoomTypeByHotelId(Long hotelId);

    void clearStockAndPrice();
}
