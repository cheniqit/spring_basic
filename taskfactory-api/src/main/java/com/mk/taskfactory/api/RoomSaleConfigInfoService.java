package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.TRoomSaleConfigInfoDto;

import java.util.List;

public interface RoomSaleConfigInfoService {

    public List<TRoomSaleConfigInfoDto> queryRoomSaleConfigInfoList(TRoomSaleConfigInfoDto dto);

    public int saveRoomSaleConfigInfo(TRoomSaleConfigInfoDto dto);

    public int updateRoomSaleConfigInfo(TRoomSaleConfigInfoDto dto);
}
