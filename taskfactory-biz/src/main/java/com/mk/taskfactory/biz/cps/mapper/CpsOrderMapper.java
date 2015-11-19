package com.mk.taskfactory.biz.cps.mapper;


import com.mk.taskfactory.api.dtos.TRoomSaleConfigInfoDto;
import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.TRoomSaleConfigInfo;

import java.util.List;

@MyBatisRepository
public interface CpsOrderMapper {

    public List<TRoomSaleConfigInfo> queryRoomSaleConfigInfoList(TRoomSaleConfigInfoDto dto);

    public int saveRoomSaleConfigInfo(TRoomSaleConfigInfoDto dto);

    public TRoomSaleConfigInfo queryRoomSaleConfigById(Integer Id);

    public int updateTRoomSaleConfigInfo(TRoomSaleConfigInfoDto dto);

}
