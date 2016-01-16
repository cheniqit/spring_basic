package com.mk.taskfactory.biz.impl.ots;

import com.mk.taskfactory.api.dtos.SyServDictItemDto;
import com.mk.taskfactory.api.dtos.TRoomTypeDynamicPriceDto;
import com.mk.taskfactory.api.ots.RoomTypeDynamicPriceService;
import com.mk.taskfactory.api.ots.SyServDictItemService;
import com.mk.taskfactory.biz.mapper.ots.RoomTypeDynamicPriceMapper;
import com.mk.taskfactory.biz.mapper.ots.SyServDictItemMapper;
import com.mk.taskfactory.model.SyServDictItem;
import com.mk.taskfactory.model.TRoomTypeDynamicPrice;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomTypeDynamicPriceServiceImpl implements RoomTypeDynamicPriceService {
    @Autowired
    private RoomTypeDynamicPriceMapper mapper;

    public List<TRoomTypeDynamicPriceDto> qureyByPramas(TRoomTypeDynamicPriceDto bean){
        List<TRoomTypeDynamicPrice> list = mapper.qureyByPramas(bean);
        if (CollectionUtils.isEmpty(list)){
            return  null;
        }
        List<TRoomTypeDynamicPriceDto> resultList = new ArrayList<TRoomTypeDynamicPriceDto>();

        for (TRoomTypeDynamicPrice model : list) {
            resultList.add(buildDto(model));
        }
        return resultList;
    }
    public TRoomTypeDynamicPriceDto getByPramas(TRoomTypeDynamicPriceDto bean){
        return buildDto(mapper.getByPramas(bean));
    }
    public Integer save(TRoomTypeDynamicPriceDto bean){
        return mapper.save(bean);
    }
    public Integer delete(Integer id){
        return mapper.delete(id);
    }
    public Integer updateById(Integer id){
        return mapper.updateById(id);
    }
    public Integer count(TRoomTypeDynamicPriceDto bean){
        return mapper.count(bean);
    }
    private TRoomTypeDynamicPriceDto buildDto(TRoomTypeDynamicPrice bean) {
        if (bean==null){
            return new TRoomTypeDynamicPriceDto();
        }
        TRoomTypeDynamicPriceDto resultDto=new TRoomTypeDynamicPriceDto();
        BeanUtils.copyProperties(bean, resultDto);
        return resultDto;
    }
}
