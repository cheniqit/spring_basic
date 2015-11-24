package com.mk.taskfactory.biz.mapper;


import com.mk.taskfactory.biz.order.model.OrderLog;
import com.mk.taskfactory.biz.order.model.PPay;
import com.mk.taskfactory.biz.repository.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface OrderLogMapper {
       public   List<OrderLog>   getByPayId(Long payId);
}
