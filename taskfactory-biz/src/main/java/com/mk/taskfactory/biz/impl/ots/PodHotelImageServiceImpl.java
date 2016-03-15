package com.mk.taskfactory.biz.impl.ots;

import com.mk.taskfactory.api.dtos.PodHotelImageDto;
import com.mk.taskfactory.api.ots.PodHotelImageService;
import com.mk.taskfactory.biz.mapper.ots.PodHotelImageMapper;
import com.mk.taskfactory.model.PodHotelImage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class PodHotelImageServiceImpl implements PodHotelImageService {
    @Autowired
    private PodHotelImageMapper mapper;

    public List<PodHotelImageDto> qureyByPramas(PodHotelImageDto bean){
        List<PodHotelImage> list = mapper.qureyByPramas(bean);
        if (CollectionUtils.isEmpty(list)){
            return  null;
        }
        List<PodHotelImageDto> resultList = new ArrayList<PodHotelImageDto>();

        for (PodHotelImage model : list) {
            resultList.add(buildDto(model));
        }
        return resultList;
    }
    public PodHotelImageDto getByPramas(PodHotelImageDto bean){
        return buildDto(mapper.getByPramas(bean));
    }
    public Integer save(PodHotelImageDto bean){
        return mapper.save(bean);
    }
    public Integer delete(Integer id){
        return mapper.delete(id);
    }
    public Integer updateById(PodHotelImageDto bean){
        return mapper.updateById(bean);
    }
    public Integer count(PodHotelImageDto bean){
        return mapper.count(bean);
    }
    private PodHotelImageDto buildDto(PodHotelImage bean) {
        if (bean==null){
            return new PodHotelImageDto();
        }
        PodHotelImageDto resultDto=new PodHotelImageDto();
        BeanUtils.copyProperties(bean, resultDto);
        return resultDto;
    }
}
