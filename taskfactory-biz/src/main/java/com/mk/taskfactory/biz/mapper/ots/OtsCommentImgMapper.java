package com.mk.taskfactory.biz.mapper.ots;

import com.mk.taskfactory.api.dtos.crawer.TExCommentImgDto;
import com.mk.taskfactory.model.crawer.TExCommentImg;

import java.util.List;

public interface OtsCommentImgMapper {
    public List<TExCommentImg> qureyByPramas(TExCommentImgDto bean);
    public TExCommentImg getByPramas(TExCommentImgDto bean);
    public Integer save(TExCommentImgDto bean);
    public Integer delete(Integer id);
    public Integer updateById(TExCommentImgDto bean);
    public Integer count(TExCommentImgDto bean);
}