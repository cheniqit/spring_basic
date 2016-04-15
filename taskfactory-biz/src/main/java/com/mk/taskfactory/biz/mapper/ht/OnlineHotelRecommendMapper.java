package com.mk.taskfactory.biz.mapper.ht;

import com.mk.taskfactory.api.dtos.ht.OnlineHotelRecommendDto;
import com.mk.taskfactory.model.ht.OnlineHotelRecommend;

import java.util.List;

public interface OnlineHotelRecommendMapper {
    public List<OnlineHotelRecommend> qureyByPramas(OnlineHotelRecommendDto bean);
    public OnlineHotelRecommend getByPramas(OnlineHotelRecommendDto bean);
    public Integer save(OnlineHotelRecommendDto bean);
    public Integer delete(Integer id);
    public Integer updateById(OnlineHotelRecommendDto bean);
    public Integer count(OnlineHotelRecommendDto bean);
}