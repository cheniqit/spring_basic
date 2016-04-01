package com.mk.taskfactory.biz.mapper.ht;

import com.mk.taskfactory.api.dtos.ht.QCommentDto;
import com.mk.taskfactory.model.ht.QComment;

import java.util.List;

public interface QCommentMapper {
    public List<QComment> qureyByPramas(QCommentDto bean);
    public QComment getByPramas(QCommentDto bean);
    public Integer save(QCommentDto bean);
    public Integer delete(Integer id);
    public Integer updateById(QCommentDto bean);
    public Integer count(QCommentDto bean);
}