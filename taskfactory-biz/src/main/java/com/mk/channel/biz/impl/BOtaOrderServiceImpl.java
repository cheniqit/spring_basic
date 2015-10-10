package com.mk.channel.biz.impl;

import com.mk.channel.api.dtos.BOtaOrderDto;
import com.mk.channel.api.BOtaOrderService;
import com.mk.channel.biz.mapper.umember.BOtaOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kxl on 2015/9/22.
 */
@Service
public class BOtaOrderServiceImpl implements BOtaOrderService {
    @Autowired
    private BOtaOrderMapper orderMapper;

    public int getMemberIsOrder(BOtaOrderDto bean){
        Integer orderCount=orderMapper.getMemberIsOrder(bean);
        if (orderCount==null){
            orderCount=0;
        }
        return orderCount;
    }
}
