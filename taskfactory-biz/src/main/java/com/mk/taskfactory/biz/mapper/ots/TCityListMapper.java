package com.mk.taskfactory.biz.mapper.ots;


import com.mk.taskfactory.api.dtos.TCityListDto;
import com.mk.taskfactory.api.dtos.crawer.QHotelDto;
import com.mk.taskfactory.model.TCityList;
import com.mk.taskfactory.model.crawer.QHotel;

import java.util.List;

public interface TCityListMapper {
    public List<TCityList> qureyByPramas(TCityListDto bean);
    public TCityList getByPramas(TCityListDto bean);
    public Integer save(TCityListDto bean);
    public Integer delete(Integer id);
    public Integer updateById(TCityListDto bean);
    public Integer count(TCityListDto bean);
}