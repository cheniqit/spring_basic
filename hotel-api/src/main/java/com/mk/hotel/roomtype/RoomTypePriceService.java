package com.mk.hotel.roomtype;

import com.mk.hotel.roomtype.dto.RoomTypePriceDto;

import java.util.List;

public interface RoomTypePriceService {

    int saveOrUpdateByFangId(RoomTypePriceDto roomTypePriceDto);

    int saveOrUpdateByFangId(List<RoomTypePriceDto> roomTypePriceDtoList);
}
