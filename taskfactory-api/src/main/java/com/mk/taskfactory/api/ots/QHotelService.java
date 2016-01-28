package com.mk.taskfactory.api.ots;

import com.mk.taskfactory.api.dtos.HotelDayClickDto;
import com.mk.taskfactory.api.dtos.QHotelDto;

import java.util.List;

public interface QHotelService {
    public List<QHotelDto> qureyByPramas(QHotelDto bean);
    public QHotelDto getByPramas(QHotelDto bean);
    public Integer save(QHotelDto bean);
    public Integer delete(Integer id);
    public Integer updateById(QHotelDto bean);
    public Integer count(QHotelDto bean);
}
