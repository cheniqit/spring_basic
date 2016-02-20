package com.mk.taskfactory.api.crawer;

import com.mk.taskfactory.api.dtos.crawer.QCommentDetailDto;
import com.mk.taskfactory.api.dtos.crawer.QCommentDto;
import com.mk.taskfactory.api.dtos.crawer.QHotelDto;

import java.util.List;

public interface QCommentDetailService {
    public List<QCommentDetailDto> qureyByPramas(QCommentDetailDto bean);
    public QCommentDetailDto getByPramas(QCommentDetailDto bean);
    public Integer save(QCommentDetailDto bean);
    public Integer delete(Integer id);
    public Integer updateById(QCommentDetailDto bean);
    public Integer count(QCommentDetailDto bean);
}
