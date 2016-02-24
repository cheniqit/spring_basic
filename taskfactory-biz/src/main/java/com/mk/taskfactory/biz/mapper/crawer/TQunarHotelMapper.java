package com.mk.taskfactory.biz.mapper.crawer;

import com.mk.taskfactory.api.dtos.crawer.TExCommentImgDto;
import com.mk.taskfactory.api.dtos.crawer.TQunarHotelDto;
import com.mk.taskfactory.model.crawer.TExCommentImg;
import com.mk.taskfactory.model.crawer.TQunarHotel;

import java.util.List;

public interface TQunarHotelMapper {
    public List<TQunarHotel> qureyByPramas(TQunarHotelDto bean);
    public TQunarHotel getByPramas(TQunarHotelDto bean);
    public Integer save(TQunarHotelDto bean);
    public Integer delete(Integer id);
    public Integer updateById(TQunarHotelDto bean);
    public Integer count(TQunarHotelDto bean);
}