package com.mk.taskfactory.biz.impl.ht;

import com.mk.taskfactory.api.dtos.ht.QFacilityDto;
import com.mk.taskfactory.api.ht.QFacilityService;
import com.mk.taskfactory.biz.mapper.ht.QFacilityMapper;
import com.mk.taskfactory.model.ht.QFacility;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class QFacilityServiceImpl implements QFacilityService {
    @Autowired
    private QFacilityMapper mapper;

    public List<QFacilityDto> qureyByPramas(QFacilityDto bean){
        List<QFacility> list = mapper.qureyByPramas(bean);
        if (CollectionUtils.isEmpty(list)){
            return  null;
        }
        List<QFacilityDto> resultList = new ArrayList<QFacilityDto>();

        for (QFacility model : list) {
            resultList.add(buildDto(model));
        }
        return resultList;
    }
    public QFacilityDto getByPramas(QFacilityDto bean){
        return buildDto(mapper.getByPramas(bean));
    }
    public Integer save(QFacilityDto bean){
        return mapper.save(bean);
    }
    public Integer delete(Integer id){
        return mapper.delete(id);
    }
    public Integer updateById(QFacilityDto bean){
        return mapper.updateById(bean);
    }
    public Integer count(QFacilityDto bean){
        return mapper.count(bean);
    }
    private QFacilityDto buildDto(QFacility bean) {
        if (bean==null){
            return new QFacilityDto();
        }
        QFacilityDto resultDto=new QFacilityDto();
        BeanUtils.copyProperties(bean, resultDto);
        return resultDto;
    }
}
