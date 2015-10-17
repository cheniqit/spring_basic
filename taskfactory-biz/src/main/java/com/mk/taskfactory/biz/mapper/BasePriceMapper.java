package com.mk.taskfactory.biz.mapper;


import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.TBaseprice;

@MyBatisRepository
public interface BasePriceMapper {

    TBaseprice selectByPrimaryKey(Long id);

    TBaseprice findByRoomtypeId(Long roomTypeId);
}
