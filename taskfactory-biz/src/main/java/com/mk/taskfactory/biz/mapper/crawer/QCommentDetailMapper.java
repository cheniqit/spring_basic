package com.mk.taskfactory.biz.mapper.crawer;

import com.mk.taskfactory.api.dtos.crawer.QCommentDetailDto;
import com.mk.taskfactory.model.crawer.QCommentDetail;
import java.util.List;

public interface QCommentDetailMapper {
    public List<QCommentDetail> qureyByPramas(QCommentDetailDto bean);
    public QCommentDetail getByPramas(QCommentDetailDto bean);
    public Integer save(QCommentDetailDto bean);
    public Integer delete(Integer id);
    public Integer updateById(QCommentDetailDto bean);
    public Integer count(QCommentDetailDto bean);
}