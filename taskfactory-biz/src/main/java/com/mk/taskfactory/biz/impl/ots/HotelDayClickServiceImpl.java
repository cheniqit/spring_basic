package com.mk.taskfactory.biz.impl.ots;

import com.mk.taskfactory.api.dtos.HotelDayClickDto;
import com.mk.taskfactory.api.ots.HotelDayClickService;
import com.mk.taskfactory.biz.mapper.ots.HotelDayClickMapper;
import com.mk.taskfactory.model.HotelDayClick;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelDayClickServiceImpl implements HotelDayClickService {
    @Autowired
    private HotelDayClickMapper mapper;

    public List<HotelDayClickDto> qureyByPramas(HotelDayClickDto bean){
        List<HotelDayClick> list = mapper.qureyByPramas(bean);
        if (CollectionUtils.isEmpty(list)){
            return  null;
        }
        List<HotelDayClickDto> resultList = new ArrayList<HotelDayClickDto>();

        for (HotelDayClick model : list) {
            resultList.add(buildDto(model));
        }
        return resultList;
    }
    public HotelDayClickDto getByPramas(HotelDayClickDto bean){
        return buildDto(mapper.getByPramas(bean));
    }
    public Integer save(HotelDayClickDto bean){
        return mapper.save(bean);
    }
    public Integer delete(Integer id){
        return mapper.delete(id);
    }
    public Integer updateById(HotelDayClickDto bean){
        return mapper.updateById(bean);
    }
    public Integer count(HotelDayClickDto bean){
        return mapper.count(bean);
    }
    private HotelDayClickDto buildDto(HotelDayClick bean) {
        if (bean==null){
            return new HotelDayClickDto();
        }
        HotelDayClickDto resultDto=new HotelDayClickDto();
        BeanUtils.copyProperties(bean, resultDto);
        return resultDto;
    }
}
