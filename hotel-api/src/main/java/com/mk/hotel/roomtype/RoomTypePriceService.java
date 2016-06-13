package com.mk.hotel.roomtype;

import com.mk.hotel.hotelinfo.enums.HotelSourceEnum;
import com.mk.hotel.roomtype.dto.RoomTypePriceDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface RoomTypePriceService {

    int saveOrUpdateByFangId(RoomTypePriceDto roomTypePriceDto, HotelSourceEnum hotelSourceEnum);

    int saveOrUpdateByFangId(List<RoomTypePriceDto> roomTypePriceDtoList, HotelSourceEnum hotelSourceEnum);

    void updateRedisPrice(Long roomTypeId, String roomTypeName, Date day, BigDecimal price, BigDecimal cost, String cacheFrom);
}
