package com.mk.taskfactory.biz.impl.ots;

import com.mk.taskfactory.api.dtos.OrderToCsLogDto;
import com.mk.taskfactory.api.ots.OrderToCsLogService;
import com.mk.taskfactory.biz.mapper.ots.OrderToCsLogMapper;
import com.mk.taskfactory.biz.mapper.ots.OrderToCsMapper;
import com.mk.taskfactory.model.OrderToCsLog;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderToCsLogServiceImpl implements OrderToCsLogService {
    @Autowired
    private OrderToCsLogMapper mapper;

    @Override
    public int save(OrderToCsLogDto logDto) {

        OrderToCsLog log = new OrderToCsLog();
        BeanUtils.copyProperties(logDto, log);
        return mapper.save(log);
    }
}
