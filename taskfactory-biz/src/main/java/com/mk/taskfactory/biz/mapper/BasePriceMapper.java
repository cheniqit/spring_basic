package com.mk.taskfactory.biz.mapper;


import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.TBaseprice;

@MyBatisRepository
public interface BasePriceMapper {

    public TBaseprice selectByPrimaryKey(Long id);

    public TBaseprice findByRoomtypeId(Long roomTypeId);
}
