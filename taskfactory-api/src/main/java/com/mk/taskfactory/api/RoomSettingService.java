package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.TRoomChangeTypeDto;
import com.mk.taskfactory.api.dtos.TRoomSettingDto;

public interface RoomSettingService {
    public void deleteByRoomType(Integer roomTypeId);
    public TRoomSettingDto selectByRoomTypeIdAndRoomNo(TRoomSettingDto bean);
    public Integer saveTRoomSetting(TRoomSettingDto bean);
    public void updateTRoomSetting(TRoomSettingDto bean);
    public void updateTRoomSettingByRoomTypeId(TRoomChangeTypeDto bean);

    public  int  updateRoomTypeByRoomNo(String  roomNo,Integer oldRoomTypeId,Integer  roomTypeId);
}
