package com.mk.taskfactory.api.ots;

import com.mk.taskfactory.api.dtos.HotelDayClickDto;

import java.util.List;

public interface HotelDayClickService {
    public List<HotelDayClickDto> qureyByPramas(HotelDayClickDto bean);
    public HotelDayClickDto getByPramas(HotelDayClickDto bean);
    public Integer save(HotelDayClickDto bean);
    public Integer delete(Integer id);
    public Integer updateById(HotelDayClickDto bean);
    public Integer count(HotelDayClickDto bean);
}
