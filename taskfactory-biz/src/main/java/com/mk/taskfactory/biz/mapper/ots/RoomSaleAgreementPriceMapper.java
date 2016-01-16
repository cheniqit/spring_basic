package com.mk.taskfactory.biz.mapper.ots;

import com.mk.taskfactory.api.dtos.TRoomSaleAgreementPriceDto;
import com.mk.taskfactory.model.TRoomSaleAgreementPrice;

import java.util.List;

public interface RoomSaleAgreementPriceMapper {
    int countByPramas(TRoomSaleAgreementPriceDto bean);
    List<TRoomSaleAgreementPrice> qureyByPramas(TRoomSaleAgreementPriceDto bean);
    TRoomSaleAgreementPrice getById(Integer id);

}