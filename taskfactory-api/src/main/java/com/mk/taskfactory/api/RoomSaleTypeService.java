package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.TRoomSaleTypeDto;

import java.util.List;

public interface RoomSaleTypeService {

    public List<TRoomSaleTypeDto> queryRoomSaleType (TRoomSaleTypeDto dto);
    public int saveRoomSaleType(TRoomSaleTypeDto dto);
    public int deleteRoomSaleType(int id);
    public int updateRoomSaleType(TRoomSaleTypeDto dto);
}
