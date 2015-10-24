package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.TRoomTypeInfoDto;

public interface RoomTypeInfoService {
    public void deleteByRoomType(Integer roomTypeId);
    public TRoomTypeInfoDto findByRoomTypeId(Integer roomTypeId);
    public Integer saveRoomTypeInfo(TRoomTypeInfoDto bean);

}
