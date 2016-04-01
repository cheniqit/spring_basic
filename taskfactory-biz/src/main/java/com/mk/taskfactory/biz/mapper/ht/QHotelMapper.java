package com.mk.taskfactory.biz.mapper.ht;


import com.mk.taskfactory.api.dtos.ht.QHotelDto;
import com.mk.taskfactory.model.ht.QHotel;

import java.util.List;

public interface QHotelMapper {
    public List<QHotel> qureyByPramas(QHotelDto bean);
    public QHotel getByPramas(QHotelDto bean);
    public Integer save(QHotelDto bean);
    public Integer delete(Integer id);
    public Integer updateById(QHotelDto bean);
    public Integer count(QHotelDto bean);
}