package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.TRoomSaleConfigDto;
import com.mk.taskfactory.api.dtos.TRoomTypeDto;

public interface RoomTypeService {
    public void updatePlusRoomNum(TRoomTypeDto roomTypeDto);
    public Integer saveRoomSaleConfig(TRoomSaleConfigDto bean);
    public Integer delTRoomTypeById(Integer id);
    public Integer updateRoomSaleConfig(TRoomSaleConfigDto bean);
}
