package com.mk.taskfactory.api.ht;

import com.mk.taskfactory.api.dtos.ht.QCommentDto;

import java.util.List;

public interface QCommentService {
    public List<QCommentDto> qureyByPramas(QCommentDto bean);
    public QCommentDto getByPramas(QCommentDto bean);
    public Integer save(QCommentDto bean);
    public Integer delete(Integer id);
    public Integer updateById(QCommentDto bean);
    public Integer count(QCommentDto bean);
}
