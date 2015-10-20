package com.mk.taskfactory.biz.impl;


import com.mk.taskfactory.api.RoomSaleConfigInfoService;
import com.mk.taskfactory.api.dtos.TRoomSaleConfigDto;
import com.mk.taskfactory.api.dtos.TRoomSaleConfigInfoDto;
import com.mk.taskfactory.biz.mapper.RoomSaleConfigInfoMapper;
import com.mk.taskfactory.model.TRoomSaleConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomSaleConfigInfoServiceImpl implements RoomSaleConfigInfoService {

    @Autowired
    private RoomSaleConfigInfoMapper roomSaleConfigInfoMapper;

    public TRoomSaleConfigInfoDto queryRoomSaleConfigById(Integer id){
        TRoomSaleConfig roomSaleConfig=roomSaleConfigInfoMapper.queryRoomSaleConfigById(id);
        return buildTRoomSaleConfigInfoDto(roomSaleConfig);
    }
    private TRoomSaleConfigInfoDto buildTRoomSaleConfigInfoDto(TRoomSaleConfig bean) {
        if (bean==null){
            return new TRoomSaleConfigInfoDto();
        }
        TRoomSaleConfigInfoDto roomSaleConfigInfoDto=new TRoomSaleConfigInfoDto();
        BeanUtils.copyProperties(bean, roomSaleConfigInfoDto);
        return roomSaleConfigInfoDto;
    }


}
