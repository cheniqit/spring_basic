package com.mk.taskfactory.api.crawer;

import com.mk.taskfactory.api.dtos.crawer.QHotelRoomtypeDto;
import com.mk.taskfactory.api.dtos.crawer.RoomTypePriceDto;

import java.util.List;

public interface RoomTypePriceService {
    public RoomTypePriceDto getLastMinOtaPrice(RoomTypePriceDto bean);

}
