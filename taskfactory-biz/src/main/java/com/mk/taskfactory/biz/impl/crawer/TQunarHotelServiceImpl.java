package com.mk.taskfactory.biz.impl.crawer;

import com.mk.taskfactory.api.crawer.CrawerCommentImgService;
import com.mk.taskfactory.api.crawer.TQunarHotelService;
import com.mk.taskfactory.api.dtos.crawer.TExCommentImgDto;
import com.mk.taskfactory.api.dtos.crawer.TQunarHotelDto;
import com.mk.taskfactory.biz.mapper.crawer.CrawerCommentImgMapper;
import com.mk.taskfactory.biz.mapper.crawer.TQunarHotelMapper;
import com.mk.taskfactory.model.crawer.TExCommentImg;
import com.mk.taskfactory.model.crawer.TQunarHotel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class TQunarHotelServiceImpl implements TQunarHotelService {
    @Autowired
    private TQunarHotelMapper mapper;

    public List<TQunarHotelDto> qureyByPramas(TQunarHotelDto bean){
        List<TQunarHotel> list = mapper.qureyByPramas(bean);
        if (CollectionUtils.isEmpty(list)){
            return  null;
        }
        List<TQunarHotelDto> resultList = new ArrayList<TQunarHotelDto>();

        for (TQunarHotel model : list) {
            resultList.add(buildDto(model));
        }
        return resultList;
    }
    public TQunarHotelDto getByPramas(TQunarHotelDto bean){
        return buildDto(mapper.getByPramas(bean));
    }
    public Integer save(TQunarHotelDto bean){
        return mapper.save(bean);
    }
    public Integer delete(Integer id){
        return mapper.delete(id);
    }
    public Integer updateById(TQunarHotelDto bean){
        return mapper.updateById(bean);
    }
    public Integer count(TQunarHotelDto bean){
        return mapper.count(bean);
    }
    private TQunarHotelDto buildDto(TQunarHotel bean) {
        if (bean==null){
            return new TQunarHotelDto();
        }
        TQunarHotelDto resultDto=new TQunarHotelDto();
        BeanUtils.copyProperties(bean, resultDto);
        return resultDto;
    }
}
