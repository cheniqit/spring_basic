package com.mk.taskfactory.biz.mapper;


import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.THotel;

@MyBatisRepository
public interface HotelMapper {
    public THotel getCityIdByHotelId(Integer id);
}
