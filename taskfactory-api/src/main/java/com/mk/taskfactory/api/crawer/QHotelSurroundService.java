package com.mk.taskfactory.api.crawer;

import com.mk.taskfactory.api.dtos.crawer.QHotelDto;
import com.mk.taskfactory.api.dtos.crawer.QHotelSurroundDto;

import java.util.List;

public interface QHotelSurroundService {
    public List<QHotelSurroundDto> qureyByPramas(QHotelSurroundDto bean);
    public QHotelSurroundDto getByPramas(QHotelSurroundDto bean);
    public Integer save(QHotelSurroundDto bean);
    public Integer delete(Integer id);
    public Integer updateById(QHotelSurroundDto bean);
    public Integer count(QHotelSurroundDto bean);
}
