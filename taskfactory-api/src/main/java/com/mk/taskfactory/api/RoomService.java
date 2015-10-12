package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.TRoomDto;

import java.util.List;

/**
 * Created by admin on 2015/9/22.
 */
public interface RoomService {
    public List<TRoomDto> queryRoomByParams(TRoomDto bean);
    public Integer saveTRoom(TRoomDto bean);
    public Integer deleteTRoomByRoomTypeId(Integer roomTypeId);
    public Integer updateRoomById(TRoomDto bean);
    public List<TRoomDto> findRoomsByHotelId(Integer hotelId);
    public List<TRoomDto> findRoomsByRoomTypeId(Integer roomTypeId);
    public TRoomDto findRoomsById(Integer id);
}
