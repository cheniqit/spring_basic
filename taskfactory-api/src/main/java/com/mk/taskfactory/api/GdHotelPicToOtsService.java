package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.crawer.CrawlPodHotelImageDto;

import java.util.Map;

public interface GdHotelPicToOtsService {
    public Map<String,Object> hotelPhotos(Integer start);
    public Map<String,Object> roomPic(Integer start);
}

