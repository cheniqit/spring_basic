package com.mk.taskfactory.biz.impl.ots;

import com.mk.taskfactory.api.dtos.HotelDayClickDto;
import com.mk.taskfactory.api.dtos.QHotelDto;
import com.mk.taskfactory.api.ots.HotelDayClickService;
import com.mk.taskfactory.api.ots.QHotelService;
import com.mk.taskfactory.biz.mapper.ots.HotelDayClickMapper;
import com.mk.taskfactory.biz.mapper.ots.QHotelMapper;
import com.mk.taskfactory.model.HotelDayClick;
import com.mk.taskfactory.model.QHotel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class QHotelServiceImpl implements QHotelService {
    @Autowired
    private QHotelMapper mapper;

    public List<QHotelDto> qureyByPramas(QHotelDto bean){
        List<QHotel> list = mapper.qureyByPramas(bean);
        if (CollectionUtils.isEmpty(list)){
            return  null;
        }
        List<QHotelDto> resultList = new ArrayList<QHotelDto>();

        for (QHotel model : list) {
            resultList.add(buildDto(model));
        }
        return resultList;
    }
    public QHotelDto getByPramas(QHotelDto bean){
        return buildDto(mapper.getByPramas(bean));
    }
    public Integer save(QHotelDto bean){
        return mapper.save(bean);
    }
    public Integer delete(Integer id){
        return mapper.delete(id);
    }
    public Integer updateById(QHotelDto bean){
        return mapper.updateById(bean);
    }
    public Integer count(QHotelDto bean){
        return mapper.count(bean);
    }
    private QHotelDto buildDto(QHotel bean) {
        if (bean==null){
            return new QHotelDto();
        }
        QHotelDto resultDto=new QHotelDto();
        BeanUtils.copyProperties(bean, resultDto);
        return resultDto;
    }
}
