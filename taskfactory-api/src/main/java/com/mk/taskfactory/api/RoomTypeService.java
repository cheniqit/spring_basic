package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.TRoomTypeDto;

import java.util.HashMap;
import java.util.List;

public interface RoomTypeService {
    public void updatePlusRoomNum(TRoomTypeDto roomTypeDto);
    public Integer saveTRoomType(TRoomTypeDto bean);
    public Integer delTRoomTypeById(Integer id);
    public Integer updateTRoomType(TRoomTypeDto bean);
    public TRoomTypeDto findTRoomTypeById(Integer id);
    public TRoomTypeDto findByName(TRoomTypeDto bean) throws Exception;
    public List<TRoomTypeDto> queryJionThotel(TRoomTypeDto bean);
    public Integer count();
    public List<TRoomTypeDto> qureyByPramas(TRoomTypeDto bean);
    public TRoomTypeDto getByPramas(TRoomTypeDto bean);
    public Integer count(TRoomTypeDto bean);
}
