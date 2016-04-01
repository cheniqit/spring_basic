package com.mk.taskfactory.api.ht;

import com.mk.taskfactory.api.dtos.ht.QCommentDetailDto;

import java.util.List;

public interface QCommentDetailService {
    public List<QCommentDetailDto> qureyByPramas(QCommentDetailDto bean);
    public QCommentDetailDto getByPramas(QCommentDetailDto bean);
    public Integer save(QCommentDetailDto bean);
    public Integer delete(Integer id);
    public Integer updateById(QCommentDetailDto bean);
    public Integer count(QCommentDetailDto bean);
}
