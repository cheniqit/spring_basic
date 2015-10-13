package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.TRoomSettingDto;

public interface RoomSettingService {
    public void deleteByRoomType(int roomTypeId);
    public TRoomSettingDto selectByRoomTypeIdAndRoomNo(TRoomSettingDto bean);
    public Integer saveTRoomSetting(TRoomSettingDto bean);
    public void updateTRoomSetting(TRoomSettingDto bean);
}
