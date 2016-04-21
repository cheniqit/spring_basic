package com.mk.taskfactory.api.ots;

import com.mk.taskfactory.api.dtos.HotelDensityDto;

import java.util.List;

public interface HotelDensityService {
    public List<HotelDensityDto> qureyByPramas(HotelDensityDto bean);
    public HotelDensityDto getByPramas(HotelDensityDto bean);
    public Integer save(HotelDensityDto bean);
    public Integer saveOrUpdate(HotelDensityDto bean);
    public Integer deleteByHotelId(String hotelId );
    public Integer delete(Integer id);
    public Integer updateByHotelId(HotelDensityDto bean);
    public Integer count(HotelDensityDto bean);
}
