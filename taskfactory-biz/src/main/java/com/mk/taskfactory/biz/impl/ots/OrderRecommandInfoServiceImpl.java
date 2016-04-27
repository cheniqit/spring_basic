package com.mk.taskfactory.biz.impl.ots;

import com.mk.taskfactory.api.dtos.OrderRecommendInfoDto;
import com.mk.taskfactory.api.ots.OrderRecommendInfoService;
import com.mk.taskfactory.biz.mapper.ots.OrderRecommendInfoMapper;
import com.mk.taskfactory.model.OrderRecommendInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderRecommandInfoServiceImpl implements OrderRecommendInfoService {
    @Autowired
    private OrderRecommendInfoMapper mapper;


    @Override
    public List<OrderRecommendInfoDto> selectByNewOrderId(Long orderId) {


        List<OrderRecommendInfo> infoList = this.mapper.selectByNewOrderId(orderId);

        List<OrderRecommendInfoDto> infoDtoList =  new ArrayList<OrderRecommendInfoDto>();

        for(OrderRecommendInfo info : infoList) {
            OrderRecommendInfoDto dto = new OrderRecommendInfoDto();
            BeanUtils.copyProperties(info, dto);
            infoDtoList.add(dto);
        }

        return infoDtoList;
    }
}
