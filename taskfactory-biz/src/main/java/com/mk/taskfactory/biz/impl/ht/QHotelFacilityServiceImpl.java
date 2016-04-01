package com.mk.taskfactory.biz.impl.ht;

import com.mk.taskfactory.api.dtos.ht.QHotelFacilityDto;
import com.mk.taskfactory.api.ht.QHotelFacilityService;
import com.mk.taskfactory.biz.mapper.ht.QHotelFacilityMapper;
import com.mk.taskfactory.model.ht.QHotelFacility;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class QHotelFacilityServiceImpl implements QHotelFacilityService {
    @Autowired
    private QHotelFacilityMapper mapper;

    public List<QHotelFacilityDto> qureyByPramas(QHotelFacilityDto bean){
        List<QHotelFacility> list = mapper.qureyByPramas(bean);
        if (CollectionUtils.isEmpty(list)){
            return  null;
        }
        List<QHotelFacilityDto> resultList = new ArrayList<QHotelFacilityDto>();

        for (QHotelFacility model : list) {
            resultList.add(buildDto(model));
        }
        return resultList;
    }
    public QHotelFacilityDto getByPramas(QHotelFacilityDto bean){
        return buildDto(mapper.getByPramas(bean));
    }
    public Integer save(QHotelFacilityDto bean){
        return mapper.save(bean);
    }
    public Integer delete(Integer id){
        return mapper.delete(id);
    }
    public Integer updateById(QHotelFacilityDto bean){
        return mapper.updateById(bean);
    }
    public Integer count(QHotelFacilityDto bean){
        return mapper.count(bean);
    }
    public List<QHotelFacilityDto> qureyJionFacility(QHotelFacilityDto bean){
        List<QHotelFacility> list = mapper.qureyJionFacility(bean);
        if (CollectionUtils.isEmpty(list)){
            return  null;
        }
        List<QHotelFacilityDto> resultList = new ArrayList<QHotelFacilityDto>();

        for (QHotelFacility model : list) {
            resultList.add(buildDto(model));
        }
        return resultList;
    }
    public Integer coutJionFacility(QHotelFacilityDto bean){
        return mapper.coutJionFacility(bean);
    }

    private QHotelFacilityDto buildDto(QHotelFacility bean) {
        if (bean==null){
            return new QHotelFacilityDto();
        }
        QHotelFacilityDto resultDto=new QHotelFacilityDto();
        BeanUtils.copyProperties(bean, resultDto);
        return resultDto;
    }
}
