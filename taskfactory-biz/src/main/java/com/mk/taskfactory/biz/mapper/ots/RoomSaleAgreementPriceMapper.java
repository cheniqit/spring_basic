package com.mk.taskfactory.biz.mapper.ots;

import com.mk.taskfactory.api.dtos.RoomSaleAgreementPriceDto;
import com.mk.taskfactory.model.RoomSaleAgreementPrice;

import java.util.List;

public interface RoomSaleAgreementPriceMapper {
    int countByPramas(RoomSaleAgreementPriceDto bean);

    List<RoomSaleAgreementPrice> qureyByPramas(RoomSaleAgreementPriceDto bean);

}