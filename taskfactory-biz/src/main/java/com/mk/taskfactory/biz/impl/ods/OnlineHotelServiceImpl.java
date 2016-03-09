package com.mk.taskfactory.biz.impl.ods;

import com.mk.taskfactory.api.dtos.ods.OnlineHotelDto;
import com.mk.taskfactory.api.ods.OnlineHotelService;
import com.mk.taskfactory.biz.mapper.ods.OnlineHotelMapper;
import com.mk.taskfactory.model.ods.OnlineHotel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class OnlineHotelServiceImpl implements OnlineHotelService {
    @Autowired
    private OnlineHotelMapper mapper;

    public List<OnlineHotelDto> qureyByPramas(OnlineHotelDto bean){
        List<OnlineHotel> list = mapper.qureyByPramas(bean);
        if (CollectionUtils.isEmpty(list)){
            return  null;
        }
        List<OnlineHotelDto> resultList = new ArrayList<OnlineHotelDto>();

        for (OnlineHotel model : list) {
            resultList.add(buildDto(model));
        }
        return resultList;
    }
    public OnlineHotelDto getByPramas(OnlineHotelDto bean){
        return buildDto(mapper.getByPramas(bean));
    }
    public Integer save(OnlineHotelDto bean){
        return mapper.save(bean);
    }
    public Integer delete(Integer id){
        return mapper.delete(id);
    }
    public Integer updateById(OnlineHotelDto bean){
        return mapper.updateById(bean);
    }
    public Integer count(OnlineHotelDto bean){
        return mapper.count(bean);
    }
    private OnlineHotelDto buildDto(OnlineHotel bean) {
        if (bean==null){
            return new OnlineHotelDto();
        }
        OnlineHotelDto resultDto=new OnlineHotelDto();
        BeanUtils.copyProperties(bean, resultDto);
        return resultDto;
    }
}
