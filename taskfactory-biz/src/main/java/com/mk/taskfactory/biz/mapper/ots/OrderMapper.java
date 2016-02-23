package com.mk.taskfactory.biz.mapper.ots;

import com.mk.taskfactory.api.dtos.OrderDto;
import com.mk.taskfactory.api.dtos.OrderToCsDto;
import com.mk.taskfactory.model.Order;
import com.mk.taskfactory.model.OrderToCs;

import java.util.List;

public interface OrderMapper {
    public List<Order> qureyByPramas(OrderDto bean);
    public Order getByPramas(OrderDto bean);
    public Integer save(OrderDto bean);
    public Integer delete(Integer id);
    public Integer updateById(OrderDto bean);
    public Integer count(OrderDto bean);
}