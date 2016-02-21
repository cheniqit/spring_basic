package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.crawer.TExCommentImgDto;
import com.mk.taskfactory.api.dtos.crawer.TExHotelImageDto;

import java.util.Map;

public interface CrawerToOtsService {
    public Map<String,Object> commentImg(Integer start);
    public Map<String,Object> hotelImage(Integer start);
    public void saveCommentImg(Long id);
    public void saveHotelImage(Long id);

}

