package com.mk.taskfactory.biz.impl.ots;

import com.mk.taskfactory.api.crawer.CrawerCommentImgService;
import com.mk.taskfactory.api.dtos.OrderToCsDto;
import com.mk.taskfactory.api.dtos.crawer.TExCommentImgDto;
import com.mk.taskfactory.api.ots.OrderToCsService;
import com.mk.taskfactory.biz.mapper.crawer.CrawerCommentImgMapper;
import com.mk.taskfactory.biz.mapper.ots.OrderToCsMapper;
import com.mk.taskfactory.model.OrderToCs;
import com.mk.taskfactory.model.crawer.TExCommentImg;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderToCsServiceImpl implements OrderToCsService {
    @Autowired
    private OrderToCsMapper mapper;

    public List<OrderToCsDto> qureyByPramas(OrderToCsDto bean){
        List<OrderToCs> list = mapper.qureyByPramas(bean);
        if (CollectionUtils.isEmpty(list)){
            return  null;
        }
        List<OrderToCsDto> resultList = new ArrayList<OrderToCsDto>();

        for (OrderToCs model : list) {
            resultList.add(buildDto(model));
        }
        return resultList;
    }
    public List<OrderToCsDto> qureySendList(OrderToCsDto bean){
        List<OrderToCs> list = mapper.qureySendList(bean);
        if (CollectionUtils.isEmpty(list)){
            return  null;
        }
        List<OrderToCsDto> resultList = new ArrayList<OrderToCsDto>();

        for (OrderToCs model : list) {
            resultList.add(buildDto(model));
        }
        return resultList;
    }
    public OrderToCsDto getByPramas(OrderToCsDto bean){
        return buildDto(mapper.getByPramas(bean));
    }
    public Integer save(OrderToCsDto bean){
        return mapper.save(bean);
    }
    public Integer delete(Integer id){
        return mapper.delete(id);
    }
    public Integer updateById(OrderToCsDto bean){
        return mapper.updateById(bean);
    }
    public Integer count(OrderToCsDto bean){
        return mapper.count(bean);
    }
    private OrderToCsDto buildDto(OrderToCs bean) {
        if (bean==null){
            return new OrderToCsDto();
        }
        OrderToCsDto resultDto=new OrderToCsDto();
        BeanUtils.copyProperties(bean, resultDto);
        return resultDto;
    }
}
