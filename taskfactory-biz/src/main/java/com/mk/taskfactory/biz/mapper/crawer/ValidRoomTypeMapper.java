package com.mk.taskfactory.biz.mapper.crawer;

import com.mk.taskfactory.api.dtos.crawer.ValidRoomType;

import java.util.List;

public interface ValidRoomTypeMapper {
    public List<ValidRoomType> qureyValidPriceHotel(ValidRoomType bean);
    public Integer countValidPriceHotel(ValidRoomType bean);
    public List<ValidRoomType> qureyValidPriceRoomType(ValidRoomType bean);
    public Integer countValidPriceRoomtype();
}