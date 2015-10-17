package com.mk.taskfactory.biz.mapper;


import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.BPromoCardType;

@MyBatisRepository
public interface PromoCardTypeMapper {
    public BPromoCardType findBPromoCardTypeByType(Integer type);
}