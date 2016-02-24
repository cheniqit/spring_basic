package com.mk.taskfactory.api.crawer;

import com.mk.taskfactory.api.dtos.crawer.TExCommentImgDto;
import com.mk.taskfactory.api.dtos.crawer.TQunarHotelDto;

import java.util.List;

public interface TQunarHotelService {
    public List<TQunarHotelDto> qureyByPramas(TQunarHotelDto bean);
    public TQunarHotelDto getByPramas(TQunarHotelDto bean);
    public Integer save(TQunarHotelDto bean);
    public Integer delete(Integer id);
    public Integer updateById(TQunarHotelDto bean);
    public Integer count(TQunarHotelDto bean);
}
