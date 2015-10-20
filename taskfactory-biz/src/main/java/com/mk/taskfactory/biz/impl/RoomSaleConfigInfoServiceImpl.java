package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.api.RoomSaleConfigInfoService;
import com.mk.taskfactory.api.dtos.TRoomSaleConfigInfoDto;
import com.mk.taskfactory.biz.mapper.RoomSaleConfigInfoMapper;
import com.mk.taskfactory.model.TRoomSaleConfigInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomSaleConfigInfoServiceImpl implements RoomSaleConfigInfoService {

    @Autowired
    private RoomSaleConfigInfoMapper roomSaleConfigInfoMapper;

    public TRoomSaleConfigInfoDto queryRoomSaleConfigInfoById(Integer id){
        TRoomSaleConfigInfo roomSaleConfigInfo=roomSaleConfigInfoMapper.queryRoomSaleConfigById(id);
        return buildTRoomSaleConfigInfoDto(roomSaleConfigInfo);
    }
    private TRoomSaleConfigInfoDto buildTRoomSaleConfigInfoDto(TRoomSaleConfigInfo bean) {
        if (bean==null){
            return new TRoomSaleConfigInfoDto();
        }
        TRoomSaleConfigInfoDto roomSaleConfigInfoDto=new TRoomSaleConfigInfoDto();
        BeanUtils.copyProperties(bean, roomSaleConfigInfoDto);
        return roomSaleConfigInfoDto;
    }

    public List<TRoomSaleConfigInfoDto> queryRoomSaleConfigInfoList(TRoomSaleConfigInfoDto dto) {
        List<TRoomSaleConfigInfo> configInfoList = this.roomSaleConfigInfoMapper.queryRoomSaleConfigInfoList(dto);
        List<TRoomSaleConfigInfoDto> resultList = new ArrayList<TRoomSaleConfigInfoDto>();
        for (TRoomSaleConfigInfo info :  configInfoList) {
            TRoomSaleConfigInfoDto newDto = new TRoomSaleConfigInfoDto();
            BeanUtils.copyProperties(info, newDto);
            resultList.add(newDto);
        }
        return resultList;
    }

    public int saveRoomSaleConfigInfo(TRoomSaleConfigInfoDto dto) {
        return this.roomSaleConfigInfoMapper.saveRoomSaleConfigInfo(dto);
    }

    public int updateRoomSaleConfigInfo(TRoomSaleConfigInfoDto dto) {
        return this.updateRoomSaleConfigInfo(dto);
    }
}
;
