package com.mk.taskfactory.biz.mapper.ots;

import com.mk.taskfactory.model.OrderRecommendInfo;

import java.util.List;

public interface OrderRecommendInfoMapper {

    List<OrderRecommendInfo> selectByNewOrderId(Long orderId);

}