package com.mk.taskfactory.biz.impl.crawer;

import com.mk.taskfactory.api.crawer.QHotelRoomTypeService;
import com.mk.taskfactory.api.crawer.RoomTypePriceService;
import com.mk.taskfactory.api.dtos.crawer.QHotelRoomtypeDto;
import com.mk.taskfactory.api.dtos.crawer.RoomTypePriceDto;
import com.mk.taskfactory.biz.mapper.crawer.QHotelRoomtypeMapper;
import com.mk.taskfactory.biz.mapper.crawer.RoomTypePriceMapper;
import com.mk.taskfactory.model.crawer.QHotelRoomtype;
import com.mk.taskfactory.model.crawer.RoomTypePrice;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomTypePriceServiceImpl implements RoomTypePriceService {
    @Autowired
    private RoomTypePriceMapper mapper;

    public RoomTypePriceDto getLastMinOtaPrice(RoomTypePriceDto bean){
        if (StringUtils.isEmpty(bean.getHotelSourceId())||StringUtils.isEmpty(bean.getRoomTypeKey())){
            return null;
        }
        return buildDto(mapper.getLastMinOtaPrice(bean));
    }

    private RoomTypePriceDto buildDto(RoomTypePrice bean) {
        if (bean==null){
            return new RoomTypePriceDto();
        }
        RoomTypePriceDto resultDto=new RoomTypePriceDto();
        BeanUtils.copyProperties(bean, resultDto);
        return resultDto;
    }
}
