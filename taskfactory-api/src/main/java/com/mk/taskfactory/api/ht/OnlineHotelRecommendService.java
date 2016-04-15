package com.mk.taskfactory.api.ht;

import com.mk.taskfactory.api.dtos.ht.OnlineHotelRecommendDto;

import java.util.List;

public interface OnlineHotelRecommendService {
    public List<OnlineHotelRecommendDto> qureyByPramas(OnlineHotelRecommendDto bean);
    public OnlineHotelRecommendDto getByPramas(OnlineHotelRecommendDto bean);
    public Integer save(OnlineHotelRecommendDto bean);
    public Integer delete(Integer id);
    public Integer updateById(OnlineHotelRecommendDto bean);
    public Integer count(OnlineHotelRecommendDto bean);
}
