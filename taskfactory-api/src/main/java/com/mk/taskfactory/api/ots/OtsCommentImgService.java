package com.mk.taskfactory.api.ots;

import com.mk.taskfactory.api.dtos.crawer.TExCommentImgDto;

import java.util.List;

public interface OtsCommentImgService {
    public List<TExCommentImgDto> qureyByPramas(TExCommentImgDto bean);
    public TExCommentImgDto getByPramas(TExCommentImgDto bean);
    public Integer save(TExCommentImgDto bean);
    public Integer delete(Integer id);
    public Integer updateById(TExCommentImgDto bean);
    public Integer count(TExCommentImgDto bean);
}
