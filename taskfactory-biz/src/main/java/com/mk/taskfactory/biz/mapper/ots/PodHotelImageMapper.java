package com.mk.taskfactory.biz.mapper.ots;

import com.mk.taskfactory.api.dtos.PodHotelImageDto;
import com.mk.taskfactory.model.PodHotelImage;

import java.util.List;

public interface PodHotelImageMapper {
    public List<PodHotelImage> qureyByPramas(PodHotelImageDto bean);
    public PodHotelImage getByPramas(PodHotelImageDto bean);
    public Integer save(PodHotelImageDto bean);
    public Integer delete(Integer id);
    public Integer updateById(PodHotelImageDto bean);
    public Integer count(PodHotelImageDto bean);
}