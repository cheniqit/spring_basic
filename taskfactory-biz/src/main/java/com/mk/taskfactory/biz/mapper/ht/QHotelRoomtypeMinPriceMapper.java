package com.mk.taskfactory.biz.mapper.ht;

import com.mk.taskfactory.api.dtos.ht.QHotelRoomtypeMinPriceDto;
import com.mk.taskfactory.model.ht.QHotelRoomtypeMinPrice;

import java.util.List;

public interface QHotelRoomtypeMinPriceMapper {
    public List<QHotelRoomtypeMinPrice> qureyByPramas(QHotelRoomtypeMinPriceDto bean);
    public QHotelRoomtypeMinPrice getByPramas(QHotelRoomtypeMinPriceDto bean);
    public Integer save(QHotelRoomtypeMinPriceDto bean);
    public Integer delete(Integer id);
    public Integer updateById(QHotelRoomtypeMinPriceDto bean);
    public Integer count(QHotelRoomtypeMinPriceDto bean);
}