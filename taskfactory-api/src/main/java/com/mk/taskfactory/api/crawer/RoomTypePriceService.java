package com.mk.taskfactory.api.crawer;

import com.mk.taskfactory.api.dtos.crawer.RoomTypePriceDto;

public interface RoomTypePriceService {
    public RoomTypePriceDto getLastMinOtaPrice(RoomTypePriceDto bean);

}
