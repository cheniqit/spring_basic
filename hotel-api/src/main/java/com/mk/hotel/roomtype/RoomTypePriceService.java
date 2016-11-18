package com.mk.hotel.roomtype;

import com.mk.hotel.hotelinfo.enums.HotelSourceEnum;
import com.mk.hotel.roomtype.dto.RoomTypePriceDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface RoomTypePriceService {

    int saveOrUpdateByFangId(RoomTypePriceDto roomTypePriceDto, HotelSourceEnum hotelSourceEnum);

    int saveOrUpdateByFangId(List<RoomTypePriceDto> roomTypePriceDtoList, HotelSourceEnum hotelSourceEnum);

    void updateRedisPrice(Long roomTypeId, String roomTypeName, Date day, BigDecimal price, BigDecimal cost, BigDecimal settlePrice, String cacheFrom);

    RoomTypePriceDto queryFromDb(Long roomTypeId, Date date);

    List<RoomTypePriceDto> queryFromDb(Long roomTypeId, Date fromDate, Date toDate);

    RoomTypePriceDto queryFromRedis(Long roomTypeId, Date day);

    List<RoomTypePriceDto> queryFromRedis(Long roomTypeId, Date fromDate, Date toDate);

    int savePersistToDb(Long roomTypeId, Date date);
}
