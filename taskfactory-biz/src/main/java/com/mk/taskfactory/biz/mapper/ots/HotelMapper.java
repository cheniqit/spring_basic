package com.mk.taskfactory.biz.mapper.ots;


import com.mk.taskfactory.api.dtos.THotelDto;
import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.THotel;

import java.util.List;

@MyBatisRepository
public interface HotelMapper {
    public THotel getCityIdByHotelId(Integer id);
    public List<THotel> queryTHotel(THotelDto bean);
    public Integer countTHotel(THotelDto bean);
    public THotel getByPramas(THotelDto bean);

}
