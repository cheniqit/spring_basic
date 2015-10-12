package com.mk.taskfactory.biz.mapper;


import com.mk.taskfactory.api.dtos.TRoomDto;
import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.TRoom;

import java.util.List;

@MyBatisRepository
public interface RoomMapper {
    public List<TRoom> queryRoomByParams(TRoomDto bean);
    public Integer saveTRoom(TRoomDto bean);
    public Integer deleteTRoomByRoomTypeId(Integer roomTypeId);
    public Integer updateRoomById(TRoomDto bean);
    public List<TRoom> findRoomsByHotelId(Integer hotelId);

    public Integer countRoomByRoomType(int roomTypeId);
}
