package com.mk.taskfactory.biz.order.mapper;


import com.mk.taskfactory.biz.order.model.CpsOrderList;
import com.mk.taskfactory.biz.order.model.OtaOrderMac;
import com.mk.taskfactory.biz.repository.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface CpsOrderListMapper {
       int insert(CpsOrderList cpsOrderList);
}
