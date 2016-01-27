package com.mk.taskfactory.biz.mapper.ots;

import com.mk.taskfactory.api.dtos.HotelHourClickDto;
import com.mk.taskfactory.model.HotelHourClick;

import java.util.List;

public interface HotelHourClickMapper {
    public List<HotelHourClick> qureyByPramas(HotelHourClickDto bean);
    public HotelHourClick getByPramas(HotelHourClickDto bean);
    public Integer save(HotelHourClickDto bean);
    public Integer delete(Integer id);
    public Integer updateById(HotelHourClickDto bean);
    public Integer count(HotelHourClickDto bean);
}