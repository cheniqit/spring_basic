package com.mk.taskfactory.biz.impl.ots;

import com.mk.taskfactory.api.dtos.HotelHourClickDto;
import com.mk.taskfactory.api.dtos.TRoomTypeDynamicPriceDto;
import com.mk.taskfactory.api.ots.HotelHourClickService;
import com.mk.taskfactory.api.ots.RoomTypeDynamicPriceService;
import com.mk.taskfactory.biz.mapper.ots.HotelHourClickMapper;
import com.mk.taskfactory.biz.mapper.ots.RoomTypeDynamicPriceMapper;
import com.mk.taskfactory.model.HotelHourClick;
import com.mk.taskfactory.model.TRoomTypeDynamicPrice;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelHourClickServiceImpl implements HotelHourClickService {
    @Autowired
    private HotelHourClickMapper mapper;

    public List<HotelHourClickDto> qureyByPramas(HotelHourClickDto bean){
        List<HotelHourClick> list = mapper.qureyByPramas(bean);
        if (CollectionUtils.isEmpty(list)){
            return  null;
        }
        List<HotelHourClickDto> resultList = new ArrayList<HotelHourClickDto>();

        for (HotelHourClick model : list) {
            resultList.add(buildDto(model));
        }
        return resultList;
    }
    public HotelHourClickDto getByPramas(HotelHourClickDto bean){
        return buildDto(mapper.getByPramas(bean));
    }
    public Integer save(HotelHourClickDto bean){
        return mapper.save(bean);
    }
    public Integer delete(Integer id){
        return mapper.delete(id);
    }
    public Integer updateById(HotelHourClickDto bean){
        return mapper.updateById(bean);
    }
    public Integer count(HotelHourClickDto bean){
        return mapper.count(bean);
    }
    private HotelHourClickDto buildDto(HotelHourClick bean) {
        if (bean==null){
            return new HotelHourClickDto();
        }
        HotelHourClickDto resultDto=new HotelHourClickDto();
        BeanUtils.copyProperties(bean, resultDto);
        return resultDto;
    }
}
