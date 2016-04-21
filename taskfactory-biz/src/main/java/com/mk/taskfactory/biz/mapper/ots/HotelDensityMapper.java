package com.mk.taskfactory.biz.mapper.ots;


import com.mk.taskfactory.api.dtos.HotelDensityDto;

import java.util.List;

public interface HotelDensityMapper {
    public List<HotelDensityDto> qureyByPramas(HotelDensityDto bean);
    public HotelDensityDto getByPramas(HotelDensityDto bean);
    public Integer save(HotelDensityDto bean);
    public Integer saveOrUpdate(HotelDensityDto bean);
    public Integer delete(Integer id);
    public Integer deleteByHotelId(String hotelId );
    public Integer updateByHotelId(HotelDensityDto bean);
    public Integer count(HotelDensityDto bean);
}