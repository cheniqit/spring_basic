package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.TRoomChangeTypeDto;

public interface RoomService {
    public int countRoomByRoomType(int roomTypeId);
    public void updateRoomTypeByRoomType(TRoomChangeTypeDto roomChangeTypeDto);
}
