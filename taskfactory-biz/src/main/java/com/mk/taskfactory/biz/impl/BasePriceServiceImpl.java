package com.mk.taskfactory.biz.impl;


import com.mk.taskfactory.api.BasePriceService;
import com.mk.taskfactory.api.RoomSaleConfigService;
import com.mk.taskfactory.api.dtos.TBasePriceDto;
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
    public TBasePriceDto selectByPrimaryKey(Long id) {
        return basePriceMapper.selectByPrimaryKey(id);
    }

    @Override
    public TBasePriceDto findByRoomtypeId(Long roomTypeId) {
        return basePriceMapper.findByRoomtypeId(roomTypeId);
    }

    @Override
    public int saveBasePriceService(TBasePriceDto dto) {
        return basePriceMapper.saveBasePriceDto(dto);
    }

    @Override
    public int updateBasePriceService(TBasePriceDto dto) {
        return basePriceMapper.updateBasePriceDto(dto);
    }
}
