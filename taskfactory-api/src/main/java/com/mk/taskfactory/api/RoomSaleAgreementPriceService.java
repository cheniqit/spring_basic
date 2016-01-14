package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.RoomSaleAgreementPriceDto;
import com.mk.taskfactory.api.dtos.TRoomSaleConfigDto;

import java.util.List;

/**
 * Created by admin on 2015/9/22.
 */
public interface RoomSaleAgreementPriceService {
    public int countByPramas(RoomSaleAgreementPriceDto bean);

    public List<RoomSaleAgreementPriceDto> qureyByPramas(RoomSaleAgreementPriceDto bean);
}