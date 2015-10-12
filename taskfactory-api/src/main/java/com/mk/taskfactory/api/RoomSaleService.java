package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.TRoomSaleDto;

import java.util.List;

/**
 * Created by admin on 2015/9/22.
 */
public interface RoomSaleService {
    List<TRoomSaleDto> queryYesterdayRoomSale();
}
