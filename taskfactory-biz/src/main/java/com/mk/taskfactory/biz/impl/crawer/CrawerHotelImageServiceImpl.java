package com.mk.taskfactory.biz.impl.crawer;

import com.mk.taskfactory.api.crawer.CrawerHotelImageService;
import com.mk.taskfactory.api.dtos.crawer.TExHotelImageDto;
import com.mk.taskfactory.api.dtos.ht.QHotelRoomtypeDto;
import com.mk.taskfactory.biz.mapper.crawer.CrawerHotelImageMapper;
import com.mk.taskfactory.model.crawer.TExHotelImage;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class CrawerHotelImageServiceImpl implements CrawerHotelImageService {
    @Autowired
    private CrawerHotelImageMapper mapper;

    public List<TExHotelImageDto> qureyByPramas(TExHotelImageDto bean){
        List<TExHotelImage> list = mapper.qureyByPramas(bean);
        if (CollectionUtils.isEmpty(list)){
            return  null;
        }
        List<TExHotelImageDto> resultList = new ArrayList<TExHotelImageDto>();

        for (TExHotelImage model : list) {
            resultList.add(buildDto(model));
        }
        return resultList;
    }
    public TExHotelImageDto getByPramas(TExHotelImageDto bean){
        return buildDto(mapper.getByPramas(bean));
    }
    public Integer save(TExHotelImageDto bean){
        return mapper.save(bean);
    }
    public Integer delete(Integer id){
        return mapper.delete(id);
    }
    public Integer updateById(TExHotelImageDto bean){
        return mapper.updateById(bean);
    }
    public Integer count(TExHotelImageDto bean){
        return mapper.count(bean);
    }
    private TExHotelImageDto buildDto(TExHotelImage bean) {
        if (bean==null){
            return new TExHotelImageDto();
        }
        TExHotelImageDto resultDto=new TExHotelImageDto();
        BeanUtils.copyProperties(bean, resultDto);
        return resultDto;
    }
}
