package com.mk.taskfactory.biz.impl;


import com.mk.taskfactory.api.RoomSaleAgreementPriceService;
import com.mk.taskfactory.api.dtos.RoomSaleAgreementPriceDto;
import com.mk.taskfactory.biz.mapper.ots.RoomSaleAgreementPriceMapper1;
import com.mk.taskfactory.model.RoomSaleAgreementPrice1;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomSaleAgreementPriceServiceImpl implements RoomSaleAgreementPriceService {

    @Autowired
    private RoomSaleAgreementPriceMapper1 roomSaleAgreementPriceMapper;

    public int countByPramas(RoomSaleAgreementPriceDto bean){
        return roomSaleAgreementPriceMapper.countByPramas(bean);
    }

    public List<RoomSaleAgreementPriceDto> qureyByPramas(RoomSaleAgreementPriceDto bean){
        List<RoomSaleAgreementPrice1> list=roomSaleAgreementPriceMapper.qureyByPramas(bean);
        if (CollectionUtils.isEmpty(list)){
            return  null;
        }
        List<RoomSaleAgreementPriceDto> resultList = new ArrayList<RoomSaleAgreementPriceDto>();

        for (RoomSaleAgreementPrice1 model : list) {
            resultList.add(buildDto(model));
        }
        return resultList;
    }

    private RoomSaleAgreementPriceDto buildDto(RoomSaleAgreementPrice1 bean) {
        if (bean==null){
            return new RoomSaleAgreementPriceDto();
        }
        RoomSaleAgreementPriceDto resultDto=new RoomSaleAgreementPriceDto();
        BeanUtils.copyProperties(bean, resultDto);
        return resultDto;
    }

}
