package com.mk.taskfactory.api.ots;

import com.mk.taskfactory.api.dtos.OrderToCsLogDto;

public interface OrderToCsLogService {

    public int save(OrderToCsLogDto logDto);
}
