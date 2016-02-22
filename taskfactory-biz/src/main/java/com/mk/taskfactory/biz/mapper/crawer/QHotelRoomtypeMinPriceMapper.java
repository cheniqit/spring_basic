package com.mk.taskfactory.biz.mapper.crawer;

import com.mk.taskfactory.api.dtos.crawer.QFacilityDto;
import com.mk.taskfactory.api.dtos.crawer.QHotelRoomtypeMinPriceDto;
import com.mk.taskfactory.model.crawer.QFacility;
import com.mk.taskfactory.model.crawer.QHotelRoomtypeMinPrice;

import java.util.List;

public interface QHotelRoomtypeMinPriceMapper {
    public List<QHotelRoomtypeMinPrice> qureyByPramas(QHotelRoomtypeMinPriceDto bean);
    public QHotelRoomtypeMinPrice getByPramas(QHotelRoomtypeMinPriceDto bean);
    public Integer save(QHotelRoomtypeMinPriceDto bean);
    public Integer delete(Integer id);
    public Integer updateById(QHotelRoomtypeMinPriceDto bean);
    public Integer count(QHotelRoomtypeMinPriceDto bean);
}