package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.api.OnSaleFallbackService;
import com.mk.taskfactory.api.RoomSaleService;
import com.mk.taskfactory.api.dtos.TRoomSaleDto;
import com.mk.taskfactory.biz.mapper.RoomSaleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OnSaleFallbackServiceImpl implements OnSaleFallbackService {

    @Autowired
    private RoomSaleService roomSaleService;

    public void onSaleFallback() {
        //需要回退的结果
        List<TRoomSaleDto> roomSaleDtoList = this.roomSaleService.queryYesterdayRoomSale();

        for (TRoomSaleDto roomSaleDto : roomSaleDtoList) {
            Integer roomTypeId = roomSaleDto.getRoomTypeId();
            Integer oldRoomTypeId = roomSaleDto.getOldRoomTypeId();
        }
    }
}
