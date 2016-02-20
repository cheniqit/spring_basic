package com.mk.taskfactory.biz.mapper.crawer;

import com.mk.taskfactory.api.dtos.crawer.ValidPrice;

import java.util.List;

public interface ValidPriceMapper {
    public List<ValidPrice> qureyValidPriceHotel(ValidPrice bean);
    public Integer countValidPriceHotel();
    public List<ValidPrice> qureyValidPriceRoomType(ValidPrice bean);
    public Integer countValidPriceRoomtype();
}