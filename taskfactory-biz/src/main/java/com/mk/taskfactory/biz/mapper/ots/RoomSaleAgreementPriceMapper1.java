package com.mk.taskfactory.biz.mapper.ots;

import com.mk.taskfactory.api.dtos.RoomSaleAgreementPriceDto;
import com.mk.taskfactory.model.RoomSaleAgreementPrice1;

import java.util.List;

public interface RoomSaleAgreementPriceMapper1 {
    int countByPramas(RoomSaleAgreementPriceDto bean);

    List<RoomSaleAgreementPrice1> qureyByPramas(RoomSaleAgreementPriceDto bean);

}