package com.mk.taskfactory.biz.mapper.crawer;

import com.mk.taskfactory.api.dtos.crawer.QHotelRoomtypeDto;
import com.mk.taskfactory.model.crawer.QHotelRoomtype;

import java.util.List;

public interface QHotelRoomtypeMapper {
    public List<QHotelRoomtype> qureyByPramas(QHotelRoomtypeDto bean);
    public QHotelRoomtype getByPramas(QHotelRoomtypeDto bean);
    public Integer save(QHotelRoomtypeDto bean);
    public Integer delete(Integer id);
    public Integer updateById(QHotelRoomtypeDto bean);
    public Integer count(QHotelRoomtypeDto bean);
    public QHotelRoomtype getRoomtypeImg(QHotelRoomtypeDto bean);

}