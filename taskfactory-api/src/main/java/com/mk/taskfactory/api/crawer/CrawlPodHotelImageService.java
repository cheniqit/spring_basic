package com.mk.taskfactory.api.crawer;

import com.mk.taskfactory.api.dtos.crawer.CrawlPodHotelImageDto;

import java.util.List;

public interface CrawlPodHotelImageService {
    public List<CrawlPodHotelImageDto> qureyByPramas(CrawlPodHotelImageDto bean);
    public CrawlPodHotelImageDto getByPramas(CrawlPodHotelImageDto bean);
    public Integer save(CrawlPodHotelImageDto bean);
    public Integer delete(Integer id);
    public Integer updateById(CrawlPodHotelImageDto bean);
    public Integer count(CrawlPodHotelImageDto bean);
}
