package com.mk.taskfactory.api.ots;

import com.mk.taskfactory.api.dtos.crawer.TExHotelImageDto;

import java.util.List;

public interface OtsHotelImageService {
    public List<TExHotelImageDto> qureyByPramas(TExHotelImageDto bean);
    public TExHotelImageDto getByPramas(TExHotelImageDto bean);
    public Integer save(TExHotelImageDto bean);
    public Integer delete(Integer id);
    public Integer updateById(TExHotelImageDto bean);
    public Integer count(TExHotelImageDto bean);
}
