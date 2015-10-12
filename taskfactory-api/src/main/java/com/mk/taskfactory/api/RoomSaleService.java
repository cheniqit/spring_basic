package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.TRoomSaleDto;

import java.util.List;

public interface RoomSaleService {
    List<TRoomSaleDto> queryYesterdayRoomSale();
}
