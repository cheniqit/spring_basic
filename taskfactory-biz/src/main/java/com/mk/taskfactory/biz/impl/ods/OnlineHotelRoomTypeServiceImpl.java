package com.mk.taskfactory.biz.impl.ods;

import com.mk.taskfactory.api.dtos.ods.OnlineHotelRoomTypeDto;
import com.mk.taskfactory.api.ods.OnlineHotelRoomTypeService;
import com.mk.taskfactory.biz.mapper.ods.OnlineHotelRoomTypeMapper;
import com.mk.taskfactory.model.ods.OnlineHotelRoomType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class OnlineHotelRoomTypeServiceImpl implements OnlineHotelRoomTypeService {
    @Autowired
    private OnlineHotelRoomTypeMapper mapper;

    public List<OnlineHotelRoomTypeDto> qureyByPramas(OnlineHotelRoomTypeDto bean){
        List<OnlineHotelRoomType> list = mapper.qureyByPramas(bean);
        if (CollectionUtils.isEmpty(list)){
            return  null;
        }
        List<OnlineHotelRoomTypeDto> resultList = new ArrayList<OnlineHotelRoomTypeDto>();

        for (OnlineHotelRoomType model : list) {
            resultList.add(buildDto(model));
        }
        return resultList;
    }
    public OnlineHotelRoomTypeDto getByPramas(OnlineHotelRoomTypeDto bean){
        return buildDto(mapper.getByPramas(bean));
    }
    public Integer save(OnlineHotelRoomTypeDto bean){
        return mapper.save(bean);
    }
    public Integer delete(Integer id){
        return mapper.delete(id);
    }
    public Integer updateById(OnlineHotelRoomTypeDto bean){
        return mapper.updateById(bean);
    }
    public Integer count(OnlineHotelRoomTypeDto bean){
        return mapper.count(bean);
    }
    private OnlineHotelRoomTypeDto buildDto(OnlineHotelRoomType bean) {
        if (bean==null){
            return new OnlineHotelRoomTypeDto();
        }
        OnlineHotelRoomTypeDto resultDto=new OnlineHotelRoomTypeDto();
        BeanUtils.copyProperties(bean, resultDto);
        return resultDto;
    }
}
