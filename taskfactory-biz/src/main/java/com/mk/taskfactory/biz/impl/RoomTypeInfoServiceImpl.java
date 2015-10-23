package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.api.RoomTypeInfoService;
import com.mk.taskfactory.api.dtos.TRoomTypeInfoDto;
import com.mk.taskfactory.biz.mapper.RoomTypeInfoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomTypeInfoServiceImpl implements RoomTypeInfoService {

    @Autowired
    private RoomTypeInfoMapper roomTypeInfoMapper;

    @Override
    public void deleteByRoomType(Integer roomTypeId) {
        if(roomTypeId==null){
            return;
        }
        this.roomTypeInfoMapper.deleteByRoomType(roomTypeId);
    }
    public TRoomTypeInfoDto findByRoomTypeId(Integer roomTypeId){
        TRoomTypeInfoDto roomTypeInfo=roomTypeInfoMapper.findByRoomTypeId(roomTypeId);
        if (roomTypeInfo==null){
            return  null;
        }
        return  buildTRoomTypeInfoDto(roomTypeInfo);
    }
    public Integer saveRoomTypeInfo(TRoomTypeInfoDto bean){
        return  roomTypeInfoMapper.saveRoomTypeInfo(bean);
    }
    private TRoomTypeInfoDto buildTRoomTypeInfoDto(TRoomTypeInfoDto bean) {
        if (bean==null){
            return new TRoomTypeInfoDto();
        }
        TRoomTypeInfoDto roomTypeInfoDto=new TRoomTypeInfoDto();
        BeanUtils.copyProperties(bean, roomTypeInfoDto);
        return roomTypeInfoDto;
    }
}
