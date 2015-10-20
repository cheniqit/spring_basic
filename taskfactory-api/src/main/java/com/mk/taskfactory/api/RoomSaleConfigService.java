package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.TRoomSaleConfigDto;

import java.util.List;

/**
 * Created by admin on 2015/9/22.
 */
public interface RoomSaleConfigService {
    public List<TRoomSaleConfigDto> queryRoomSaleConfigByParams(TRoomSaleConfigDto bean);

    public Integer saveRoomSaleConfig(TRoomSaleConfigDto bean);

    public Integer deleteRoomSaleConfig(Integer id);

    public Integer updateRoomSaleConfig(TRoomSaleConfigDto bean);

    public Integer updateRoomSaleConfigValid(Integer id, String valid);

    public Integer updateRoomSaleConfigStarted(Integer id, String isStart);

    public List<TRoomSaleConfigDto> queryRoomSaleConfigByValid(String   valid);
}