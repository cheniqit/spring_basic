package com.mk.taskfactory.biz.mapper;


import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.UCardUseLog;

@MyBatisRepository
public interface CardUseLogMapper {
    public Integer insert(UCardUseLog bean);
}
