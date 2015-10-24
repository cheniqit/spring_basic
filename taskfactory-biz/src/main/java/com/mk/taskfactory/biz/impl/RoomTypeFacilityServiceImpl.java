package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.api.RoomTypeFacilityService;
import com.mk.taskfactory.api.dtos.TRoomTypeFacilityDto;
import com.mk.taskfactory.biz.mapper.RoomTypeFacilityMapper;
import com.mk.taskfactory.model.TRoomTypeFacility;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomTypeFacilityServiceImpl implements RoomTypeFacilityService {

    @Autowired
    private RoomTypeFacilityMapper roomTypeFacilityMapper;

    @Override
    public void deleteByRoomType(Integer roomTypeId) {
        if (roomTypeId==null){
            return;
        }
        this.roomTypeFacilityMapper.deleteByRoomType(roomTypeId);
    }
    public List<TRoomTypeFacilityDto> findByRoomTypeId(Integer roomTypeId){
        List<TRoomTypeFacility> list=roomTypeFacilityMapper.findByRoomTypeId(roomTypeId);
        if (list==null){
            return  null;
        }
        List<TRoomTypeFacilityDto> roomTypeFacilityDtos=new ArrayList<TRoomTypeFacilityDto>();
        for (TRoomTypeFacility roomTypeFacility :list) {
            roomTypeFacilityDtos.add(buildTRoomSettingDto(roomTypeFacility));
        }
        return  roomTypeFacilityDtos;
    }
    public Integer saveRoomSaleConfig(TRoomTypeFacilityDto bean){
        return  roomTypeFacilityMapper.saveRoomSaleConfig(bean);
    }
    private TRoomTypeFacilityDto buildTRoomSettingDto(TRoomTypeFacility bean) {
        if (bean==null){
            return new TRoomTypeFacilityDto();
        }
        TRoomTypeFacilityDto roomTypeFacilityDto=new TRoomTypeFacilityDto();
        BeanUtils.copyProperties(bean, roomTypeFacilityDto);
        return roomTypeFacilityDto;
    }
}
