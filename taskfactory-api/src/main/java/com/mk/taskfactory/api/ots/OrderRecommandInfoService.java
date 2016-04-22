package com.mk.taskfactory.api.ots;

import com.mk.taskfactory.api.dtos.OrderRecommendInfoDto;

import java.util.List;

public interface OrderRecommandInfoService {

    public List<OrderRecommendInfoDto> selectByNewOrderId(Long orderId);
}
