package com.mk.taskfactory.api.ots;

import com.mk.taskfactory.api.dtos.HotelHourClickDto;

import java.util.List;

public interface HotelHourClickService {
    public List<HotelHourClickDto> qureyByPramas(HotelHourClickDto bean);
    public HotelHourClickDto getByPramas(HotelHourClickDto bean);
    public Integer save(HotelHourClickDto bean);
    public Integer delete(Integer id);
    public Integer updateById(HotelHourClickDto bean);
    public Integer count(HotelHourClickDto bean);
}
