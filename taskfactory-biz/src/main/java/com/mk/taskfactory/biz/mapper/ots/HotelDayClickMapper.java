package com.mk.taskfactory.biz.mapper.ots;

import com.mk.taskfactory.api.dtos.HotelDayClickDto;
import com.mk.taskfactory.model.HotelDayClick;

import java.util.List;

public interface HotelDayClickMapper {
    public List<HotelDayClick> qureyByPramas(HotelDayClickDto bean);
    public HotelDayClick getByPramas(HotelDayClickDto bean);
    public Integer save(HotelDayClickDto bean);
    public Integer delete(Integer id);
    public Integer updateById(HotelDayClickDto bean);
    public Integer count(HotelDayClickDto bean);
}