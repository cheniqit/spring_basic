package com.mk.taskfactory.biz.impl;


import com.mk.taskfactory.api.BasePriceService;
import com.mk.taskfactory.api.dtos.TBasePriceDto;
import com.mk.taskfactory.biz.mapper.ots.BasePriceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasePriceServiceImpl implements BasePriceService {

    @Autowired
    private BasePriceMapper basePriceMapper;
    @Override
    public TBasePriceDto selectByPrimaryKey(Long id) {
        return basePriceMapper.selectByPrimaryKey(id);
    }

    @Override
    public TBasePriceDto findByRoomtypeId(Long roomTypeId) {
        return basePriceMapper.findByRoomtypeId(roomTypeId);
    }

    @Override
    public int saveBasePriceService(TBasePriceDto dto) {
        return basePriceMapper.saveBasePriceDto(dto);
    }

    @Override
    public int updateBasePriceService(TBasePriceDto dto) {
        return basePriceMapper.updateBasePriceDto(dto);
    }
    public int deleteBasePriceByRoomType(Integer roomTypeId){
       return basePriceMapper.deleteBasePriceByRoomType(roomTypeId);
    }
}
