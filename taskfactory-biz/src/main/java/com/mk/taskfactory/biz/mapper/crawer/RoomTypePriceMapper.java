package com.mk.taskfactory.biz.mapper.crawer;


import com.mk.taskfactory.api.dtos.crawer.QHotelDto;
import com.mk.taskfactory.api.dtos.crawer.RoomTypePriceDto;
import com.mk.taskfactory.model.crawer.QHotel;
import com.mk.taskfactory.model.crawer.RoomTypePrice;

import java.util.List;

public interface RoomTypePriceMapper {
    public RoomTypePrice getLastMinOtaPrice(RoomTypePriceDto bean);
}