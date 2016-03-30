package com.mk.taskfactory.api.ht;

import com.mk.taskfactory.api.dtos.ht.QHotelRoomtypeDto;

import java.util.List;

public interface QHotelRoomTypeService {
    public List<QHotelRoomtypeDto> qureyByPramas(QHotelRoomtypeDto bean);
    public QHotelRoomtypeDto getByPramas(QHotelRoomtypeDto bean);
    public Integer save(QHotelRoomtypeDto bean);
    public Integer delete(Integer id);
    public Integer updateById(QHotelRoomtypeDto bean);
    public Integer count(QHotelRoomtypeDto bean);
    public QHotelRoomtypeDto getRoomtypeImg(QHotelRoomtypeDto bean);

}
