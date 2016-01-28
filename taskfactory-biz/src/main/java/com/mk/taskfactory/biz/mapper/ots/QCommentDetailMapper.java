package com.mk.taskfactory.biz.mapper.ots;

import com.mk.taskfactory.api.dtos.QCommentDetailDto;
import com.mk.taskfactory.model.QCommentDetail;
import java.util.List;

public interface QCommentDetailMapper {
    public List<QCommentDetail> qureyByPramas(QCommentDetailDto bean);
    public QCommentDetail getByPramas(QCommentDetailDto bean);
    public Integer save(QCommentDetailDto bean);
    public Integer delete(Integer id);
    public Integer updateById(QCommentDetailDto bean);
    public Integer count(QCommentDetailDto bean);
}