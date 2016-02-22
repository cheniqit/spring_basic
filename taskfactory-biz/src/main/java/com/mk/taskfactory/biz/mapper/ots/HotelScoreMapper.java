package com.mk.taskfactory.biz.mapper.ots;


import com.mk.taskfactory.api.dtos.HotelScoreDto;
import com.mk.taskfactory.api.dtos.crawer.QCommentDetailDto;
import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.HotelScore;
import com.mk.taskfactory.model.UCardUseLog;
import com.mk.taskfactory.model.crawer.QCommentDetail;

import java.util.List;

@MyBatisRepository
public interface HotelScoreMapper {
    public List<HotelScore> queryTHotelScore(HotelScoreDto bean);
    public Integer countTHotelScore(HotelScoreDto bean);
}
