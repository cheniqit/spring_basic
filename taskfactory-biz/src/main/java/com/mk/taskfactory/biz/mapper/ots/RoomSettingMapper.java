package com.mk.taskfactory.biz.mapper.ots;

import com.mk.taskfactory.api.dtos.TRoomChangeTypeDto;
import com.mk.taskfactory.api.dtos.TRoomSettingDto;
import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.TRoomSetting;

import java.util.HashMap;

@MyBatisRepository
public interface RoomSettingMapper {
    public void deleteByRoomType(Integer roomTypeId);
    public TRoomSetting selectByRoomTypeIdAndRoomNo(TRoomSettingDto bean);
    public Integer saveTRoomSetting(TRoomSettingDto bean);
    public void updateTRoomSetting(TRoomSettingDto bean);
    public void updateTRoomSettingByRoomTypeId(TRoomChangeTypeDto bean);

    public  int  updateRoomTypeByRoomNo(HashMap hm);
    }
