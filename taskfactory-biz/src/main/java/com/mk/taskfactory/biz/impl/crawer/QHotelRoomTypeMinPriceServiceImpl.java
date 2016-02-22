package com.mk.taskfactory.biz.impl.crawer;

import com.mk.taskfactory.api.crawer.QHotelRoomTypeMinPriceService;
import com.mk.taskfactory.api.crawer.QHotelRoomTypeService;
import com.mk.taskfactory.api.dtos.crawer.QHotelRoomtypeDto;
import com.mk.taskfactory.api.dtos.crawer.QHotelRoomtypeMinPriceDto;
import com.mk.taskfactory.biz.mapper.crawer.QHotelRoomtypeMapper;
import com.mk.taskfactory.biz.mapper.crawer.QHotelRoomtypeMinPriceMapper;
import com.mk.taskfactory.model.crawer.QHotelRoomtype;
import com.mk.taskfactory.model.crawer.QHotelRoomtypeMinPrice;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class QHotelRoomTypeMinPriceServiceImpl implements QHotelRoomTypeMinPriceService {
    @Autowired
    private QHotelRoomtypeMinPriceMapper mapper;

    public List<QHotelRoomtypeMinPriceDto> qureyByPramas(QHotelRoomtypeMinPriceDto bean){
        List<QHotelRoomtypeMinPrice> list = mapper.qureyByPramas(bean);
        if (CollectionUtils.isEmpty(list)){
            return  null;
        }
        List<QHotelRoomtypeMinPriceDto> resultList = new ArrayList<QHotelRoomtypeMinPriceDto>();

        for (QHotelRoomtypeMinPrice model : list) {
            resultList.add(buildDto(model));
        }
        return resultList;
    }
    public QHotelRoomtypeMinPriceDto getByPramas(QHotelRoomtypeMinPriceDto bean){
        return buildDto(mapper.getByPramas(bean));
    }
    public Integer save(QHotelRoomtypeMinPriceDto bean){
        return mapper.save(bean);
    }
    public Integer delete(Integer id){
        return mapper.delete(id);
    }
    public Integer updateById(QHotelRoomtypeMinPriceDto bean){
        return mapper.updateById(bean);
    }
    public Integer count(QHotelRoomtypeMinPriceDto bean){
        return mapper.count(bean);
    }

    private QHotelRoomtypeMinPriceDto buildDto(QHotelRoomtypeMinPrice bean) {
        if (bean==null){
            return new QHotelRoomtypeMinPriceDto();
        }
        QHotelRoomtypeMinPriceDto resultDto=new QHotelRoomtypeMinPriceDto();
        BeanUtils.copyProperties(bean, resultDto);
        return resultDto;
    }
}
