package com.mk.taskfactory.biz.impl;


import com.mk.taskfactory.api.RoomSaleAgreementPriceService;
import com.mk.taskfactory.api.dtos.TRoomSaleAgreementPriceDto;
import com.mk.taskfactory.biz.mapper.ots.RoomSaleAgreementPriceMapper;
import com.mk.taskfactory.model.TRoomSaleAgreementPrice;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomSaleAgreementPriceServiceImpl implements RoomSaleAgreementPriceService {

    @Autowired
    private RoomSaleAgreementPriceMapper roomSaleAgreementPriceMapper;

    public int countByPramas(TRoomSaleAgreementPriceDto bean){
        return roomSaleAgreementPriceMapper.countByPramas(bean);
    }

    public List<TRoomSaleAgreementPriceDto> qureyByPramas(TRoomSaleAgreementPriceDto bean){
        List<TRoomSaleAgreementPrice> list=roomSaleAgreementPriceMapper.qureyByPramas(bean);
        if (CollectionUtils.isEmpty(list)){
            return  null;
        }
        List<TRoomSaleAgreementPriceDto> resultList = new ArrayList<TRoomSaleAgreementPriceDto>();

        for (TRoomSaleAgreementPrice model : list) {
            resultList.add(buildDto(model));
        }
        return resultList;
    }
    public TRoomSaleAgreementPriceDto getById(Integer id){
        return buildDto(roomSaleAgreementPriceMapper.getById(id));
    }
    private TRoomSaleAgreementPriceDto buildDto(TRoomSaleAgreementPrice bean) {
        if (bean==null){
            return new TRoomSaleAgreementPriceDto();
        }
        TRoomSaleAgreementPriceDto resultDto=new TRoomSaleAgreementPriceDto();
        BeanUtils.copyProperties(bean, resultDto);
        return resultDto;
    }

}
