package com.mk.taskfactory.biz.impl.ots;

import com.mk.taskfactory.api.dtos.TRoomTypeDynamicPriceDto;
import com.mk.taskfactory.api.dtos.TRoomTypePmsDynamicInfoDto;
import com.mk.taskfactory.api.ots.RoomTypeDynamicPriceService;
import com.mk.taskfactory.api.ots.RoomTypePmsDynamicInfoService;
import com.mk.taskfactory.biz.mapper.ots.RoomTypeDynamicPriceMapper;
import com.mk.taskfactory.biz.mapper.ots.RoomTypePmsDynamicInfoMapper;
import com.mk.taskfactory.model.TRoomTypeDynamicPrice;
import com.mk.taskfactory.model.TRoomTypePmsDynamicInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomTypeIPmsDynamicInfoServiceImpl implements RoomTypePmsDynamicInfoService {
    @Autowired
    private RoomTypePmsDynamicInfoMapper mapper;

    public List<TRoomTypePmsDynamicInfoDto> qureyByPramas(TRoomTypePmsDynamicInfoDto bean){
        List<TRoomTypePmsDynamicInfo> list = mapper.qureyByPramas(bean);
        if (CollectionUtils.isEmpty(list)){
            return  null;
        }
        List<TRoomTypePmsDynamicInfoDto> resultList = new ArrayList<TRoomTypePmsDynamicInfoDto>();

        for (TRoomTypePmsDynamicInfo model : list) {
            resultList.add(buildDto(model));
        }
        return resultList;
    }
    public TRoomTypePmsDynamicInfoDto getByPramas(TRoomTypePmsDynamicInfoDto bean){
        return buildDto(mapper.getByPramas(bean));
    }
    public Integer save(TRoomTypePmsDynamicInfoDto bean){
        return mapper.save(bean);
    }
    public Integer delete(Integer id){
        return mapper.delete(id);
    }
    public Integer updateById(Integer id){
        return mapper.updateById(id);
    }
    public Integer count(TRoomTypePmsDynamicInfoDto bean){
        return mapper.count(bean);
    }
    private TRoomTypePmsDynamicInfoDto buildDto(TRoomTypePmsDynamicInfo bean) {
        if (bean==null){
            return new TRoomTypePmsDynamicInfoDto();
        }
        TRoomTypePmsDynamicInfoDto resultDto=new TRoomTypePmsDynamicInfoDto();
        BeanUtils.copyProperties(bean, resultDto);
        return resultDto;
    }
}
