package com.mk.taskfactory.biz.mapper.ots;


import com.mk.taskfactory.api.dtos.QHotelDto;
import com.mk.taskfactory.model.QHotel;

import java.util.List;

public interface QHotelMapper {
    public List<QHotel> qureyByPramas(QHotelDto bean);
    public QHotel getByPramas(QHotelDto bean);
    public Integer save(QHotelDto bean);
    public Integer delete(Integer id);
    public Integer updateById(QHotelDto bean);
    public Integer count(QHotelDto bean);
}