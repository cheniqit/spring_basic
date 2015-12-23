package com.mk.taskfactory.biz.impl.ods;

import com.mk.taskfactory.api.BasePriceService;
import com.mk.taskfactory.api.dtos.ods.TRoomPriceContrastDto;
import com.mk.taskfactory.api.ods.RoomPriceContrastService;
import com.mk.taskfactory.biz.mapper.ods.RoomPriceContrastMapper;
import com.mk.taskfactory.model.ods.TRoomPriceContrast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by kangxiaolong on 2015/12/22.
 */
@Service
public class RoomPriceContrastServiceImpl  implements RoomPriceContrastService {
    private static Logger logger = LoggerFactory.getLogger(RoomPriceContrastServiceImpl.class);

    @Autowired
    private RoomPriceContrastMapper roomPriceContrastMapper;
    public List<TRoomPriceContrastDto> queryByParams(TRoomPriceContrastDto bean){
        List<TRoomPriceContrast> list = roomPriceContrastMapper.queryByParams(bean);
        if (CollectionUtils.isEmpty(list)){
            return  null;
        }
        List<TRoomPriceContrastDto> resultList = new ArrayList<TRoomPriceContrastDto>();

        for (TRoomPriceContrast roomPriceContrast : list) {
            resultList.add(buildDto(roomPriceContrast));
        }
        return resultList;
    }
    public List<TRoomPriceContrastDto> getRoomPriceContrast(TRoomPriceContrastDto bean){
        List<TRoomPriceContrast> list = roomPriceContrastMapper.getRoomPriceContrast(bean);
        if (CollectionUtils.isEmpty(list)){
            return  null;
        }
        List<TRoomPriceContrastDto> resultList = new ArrayList<TRoomPriceContrastDto>();

        for (TRoomPriceContrast roomPriceContrast : list) {
            resultList.add(buildDto(roomPriceContrast));
        }
        return resultList;
    }
    public TRoomPriceContrastDto getById(BigInteger id){
        TRoomPriceContrast result= roomPriceContrastMapper.getById(id);
        return buildDto(result);
    }
    public Integer save(TRoomPriceContrastDto bean){
        return roomPriceContrastMapper.save(bean);
    }
    public Integer deleteById(BigInteger id){
        return roomPriceContrastMapper.deleteById(id);
    }
    public Integer updateById(TRoomPriceContrastDto bean){
        return roomPriceContrastMapper.updateById(bean);
    }
    private TRoomPriceContrastDto buildDto(TRoomPriceContrast bean) {
        if (bean==null){
            return new TRoomPriceContrastDto();
        }
        TRoomPriceContrastDto resultDto=new TRoomPriceContrastDto();
        BeanUtils.copyProperties(bean, resultDto);
        return resultDto;
    }
}
