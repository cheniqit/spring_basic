package com.mk.taskfactory.api.ots;

import com.mk.taskfactory.api.dtos.PodHotelImageDto;

import java.util.List;

public interface PodHotelImageService {
    public List<PodHotelImageDto> qureyByPramas(PodHotelImageDto bean);
    public PodHotelImageDto getByPramas(PodHotelImageDto bean);
    public Integer save(PodHotelImageDto bean);
    public Integer delete(Integer id);
    public Integer updateById(PodHotelImageDto bean);
    public Integer count(PodHotelImageDto bean);
}
