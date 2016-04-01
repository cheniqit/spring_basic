package com.mk.taskfactory.biz.mapper.crawer;


import com.mk.taskfactory.api.dtos.crawer.RoomTypePriceDto;
import com.mk.taskfactory.model.crawer.RoomTypePrice;

public interface RoomTypePriceMapper {
    public RoomTypePrice getLastMinOtaPrice(RoomTypePriceDto bean);
}