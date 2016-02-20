package com.mk.taskfactory.biz.mapper.ots;


import com.mk.taskfactory.api.dtos.EHotelPicDto;
import com.mk.taskfactory.api.dtos.TCityDto;
import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.EHotelPic;
import com.mk.taskfactory.model.TCity;

import java.util.List;

@MyBatisRepository
public interface CityMapper {
    public TCity getByCode(String code);
    public List<TCity> qureyByPramas(TCityDto bean);

}
