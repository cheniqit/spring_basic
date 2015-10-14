package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.api.RoomSettingService;
import com.mk.taskfactory.api.dtos.TRoomChangeTypeDto;
import com.mk.taskfactory.api.dtos.TRoomSettingDto;
import com.mk.taskfactory.biz.mapper.roomsale.RoomSettingMapper;
import com.mk.taskfactory.model.TRoomSetting;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomSettingServiceImpl implements RoomSettingService {

    @Autowired
    private RoomSettingMapper roomTypeSettingMapper;

    public void deleteByRoomType(Integer roomTypeId) {
        this.roomTypeSettingMapper.deleteByRoomType(roomTypeId);
    }

    public TRoomSettingDto selectByRoomTypeIdAndRoomNo(TRoomSettingDto bean){
        TRoomSetting roomSetting= roomTypeSettingMapper.selectByRoomTypeIdAndRoomNo(bean);
        return buildTRoomSettingDto(roomSetting);
    }
    public Integer saveTRoomSetting(TRoomSettingDto bean){
        return roomTypeSettingMapper.saveTRoomSetting(bean);
    }
    public void updateTRoomSetting(TRoomSettingDto bean){
         roomTypeSettingMapper.updateTRoomSetting(bean);
    }
    public void updateTRoomSettingByRoomTypeId(TRoomChangeTypeDto bean){
        roomTypeSettingMapper.updateTRoomSettingByRoomTypeId(bean);
    }
    private TRoomSettingDto buildTRoomSettingDto(TRoomSetting bean) {
        if (bean==null){
            return new TRoomSettingDto();
        }
        TRoomSettingDto roomSettingDto=new TRoomSettingDto();
        BeanUtils.copyProperties(bean, roomSettingDto);
        return roomSettingDto;
    }
}
