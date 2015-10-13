package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.api.*;
import com.mk.taskfactory.api.dtos.TRoomSaleDto;
import com.mk.taskfactory.biz.mapper.RoomSaleMapper;
import com.mk.taskfactory.model.TRoomSale;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.util.StringUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class RoomSaleServiceImpl implements RoomSaleService {

    @Autowired
    private RoomSaleMapper roomSaleMapper;

    @Override
    public List<TRoomSaleDto> queryYesterdayRoomSale() {
        List<TRoomSale> roomSaleList = this.roomSaleMapper.queryYesterdayRoomSale();

        List<TRoomSaleDto> roomSaleDtoList = new ArrayList<TRoomSaleDto>();

        for (TRoomSale roomSale : roomSaleList) {
            TRoomSaleDto dto = new TRoomSaleDto();

            //copy
            BeanUtils.copyProperties(roomSale, dto);
            roomSaleDtoList.add(dto);
        }

        return roomSaleDtoList;
    }

    @Override
    public List<TRoomSaleDto> queryUnBackRoomSale() {
        List<TRoomSale> roomSaleList = this.roomSaleMapper.queryUnBackRoomSale();

        List<TRoomSaleDto> roomSaleDtoList = new ArrayList<TRoomSaleDto>();

        for (TRoomSale roomSale : roomSaleList) {
            TRoomSaleDto dto = new TRoomSaleDto();

            //copy
            BeanUtils.copyProperties(roomSale, dto);
            roomSaleDtoList.add(dto);
        }

        return roomSaleDtoList;
    }

    @Override
    public void saveRoomSale(TRoomSaleDto roomSaleDto) {
        if (null != roomSaleDto) {
            this.roomSaleMapper.saveRoomSale(roomSaleDto);
        }
    }

    @Override
    public void updateRoomSaleBack(TRoomSaleDto roomSaleDto) {
        if (null != roomSaleDto && null != roomSaleDto.getId() && StringUtil.isNotEmpty(roomSaleDto.getIsBack())) {
            this.roomSaleMapper.updateRoomSaleBack(roomSaleDto);
        }
    }
}
