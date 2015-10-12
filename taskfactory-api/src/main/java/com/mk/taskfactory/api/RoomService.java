package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.TRoomChangeTypeDto;

/**
 * Created by admin on 2015/9/22.
 */
public interface RoomService {
    public int countRoomByRoomType(int roomTypeId);
    public void updateRoomTypeByRoomType(TRoomChangeTypeDto roomChangeTypeDto);
}
