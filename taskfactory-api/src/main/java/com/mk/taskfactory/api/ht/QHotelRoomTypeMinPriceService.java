package com.mk.taskfactory.api.ht;

import com.mk.taskfactory.api.dtos.ht.QHotelRoomtypeMinPriceDto;

import java.util.List;

public interface QHotelRoomTypeMinPriceService {
    public List<QHotelRoomtypeMinPriceDto> qureyByPramas(QHotelRoomtypeMinPriceDto bean);
    public QHotelRoomtypeMinPriceDto getByPramas(QHotelRoomtypeMinPriceDto bean);
    public Integer save(QHotelRoomtypeMinPriceDto bean);
    public Integer delete(Integer id);
    public Integer updateById(QHotelRoomtypeMinPriceDto bean);
    public Integer count(QHotelRoomtypeMinPriceDto bean);

}
