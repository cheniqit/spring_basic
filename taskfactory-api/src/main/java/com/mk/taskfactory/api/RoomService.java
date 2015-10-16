package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.TRoomChangeTypeDto;
import com.mk.taskfactory.api.dtos.TRoomDto;

import java.util.List;

public interface RoomService {
    public int countRoomByRoomType(int roomTypeId);
    public void updateRoomTypeByRoomType(TRoomChangeTypeDto roomChangeTypeDto);
    public List<TRoomDto> queryRoomByParams(TRoomDto bean);
    public Integer saveTRoom(TRoomDto bean);
    public Integer deleteTRoomByRoomTypeId(Integer roomTypeId);
    public Integer updateRoomById(TRoomDto bean);
    public List<TRoomDto> findRoomsByHotelId(Integer hotelId);
    public List<TRoomDto> findRoomsByRoomTypeId(Integer roomTypeId);
    public TRoomDto findRoomsById(Integer id);

    public TRoomDto queryRoomByName(TRoomDto bean) throws Exception;
}
