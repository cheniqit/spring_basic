package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.TRoomTypeDto;

public interface RoomTypeService {
    public void updatePlusRoomNum(TRoomTypeDto roomTypeDto);
    public Integer saveRoomSaleConfig(TRoomTypeDto bean);
    public Integer delTRoomTypeById(Integer id);
    public Integer updateRoomSaleConfig(TRoomTypeDto bean);
    public TRoomTypeDto findTRoomTypeById(Integer id);
}
