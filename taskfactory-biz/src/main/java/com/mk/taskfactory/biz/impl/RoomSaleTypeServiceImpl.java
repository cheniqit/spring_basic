package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.api.RoomSaleTypeService;
import com.mk.taskfactory.api.dtos.TRoomSaleTypeDto;
import com.mk.taskfactory.biz.mapper.RoomSaleTypeMapper;
import com.mk.taskfactory.model.TRoomSaleType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomSaleTypeServiceImpl implements RoomSaleTypeService {

    @Autowired
    private RoomSaleTypeMapper roomSaleTypeMapper;

    public List<TRoomSaleTypeDto> queryRoomSaleType (TRoomSaleTypeDto dto) {
        List<TRoomSaleType> typeList = this.roomSaleTypeMapper.queryRoomSaleType(dto);
        List<TRoomSaleTypeDto> resultList = new ArrayList<TRoomSaleTypeDto>();
        for (TRoomSaleType type :  typeList) {
            TRoomSaleTypeDto newDto = new TRoomSaleTypeDto();
            BeanUtils.copyProperties(type, newDto);
            resultList.add(newDto);
        }
        return resultList;
    }
    public int saveRoomSaleType(TRoomSaleTypeDto dto) {
        return this.roomSaleTypeMapper.saveRoomSaleType(dto);
    }
    public int deleteRoomSaleType(int id) {

        return this.roomSaleTypeMapper.deleteRoomSaleType(id);
    }
    public int updateRoomSaleType(TRoomSaleTypeDto dto) {
        return this.roomSaleTypeMapper.updateRoomSaleType(dto);
    }
}
;