package com.mk.taskfactory.biz.mapper.ots;

import com.mk.taskfactory.api.dtos.OrderToCsDto;
import com.mk.taskfactory.api.dtos.crawer.TExHotelImageDto;
import com.mk.taskfactory.model.OrderToCs;
import com.mk.taskfactory.model.crawer.TExHotelImage;

import java.util.List;

public interface OrderToCsMapper {
    public List<OrderToCs> qureyByPramas(OrderToCsDto bean);
    public OrderToCs getByPramas(OrderToCsDto bean);
    public Integer save(OrderToCsDto bean);
    public Integer delete(Integer id);
    public Integer updateById(OrderToCsDto bean);
    public Integer count(OrderToCsDto bean);
    public List<OrderToCs> qureySendList(OrderToCsDto bean);

}