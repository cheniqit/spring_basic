package com.mk.taskfactory.biz.mapper.crawer;

import com.mk.taskfactory.api.dtos.crawer.TExCommentImgDto;
import com.mk.taskfactory.api.dtos.crawer.TExHotelImageDto;
import java.util.List;

import com.mk.taskfactory.model.crawer.TExCommentImg;
import org.apache.ibatis.annotations.Param;

public interface CrawerCommentImgMapper {
    public List<TExCommentImg> qureyByPramas(TExCommentImgDto bean);
    public TExCommentImg getByPramas(TExCommentImgDto bean);
    public Integer save(TExCommentImgDto bean);
    public Integer delete(Integer id);
    public Integer updateById(TExCommentImgDto bean);
    public Integer count(TExCommentImgDto bean);
}