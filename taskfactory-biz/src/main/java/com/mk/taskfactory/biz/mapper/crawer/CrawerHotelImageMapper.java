package com.mk.taskfactory.biz.mapper.crawer;

import com.mk.taskfactory.api.dtos.crawer.TExHotelImageDto;
import java.util.List;

import com.mk.taskfactory.model.crawer.TExHotelImage;
import org.apache.ibatis.annotations.Param;

public interface CrawerHotelImageMapper {
    public List<TExHotelImage> qureyByPramas(TExHotelImageDto bean);
    public TExHotelImage getByPramas(TExHotelImageDto bean);
    public Integer save(TExHotelImageDto bean);
    public Integer delete(Integer id);
    public Integer updateById(TExHotelImageDto bean);
    public Integer count(TExHotelImageDto bean);
}