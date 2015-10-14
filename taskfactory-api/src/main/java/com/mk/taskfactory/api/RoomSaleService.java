package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.RoomSaleToOtsDto;
import com.mk.taskfactory.api.dtos.TRoomSaleDto;

import java.util.List;

public interface RoomSaleService {
    public List<TRoomSaleDto> queryYesterdayRoomSale();
    public List<TRoomSaleDto> queryUnBackRoomSale();
    public void saveRoomSale(TRoomSaleDto roomSaleDto);
    public void updateRoomSaleBack(TRoomSaleDto roomSaleDto);
    public List<RoomSaleToOtsDto> querySaleRoom(TRoomSaleDto bean);
}
