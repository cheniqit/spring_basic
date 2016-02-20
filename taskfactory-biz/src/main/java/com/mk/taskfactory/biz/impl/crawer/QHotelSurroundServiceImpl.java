package com.mk.taskfactory.biz.impl.crawer;

import com.mk.taskfactory.api.crawer.QHotelService;
import com.mk.taskfactory.api.crawer.QHotelSurroundService;
import com.mk.taskfactory.api.dtos.crawer.QHotelDto;
import com.mk.taskfactory.api.dtos.crawer.QHotelSurroundDto;
import com.mk.taskfactory.biz.mapper.crawer.QHotelMapper;
import com.mk.taskfactory.biz.mapper.crawer.QHotelSurroundMapper;
import com.mk.taskfactory.model.crawer.QHotel;
import com.mk.taskfactory.model.crawer.QHotelSurround;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class QHotelSurroundServiceImpl implements QHotelSurroundService {
    @Autowired
    private QHotelSurroundMapper mapper;

    public List<QHotelSurroundDto> qureyByPramas(QHotelSurroundDto bean){
        List<QHotelSurround> list = mapper.qureyByPramas(bean);
        if (CollectionUtils.isEmpty(list)){
            return  null;
        }
        List<QHotelSurroundDto> resultList = new ArrayList<QHotelSurroundDto>();

        for (QHotelSurround model : list) {
            resultList.add(buildDto(model));
        }
        return resultList;
    }
    public QHotelSurroundDto getByPramas(QHotelSurroundDto bean){
        return buildDto(mapper.getByPramas(bean));
    }
    public Integer save(QHotelSurroundDto bean){
        return mapper.save(bean);
    }
    public Integer delete(Integer id){
        return mapper.delete(id);
    }
    public Integer updateById(QHotelSurroundDto bean){
        return mapper.updateById(bean);
    }
    public Integer count(QHotelSurroundDto bean){
        return mapper.count(bean);
    }
    private QHotelSurroundDto buildDto(QHotelSurround bean) {
        if (bean==null){
            return new QHotelSurroundDto();
        }
        QHotelSurroundDto resultDto=new QHotelSurroundDto();
        BeanUtils.copyProperties(bean, resultDto);
        return resultDto;
    }
}