package com.mk.taskfactory.biz.mapper.ots;

import com.mk.taskfactory.api.dtos.QHotelSurroundDto;
import com.mk.taskfactory.model.QHotelSurround;

import java.util.List;

public interface QHotelSurroundMapper {
    public List<QHotelSurround> qureyByPramas(QHotelSurroundDto bean);
    public QHotelSurround getByPramas(QHotelSurroundDto bean);
    public Integer save(QHotelSurroundDto bean);
    public Integer delete(Integer id);
    public Integer updateById(QHotelSurroundDto bean);
    public Integer count(QHotelSurroundDto bean);
}