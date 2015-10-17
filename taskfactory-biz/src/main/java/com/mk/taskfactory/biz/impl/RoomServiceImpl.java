package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.api.RoomService;
import com.mk.taskfactory.api.dtos.TRoomChangeTypeDto;
import com.mk.taskfactory.api.dtos.TRoomDto;
import com.mk.taskfactory.biz.mapper.RoomMapper;
import com.mk.taskfactory.model.TRoom;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public int countRoomByRoomType(int roomTypeId) {
        return this.roomMapper.countRoomByRoomType(roomTypeId);
    }

    @Override
    public void updateRoomTypeByRoomType(TRoomChangeTypeDto roomChangeTypeDto) {
        if (null == roomChangeTypeDto
                || null == roomChangeTypeDto.getOldRoomTypeId()
                || null == roomChangeTypeDto.getRoomTypeId()) {
            //TODO LOG
            return;
        }
        this.roomMapper.updateRoomTypeByRoomType(roomChangeTypeDto);
    }

    public  List<TRoomDto> queryRoomByParams(TRoomDto bean){
        List<TRoom> list=roomMapper.queryRoomByParams(bean);
        if (list==null){
            return  null;
        }
        List<TRoomDto> roomDtos=new ArrayList<TRoomDto>();
        for (TRoom room :list) {
            roomDtos.add(buildTRoomDto(room));
        }
        return  roomDtos;
    }
    public Integer saveTRoom(TRoomDto bean){
        return  roomMapper.saveTRoom(bean);
    }
    public Integer deleteTRoomByRoomTypeId(Integer roomTypeId){
        return  roomMapper.deleteTRoomByRoomTypeId(roomTypeId);
    }
    public Integer updateRoomById(TRoomDto bean){
        return roomMapper.updateRoomById(bean);
    }
    public List<TRoomDto> findRoomsByHotelId(Integer hotelId){
        List<TRoom> list=roomMapper.findRoomsByHotelId(hotelId);
        if (list==null){
            return  null;
        }
        List<TRoomDto> roomDtos=new ArrayList<TRoomDto>();
        for (TRoom room :list) {
            roomDtos.add(buildTRoomDto(room));
        }
        return  roomDtos;
    }
    public List<TRoomDto> findRoomsByRoomTypeId(Integer roomTypeId){
        List<TRoom> list=roomMapper.findRoomsByRoomTypeId(roomTypeId);
        if (list==null){
            return  null;
        }
        List<TRoomDto> roomDtos=new ArrayList<TRoomDto>();
        for (TRoom room :list) {
            roomDtos.add(buildTRoomDto(room));
        }
        return  roomDtos;
    }
    public TRoomDto findRoomsById(Integer id){
        TRoom room=roomMapper.findRoomsById(id);
        if (room==null){
            return  null;
        }
        return  buildTRoomDto(room);
    }
    private TRoomDto buildTRoomDto(TRoom bean) {
        if (bean==null){
            return new TRoomDto();
        }
        TRoomDto roomDto=new TRoomDto();
        BeanUtils.copyProperties(bean, roomDto);
        return roomDto;
    }

    public TRoomDto queryRoomByName(TRoomDto bean) throws Exception {
        List<TRoom> roomList = this.roomMapper.queryRoomByParams(bean);
        if (roomList.isEmpty()) {
            throw new Exception("无房间");
        }
        TRoom room = roomList.get(0);

        TRoomDto roomDto = new TRoomDto();
        BeanUtils.copyProperties(room, roomDto);

        return roomDto;
    }

    public  Integer  updateRoomTypeByRoomType(String name,String  pms,Integer  newRoomTypeId){
        HashMap  hm = new  HashMap();
        hm.put("name",name);
        hm.put("pms",pms);
        hm.put("newRoomTypeId",newRoomTypeId);
        return  this.roomMapper.updateRoomTypeByRoomType(hm);
    }

}
