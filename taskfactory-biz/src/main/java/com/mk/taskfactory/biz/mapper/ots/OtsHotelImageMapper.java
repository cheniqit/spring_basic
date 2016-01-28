package com.mk.taskfactory.biz.mapper.ots;

import com.mk.taskfactory.api.dtos.crawer.TExHotelImageDto;
import com.mk.taskfactory.model.crawer.TExHotelImage;

import java.util.List;

public interface OtsHotelImageMapper {
    public List<TExHotelImage> qureyByPramas(TExHotelImageDto bean);
    public TExHotelImage getByPramas(TExHotelImageDto bean);
    public Integer save(TExHotelImageDto bean);
    public Integer delete(Integer id);
    public Integer updateById(TExHotelImageDto bean);
    public Integer count(TExHotelImageDto bean);
}