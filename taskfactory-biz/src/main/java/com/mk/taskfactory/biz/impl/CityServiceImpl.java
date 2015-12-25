package com.mk.taskfactory.biz.impl;

import com.alibaba.fastjson.JSONObject;
import com.mk.taskfactory.api.CityService;
import com.mk.taskfactory.api.DoPriceDumpService;
import com.mk.taskfactory.api.dtos.TCityDto;
import com.mk.taskfactory.api.dtos.ods.TRoomTypePriceDumpDto;
import com.mk.taskfactory.biz.mapper.ots.CityMapper;
import com.mk.taskfactory.biz.mapper.ots.HotelMapper;
import com.mk.taskfactory.biz.utils.HttpUtils;
import com.mk.taskfactory.biz.utils.JsonUtils;
import com.mk.taskfactory.common.Constants;
import com.mk.taskfactory.model.TCity;
import com.mk.taskfactory.model.THotel;
import com.mk.taskfactory.model.ods.TRoomTypePriceDump;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by kangxiaolong on 2015/12/25.
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityMapper cityMapper;

    public TCityDto getByCode(String code){
        TCity result= cityMapper.getByCode(code);
        return buildDto(result);
    }

    private TCityDto buildDto(TCity bean) {
        if (bean==null){
            return new TCityDto();
        }
        TCityDto resultDto=new TCityDto();
        BeanUtils.copyProperties(bean, resultDto);
        return resultDto;
    }
}
