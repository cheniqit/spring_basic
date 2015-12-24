package com.mk.taskfactory.biz.mapper.ots;


import com.mk.taskfactory.api.dtos.SyServDictItemDto;
import com.mk.taskfactory.biz.order.model.OrderLog;
import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.SyServDictItem;

import java.util.List;

@MyBatisRepository
public interface SyServDictItemMapper {
     public List<SyServDictItem> queryByParams(SyServDictItemDto bean);
}
