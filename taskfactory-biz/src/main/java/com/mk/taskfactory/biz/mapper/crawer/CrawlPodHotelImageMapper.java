package com.mk.taskfactory.biz.mapper.crawer;

import com.mk.taskfactory.api.dtos.crawer.CrawlPodHotelImageDto;
import com.mk.taskfactory.model.crawer.CrawlPodHotelImage;

import java.util.List;

public interface CrawlPodHotelImageMapper {
    public List<CrawlPodHotelImage> qureyByPramas(CrawlPodHotelImageDto bean);
    public CrawlPodHotelImage getByPramas(CrawlPodHotelImageDto bean);
    public Integer save(CrawlPodHotelImageDto bean);
    public Integer delete(Integer id);
    public Integer updateById(CrawlPodHotelImageDto bean);
    public Integer count(CrawlPodHotelImageDto bean);
}