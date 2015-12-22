package com.mk.taskfactory.biz.mapper.ots;

import com.mk.taskfactory.api.dtos.TRoomSaleTypeDto;
import com.mk.taskfactory.model.TRoomSaleType;

import java.util.List;

public interface RoomSaleTypeMapper {
    public List<TRoomSaleType> queryRoomSaleType (TRoomSaleTypeDto dto);
    public int saveRoomSaleType(TRoomSaleTypeDto dto);
    public int deleteRoomSaleType(int id);
    public int updateRoomSaleType(TRoomSaleTypeDto dto);
}
