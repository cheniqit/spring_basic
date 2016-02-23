package com.mk.taskfactory.api.ots;

import com.mk.taskfactory.api.dtos.OrderDto;
import com.mk.taskfactory.api.dtos.OrderToCsDto;

import java.util.List;

public interface OrderService {
    public List<OrderDto> qureyByPramas(OrderDto bean);
    public OrderDto getByPramas(OrderDto bean);
    public Integer save(OrderDto bean);
    public Integer delete(Integer id);
    public Integer updateById(OrderDto bean);
    public Integer count(OrderDto bean);
}
