package com.mk.taskfactory.api.ots;

import com.mk.taskfactory.api.dtos.OrderToCsDto;
import com.mk.taskfactory.api.dtos.crawer.TExCommentImgDto;

import java.util.List;

public interface OrderToCsService {
    public List<OrderToCsDto> qureyByPramas(OrderToCsDto bean);
    public OrderToCsDto getByPramas(OrderToCsDto bean);
    public Integer save(OrderToCsDto bean);
    public Integer delete(Integer id);
    public Integer updateById(OrderToCsDto bean);
    public Integer count(OrderToCsDto bean);
    public List<OrderToCsDto> qureySendList(OrderToCsDto bean);
    }
