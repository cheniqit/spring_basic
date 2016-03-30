package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.api.QHotelToRedisService;
import com.mk.taskfactory.api.dtos.TCityListDto;
import com.mk.taskfactory.api.dtos.TFacilityDto;
import com.mk.taskfactory.api.dtos.ods.OnlineHotelDto;
import com.mk.taskfactory.api.dtos.ods.OnlineHotelPriorityDto;
import com.mk.taskfactory.api.dtos.ods.OnlineHotelRoomTypeDto;
import com.mk.taskfactory.biz.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class JobStartServiceImpl {
    @Autowired
    private QHotelToRedisService qHotelToRedisService;

    public void tFacilityToRedis(){
        TFacilityDto dto = new TFacilityDto();
        qHotelToRedisService.tFacilityToRedis(dto);
    }

    public void onlineCityToRedis(){
        TCityListDto dto = new TCityListDto();
        qHotelToRedisService.onlineCityToRedis(dto);
    }
    public void hotelPriorityToRedis(){
        OnlineHotelPriorityDto dto = new OnlineHotelPriorityDto();
        Calendar   cal   =   Calendar.getInstance();
        cal.add(Calendar.DATE,   -1);
        String updateTime= DateUtils.format_yMd(cal.getTime());
        dto.setStrUpdateTime(updateTime);
        qHotelToRedisService.hotelPriorityToRedis(dto);
    }
    public void roomTypePicMappingToRedis(){
        OnlineHotelDto dto = new OnlineHotelDto();
        Calendar   cal   =   Calendar.getInstance();
        cal.add(Calendar.DATE,   -1);
        String updateTime= DateUtils.format_yMd(cal.getTime());
        dto.setStrUpdateTime(updateTime);
        qHotelToRedisService.roomTypePicMappingToRedis(dto);
    }
    public void hotelResourceToRedis(){
        OnlineHotelDto dto = new OnlineHotelDto();
        Calendar cal   =   Calendar.getInstance();
        cal.add(Calendar.DATE,   -1);
        String updateTime= DateUtils.format_yMd(cal.getTime());
        dto.setStrUpdateTime(updateTime);
        qHotelToRedisService.hotelResourceToRedis(dto);
    }

    public void onlineHotelToRedis(){
        OnlineHotelDto dto = new OnlineHotelDto();
        Calendar cal   =   Calendar.getInstance();
        cal.add(Calendar.DATE,   -1);
        String updateTime= DateUtils.format_yMd(cal.getTime());
        dto.setStrUpdateTime(updateTime);
        qHotelToRedisService.onlineHotelToRedis(dto);
    }

    public void onlineRoomTypeToRedis(){
        OnlineHotelRoomTypeDto dto = new OnlineHotelRoomTypeDto();
        Calendar   cal   =   Calendar.getInstance();
        cal.add(Calendar.DATE,   -1);
        String updateTime= DateUtils.format_yMd(cal.getTime());
        dto.setStrUpdateTime(updateTime);
        qHotelToRedisService.onlineRoomTypeToRedis(dto);
    }
    public void cityHotelSetToRedis(){
        TCityListDto dto = new TCityListDto();
        qHotelToRedisService.cityHotelSetToRedis(dto);
    }
    public void indexerjob(){
        qHotelToRedisService.indexerjob();
    }

}
