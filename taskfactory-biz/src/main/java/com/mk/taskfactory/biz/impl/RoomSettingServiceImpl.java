package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.api.RoomSettingService;
import com.mk.taskfactory.api.dtos.TRoomChangeTypeDto;
import com.mk.taskfactory.api.dtos.TRoomSettingDto;
import com.mk.taskfactory.biz.mapper.RoomSettingMapper;
import com.mk.taskfactory.model.TRoomSetting;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class RoomSettingServiceImpl implements RoomSettingService {

    @Autowired
    private RoomSettingMapper roomTypeSettingMapper;

    public void deleteByRoomType(Integer roomTypeId) {
        this.roomTypeSettingMapper.deleteByRoomType(roomTypeId);
    }

    public TRoomSettingDto selectByRoomTypeIdAndRoomNo(TRoomSettingDto bean){
        TRoomSetting roomSetting= roomTypeSettingMapper.selectByRoomTypeIdAndRoomNo(bean);
        if (null == roomSetting) {
            return null;
        }
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

    public  int  updateRoomTypeByRoomNo(String  roomNo,Integer oldRoomTypeId,Integer  roomTypeId){
        HashMap  hm = new HashMap();
        hm.put("roomNo",roomNo);
        hm.put("oldRoomTypeId",oldRoomTypeId);
        hm.put("roomTypeId",roomTypeId);
        return  this.roomTypeSettingMapper.updateRoomTypeByRoomNo(hm);
    }

}
