package com.mk.taskfactory.biz.impl.ods;

import com.mk.taskfactory.api.dtos.ods.OnlineHotelPriorityDto;
import com.mk.taskfactory.api.ods.OnlineHotelPriorityService;
import com.mk.taskfactory.biz.mapper.ods.OnlineHotelPriorityMapper;
import com.mk.taskfactory.model.ods.OnlineHotelPriority;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class OnlineHotelPriorityServiceImpl implements OnlineHotelPriorityService {
    @Autowired
    private OnlineHotelPriorityMapper mapper;

    public List<OnlineHotelPriorityDto> qureyByPramas(OnlineHotelPriorityDto bean){
        List<OnlineHotelPriority> list = mapper.qureyByPramas(bean);
        if (CollectionUtils.isEmpty(list)){
            return  null;
        }
        List<OnlineHotelPriorityDto> resultList = new ArrayList<OnlineHotelPriorityDto>();

        for (OnlineHotelPriority model : list) {
            resultList.add(buildDto(model));
        }
        return resultList;
    }
    public OnlineHotelPriorityDto getByPramas(OnlineHotelPriorityDto bean){
        return buildDto(mapper.getByPramas(bean));
    }
    public Integer save(OnlineHotelPriorityDto bean){
        return mapper.save(bean);
    }
    public Integer delete(Integer id){
        return mapper.delete(id);
    }
    public Integer updateById(OnlineHotelPriorityDto bean){
        return mapper.updateById(bean);
    }
    public Integer count(OnlineHotelPriorityDto bean){
        return mapper.count(bean);
    }
    private OnlineHotelPriorityDto buildDto(OnlineHotelPriority bean) {
        if (bean==null){
            return new OnlineHotelPriorityDto();
        }
        OnlineHotelPriorityDto resultDto=new OnlineHotelPriorityDto();
        BeanUtils.copyProperties(bean, resultDto);
        return resultDto;
    }
}
