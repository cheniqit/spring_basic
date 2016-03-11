package com.mk.taskfactory.biz.mapper.ots;

import com.mk.taskfactory.api.dtos.crawer.CrawlPodHotelImageDto;
import com.mk.taskfactory.model.PodHotelImage;

import java.util.List;

public interface PodHotelImageMapper {
    public List<PodHotelImage> qureyByPramas(CrawlPodHotelImageDto bean);
    public PodHotelImage getByPramas(CrawlPodHotelImageDto bean);
    public Integer save(CrawlPodHotelImageDto bean);
    public Integer delete(Integer id);
    public Integer updateById(CrawlPodHotelImageDto bean);
    public Integer count(CrawlPodHotelImageDto bean);
}