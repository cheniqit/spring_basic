package com.mk.taskfactory.biz.mapper.ots;


import com.mk.taskfactory.api.dtos.HotelScoreDto;
import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.HotelScore;

import java.util.List;

@MyBatisRepository
public interface HotelScoreMapper {
    public List<HotelScore> queryTHotelScore(HotelScoreDto bean);
    public Integer countTHotelScore(HotelScoreDto bean);
    public HotelScore getByPramas(HotelScoreDto bean);
}
