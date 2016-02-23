package com.mk.taskfactory.biz.impl.ots;

import com.mk.taskfactory.api.dtos.OrderDto;
import com.mk.taskfactory.api.dtos.OrderToCsDto;
import com.mk.taskfactory.api.ots.OrderService;
import com.mk.taskfactory.api.ots.OrderToCsService;
import com.mk.taskfactory.biz.mapper.ots.OrderMapper;
import com.mk.taskfactory.biz.mapper.ots.OrderToCsMapper;
import com.mk.taskfactory.model.Order;
import com.mk.taskfactory.model.OrderToCs;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper mapper;

    public List<OrderDto> qureyByPramas(OrderDto bean){
        List<Order> list = mapper.qureyByPramas(bean);
        if (CollectionUtils.isEmpty(list)){
            return  null;
        }
        List<OrderDto> resultList = new ArrayList<OrderDto>();

        for (Order model : list) {
            resultList.add(buildDto(model));
        }
        return resultList;
    }
    public OrderDto getByPramas(OrderDto bean){
        return buildDto(mapper.getByPramas(bean));
    }
    public Integer save(OrderDto bean){
        return mapper.save(bean);
    }
    public Integer delete(Integer id){
        return mapper.delete(id);
    }
    public Integer updateById(OrderDto bean){
        return mapper.updateById(bean);
    }
    public Integer count(OrderDto bean){
        return mapper.count(bean);
    }
    private OrderDto buildDto(Order bean) {
        if (bean==null){
            return new OrderDto();
        }
        OrderDto resultDto=new OrderDto();
        BeanUtils.copyProperties(bean, resultDto);
        return resultDto;
    }
}
