package com.mk.taskfactory.biz.impl;


import com.mk.taskfactory.api.BasePriceService;
import com.mk.taskfactory.api.RoomSaleConfigService;
import com.mk.taskfactory.api.dtos.TBasepriceDto;
import com.mk.taskfactory.api.dtos.TRoomSaleConfigDto;
import com.mk.taskfactory.biz.mapper.BasePriceMapper;
import com.mk.taskfactory.biz.mapper.RoomSaleConfigMapper;
import com.mk.taskfactory.model.TBaseprice;
import com.mk.taskfactory.model.TRoomSaleConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasePriceServiceImpl implements BasePriceService {

    @Autowired
    private BasePriceMapper basePriceMapper;
    @Override
    public TBasepriceDto selectByPrimaryKey(Long id) {
        TBaseprice basePrice = basePriceMapper.selectByPrimaryKey(id);

        TBasepriceDto dto = new TBasepriceDto();
        BeanUtils.copyProperties(basePrice, dto);
        return dto;
    }

    @Override
    public TBasepriceDto findByRoomtypeId(Long roomTypeId) {
        TBaseprice basePrice = basePriceMapper.findByRoomtypeId(roomTypeId);

        TBasepriceDto dto = new TBasepriceDto();
        BeanUtils.copyProperties(basePrice, dto);
        return dto;
    }
}
