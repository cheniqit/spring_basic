package com.mk.taskfactory.api.crawer;

import com.mk.taskfactory.api.dtos.crawer.QCommentDto;
import com.mk.taskfactory.api.dtos.crawer.QHotelDto;

import java.util.List;

public interface QCommentService {
    public List<QCommentDto> qureyByPramas(QCommentDto bean);
    public QCommentDto getByPramas(QCommentDto bean);
    public Integer save(QCommentDto bean);
    public Integer delete(Integer id);
    public Integer updateById(QCommentDto bean);
    public Integer count(QCommentDto bean);
}
