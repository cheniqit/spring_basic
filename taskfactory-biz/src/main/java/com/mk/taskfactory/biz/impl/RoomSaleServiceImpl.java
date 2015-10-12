package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.api.RoomSaleService;
import com.mk.taskfactory.api.dtos.TRoomSaleConfigDto;
import com.mk.taskfactory.biz.mapper.RoomMapper;
import com.mk.taskfactory.biz.mapper.RoomSaleConfigMapper;
import com.mk.taskfactory.model.TRoomSaleConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2015/9/22.
 */
@Service
public class RoomSaleServiceImpl implements RoomSaleService {

    @Autowired
    private RoomSaleConfigMapper roomSaleConfigMapper;
    @Autowired
    private RoomMapper roomMapper;

    public  List<TRoomSaleConfigDto> queryRoomSaleConfigByParams(TRoomSaleConfigDto bean){
        List<TRoomSaleConfig> list=roomSaleConfigMapper.queryRoomSaleConfigByParams(bean);
        if (list==null){
            return  null;
        }
        List<TRoomSaleConfigDto> memberDtos=new ArrayList<TRoomSaleConfigDto>();
        for (TRoomSaleConfig roomSaleConfig:list){
            memberDtos.add(buildUMemberDto(roomSaleConfig));
        }
        return  memberDtos;
    }
    public Integer saveRoomSaleConfig(TRoomSaleConfigDto bean){
        return  null;
    }
    public Integer deleteRoomSaleConfig(Integer id){
        return  null;
    }
    public Integer updateRoomSaleConfig(TRoomSaleConfigDto bean){
        return null;
    }
    private TRoomSaleConfigDto buildUMemberDto(TRoomSaleConfig bean) {
        if (bean==null){
            return new TRoomSaleConfigDto();
        }
        TRoomSaleConfigDto roomSaleDto=new TRoomSaleConfigDto();
        BeanUtils.copyProperties(bean, roomSaleDto);
        return roomSaleDto;
    }
}
