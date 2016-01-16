package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.TRoomSaleAgreementPriceDto;

import java.util.List;

/**
 * Created by admin on 2015/9/22.
 */
public interface RoomSaleAgreementPriceService {
    public int countByPramas(TRoomSaleAgreementPriceDto bean);
    public List<TRoomSaleAgreementPriceDto> qureyByPramas(TRoomSaleAgreementPriceDto bean);
    public TRoomSaleAgreementPriceDto getById(Integer id);
}