package com.mk.taskfactory.biz.mapper.ots;

import com.mk.taskfactory.api.dtos.QHotelRoomtypeDto;
import com.mk.taskfactory.model.QHotelRoomtype;

import java.util.List;

public interface QHotelRoomtypeMapper {
    public List<QHotelRoomtype> qureyByPramas(QHotelRoomtypeDto bean);
    public QHotelRoomtype getByPramas(QHotelRoomtypeDto bean);
    public Integer save(QHotelRoomtypeDto bean);
    public Integer delete(Integer id);
    public Integer updateById(QHotelRoomtypeDto bean);
    public Integer count(QHotelRoomtypeDto bean);
}