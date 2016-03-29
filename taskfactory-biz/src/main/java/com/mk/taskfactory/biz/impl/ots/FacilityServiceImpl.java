package com.mk.taskfactory.biz.impl.ots;

import com.mk.taskfactory.api.dtos.TFacilityDto;
import com.mk.taskfactory.api.ots.FacilityService;
import com.mk.taskfactory.biz.mapper.ots.FacilityMapper;
import com.mk.taskfactory.model.TFacility;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacilityServiceImpl implements FacilityService {
    @Autowired
    private FacilityMapper mapper;

    public List<TFacilityDto> qureyByPramas(TFacilityDto bean){
        List<TFacility> list = mapper.qureyByPramas(bean);
        if (CollectionUtils.isEmpty(list)){
            return  null;
        }
        List<TFacilityDto> resultList = new ArrayList<TFacilityDto>();

        for (TFacility model : list) {
            resultList.add(buildDto(model));
        }
        return resultList;
    }
    public TFacilityDto getByPramas(TFacilityDto bean){
        return buildDto(mapper.getByPramas(bean));
    }
    public Integer save(TFacilityDto bean){
        return mapper.save(bean);
    }
    public Integer delete(Integer id){
        return mapper.delete(id);
    }
    public Integer updateById(TFacilityDto bean){
        return mapper.updateById(bean);
    }
    public Integer count(TFacilityDto bean){
        return mapper.count(bean);
    }
    private TFacilityDto buildDto(TFacility bean) {
        if (bean==null){
            return new TFacilityDto();
        }
        TFacilityDto resultDto=new TFacilityDto();
        BeanUtils.copyProperties(bean, resultDto);
        return resultDto;
    }
}
