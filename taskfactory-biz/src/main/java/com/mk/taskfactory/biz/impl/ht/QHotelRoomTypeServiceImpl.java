package com.mk.taskfactory.biz.impl.ht;

import com.mk.taskfactory.api.dtos.ht.QHotelRoomtypeDto;
import com.mk.taskfactory.api.ht.QHotelRoomTypeService;
import com.mk.taskfactory.biz.mapper.ht.QHotelRoomtypeMapper;
import com.mk.taskfactory.model.ods.QHotelRoomtype;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class QHotelRoomTypeServiceImpl implements QHotelRoomTypeService {
    @Autowired
    private QHotelRoomtypeMapper mapper;

    public List<QHotelRoomtypeDto> qureyByPramas(QHotelRoomtypeDto bean){
        List<QHotelRoomtype> list = mapper.qureyByPramas(bean);
        if (CollectionUtils.isEmpty(list)){
            return  null;
        }
        List<QHotelRoomtypeDto> resultList = new ArrayList<QHotelRoomtypeDto>();

        for (QHotelRoomtype model : list) {
            resultList.add(buildDto(model));
        }
        return resultList;
    }
    public QHotelRoomtypeDto getByPramas(QHotelRoomtypeDto bean){
        return buildDto(mapper.getByPramas(bean));
    }
    public Integer save(QHotelRoomtypeDto bean){
        return mapper.save(bean);
    }
    public Integer delete(Integer id){
        return mapper.delete(id);
    }
    public Integer updateById(QHotelRoomtypeDto bean){
        return mapper.updateById(bean);
    }
    public Integer count(QHotelRoomtypeDto bean){
        return mapper.count(bean);
    }
    public QHotelRoomtypeDto getRoomtypeImg(QHotelRoomtypeDto bean){
        if (StringUtils.isEmpty(bean.getHotelSourceId())||StringUtils.isEmpty(bean.getRoomtypeKey())){
            return null;
        }
        return buildDto(mapper.getRoomtypeImg(bean));
    }
    private QHotelRoomtypeDto buildDto(QHotelRoomtype bean) {
        if (bean==null){
            return new QHotelRoomtypeDto();
        }
        QHotelRoomtypeDto resultDto=new QHotelRoomtypeDto();
        BeanUtils.copyProperties(bean, resultDto);
        return resultDto;
    }
}
