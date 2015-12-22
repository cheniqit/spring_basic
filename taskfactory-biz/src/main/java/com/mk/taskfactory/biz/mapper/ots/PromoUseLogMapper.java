package com.mk.taskfactory.biz.mapper.ots;


import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.UPromoUseLog;

import java.util.List;

@MyBatisRepository
public interface PromoUseLogMapper {
    public List<UPromoUseLog> findUPromoUseLog(UPromoUseLog benn);
}
