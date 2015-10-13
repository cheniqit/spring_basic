package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.api.RoomTypeService;
import com.mk.taskfactory.api.dtos.TRoomTypeDto;
import com.mk.taskfactory.biz.mapper.RoomTypeMapper;
import com.mk.taskfactory.model.TRoomType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {

    @Autowired
    private RoomTypeMapper roomTypeMapper;

    @Override
    public void updatePlusRoomNum(TRoomTypeDto roomTypeDto) {
        if (null ==  roomTypeDto || null == roomTypeDto.getId() || null == roomTypeDto.getRoomNum()) {
            //TODO LOG
            return;
        }
        this.roomTypeMapper.updatePlusRoomNum(roomTypeDto);
    }
    public Integer saveTRoomType(TRoomTypeDto bean){
        return null;
    }
    public Integer delTRoomTypeById(Integer id){
        return  null;
    }
    public Integer updateTRoomType(TRoomTypeDto bean){
        return null;
    }
    public TRoomTypeDto findTRoomTypeById(Integer id){
        TRoomType roomType=roomTypeMapper.findTRoomTypeById(id);
        if (roomType==null){
            return  null;
        }
        return  buildTRoomTypeDto(roomType);
    }
    private TRoomTypeDto buildTRoomTypeDto(TRoomType bean) {
        if (bean==null){
            return new TRoomTypeDto();
        }
        TRoomTypeDto roomTypeDto=new TRoomTypeDto();
        BeanUtils.copyProperties(bean, roomTypeDto);
        return roomTypeDto;
    }
}
