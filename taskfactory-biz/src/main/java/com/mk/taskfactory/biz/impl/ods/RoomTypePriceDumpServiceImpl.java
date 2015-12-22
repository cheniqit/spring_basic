package com.mk.taskfactory.biz.impl.ods;

import com.mk.taskfactory.api.dtos.ods.TRoomTypePriceDumpDto;
import com.mk.taskfactory.api.ods.RoomPriceContrastService;
import com.mk.taskfactory.api.ods.RoomTypePriceDumpService;
import com.mk.taskfactory.biz.mapper.ods.RoomTypePriceDumpMapper;
import com.mk.taskfactory.model.ods.TRoomTypePriceDump;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kangxiaolong on 2015/12/22.
 */
@Service
public class RoomTypePriceDumpServiceImpl implements RoomTypePriceDumpService {
    private static Logger logger = LoggerFactory.getLogger(RoomTypePriceDumpServiceImpl.class);

    @Autowired
    private RoomTypePriceDumpMapper roomTypePriceDumpMapper;
    public List<TRoomTypePriceDumpDto> queryByParams(TRoomTypePriceDumpDto bean){
        List<TRoomTypePriceDump> list = roomTypePriceDumpMapper.queryByParams(bean);
        if (CollectionUtils.isEmpty(list)){
            return  null;
        }
        List<TRoomTypePriceDumpDto> resultList = new ArrayList<TRoomTypePriceDumpDto>();

        for (TRoomTypePriceDump model : list) {
            resultList.add(buildDto(model));
        }
        return resultList;
    }
    public TRoomTypePriceDumpDto getById(BigInteger id){
        TRoomTypePriceDump result= roomTypePriceDumpMapper.getById(id);
        return buildDto(result);
    }
    public Integer save(TRoomTypePriceDumpDto bean){
        return roomTypePriceDumpMapper.save(bean);
    }
    public Integer deleteById(BigInteger id){
        return roomTypePriceDumpMapper.deleteById(id);
    }
    public Integer updateById(TRoomTypePriceDumpDto bean){
        return roomTypePriceDumpMapper.updateById(bean);
    }
    private TRoomTypePriceDumpDto buildDto(TRoomTypePriceDump bean) {
        if (bean==null){
            return new TRoomTypePriceDumpDto();
        }
        TRoomTypePriceDumpDto resultDto=new TRoomTypePriceDumpDto();
        BeanUtils.copyProperties(bean, resultDto);
        return resultDto;
    }
}
