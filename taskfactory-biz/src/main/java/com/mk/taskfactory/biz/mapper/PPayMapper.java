package com.mk.taskfactory.biz.mapper;


import com.mk.taskfactory.biz.order.model.OtaOrder;
import com.mk.taskfactory.biz.order.model.PPay;
import com.mk.taskfactory.biz.repository.MyBatisRepository;

import java.util.HashMap;
import java.util.List;

@MyBatisRepository
public interface PPayMapper {
       public   List<PPay>   getByOrderId(Long orderId);
}
