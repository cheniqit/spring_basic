package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.EHotelPicDto;
import com.mk.taskfactory.api.dtos.EHotelPicResDto;

import java.util.Map;

public interface HotelPicSyncService {
    public void hotelPicSync();
    public void roomTypeInfoPicSync();
    public Map<String,Object> deleteByParams(EHotelPicResDto bean);
    public Map<String,Object> truncate();
}
