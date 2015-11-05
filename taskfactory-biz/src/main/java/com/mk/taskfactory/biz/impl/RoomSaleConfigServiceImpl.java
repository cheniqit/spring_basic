package com.mk.taskfactory.biz.impl;


import com.mk.taskfactory.api.RoomSaleConfigService;
import com.mk.taskfactory.api.dtos.TRoomSaleConfigDto;
import com.mk.taskfactory.api.dtos.ValueTypeEnum;
import com.mk.taskfactory.biz.mapper.RoomSaleConfigMapper;
import com.mk.taskfactory.model.TRoomSaleConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class RoomSaleConfigServiceImpl implements RoomSaleConfigService {

    @Autowired
    private RoomSaleConfigMapper roomSaleConfigMapper;

    public List<TRoomSaleConfigDto> queryRoomSaleConfigByParams(TRoomSaleConfigDto bean){
        List<TRoomSaleConfig> list=roomSaleConfigMapper.queryRoomSaleConfigByParams(bean);
        if (list==null){
            return new ArrayList<TRoomSaleConfigDto>();
        }
        List<TRoomSaleConfigDto> roomDtos=new ArrayList<TRoomSaleConfigDto>();
        for (TRoomSaleConfig roomSaleConfig :list) {
            roomDtos.add(buildTRoomSaleConfigDto(roomSaleConfig));
        }
        return  roomDtos;
    }
    public Integer saveRoomSaleConfig(TRoomSaleConfigDto bean){
        return  this.roomSaleConfigMapper.saveRoomSaleConfig(bean);
    }
    public Integer deleteRoomSaleConfig(Integer id){
        return  null;
    }
    public Integer updateRoomSaleConfig(TRoomSaleConfigDto bean){
        return this.roomSaleConfigMapper.updateRoomSaleConfig(bean);
    }
    private TRoomSaleConfigDto buildTRoomSaleConfigDto(TRoomSaleConfig bean) {
        if (bean==null){
            return new TRoomSaleConfigDto();
        }
        TRoomSaleConfigDto roomSaleConfigDto=new TRoomSaleConfigDto();
        BeanUtils.copyProperties(bean, roomSaleConfigDto);
        roomSaleConfigDto.setSettleType(ValueTypeEnum.getById(bean.getSettleType()));
        return roomSaleConfigDto;
    }

    public  Integer  updateRoomSaleConfigValid(Integer id,String  valid){
        HashMap  hm = new HashMap();
        hm.put("id",id);
        hm.put("valid", valid);
        return   roomSaleConfigMapper.updateRoomSaleConfigValid(hm);
    }

    public Integer updateRoomSaleConfigStarted(Integer id, String isStart){
        HashMap  hm = new HashMap();
        hm.put("id",id);
        hm.put("isStart", isStart);
        return   roomSaleConfigMapper.updateRoomSaleConfigStarted(hm);
    }
    public TRoomSaleConfigDto queryRoomSaleConfigById(Integer id){
        TRoomSaleConfig roomSaleConfig=roomSaleConfigMapper.queryRoomSaleConfigById(id);
        return buildTRoomSaleConfigDto(roomSaleConfig);
    }

    public List<TRoomSaleConfigDto> queryRoomSaleConfigByValid(String   valid){
        if(StringUtils.isEmpty(valid)){
            return  null;
        }
        List<TRoomSaleConfig> list=roomSaleConfigMapper.queryRoomSaleConfigByValid(valid);
        if (list==null){
            return  null;
        }
        List<TRoomSaleConfigDto> roomDtos=new ArrayList<TRoomSaleConfigDto>();
        for (TRoomSaleConfig roomSaleConfig :list) {
            roomDtos.add(buildTRoomSaleConfigDto(roomSaleConfig));
        }
        return  roomDtos;
    }

    public Integer updatePriceCache(Long hotelId) {
        return this.roomSaleConfigMapper.updatePriceCache(hotelId);
    }
}
