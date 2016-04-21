package com.mk.taskfactory.biz.mapper.ots;


import com.mk.taskfactory.api.dtos.TCityListDto;
import com.mk.taskfactory.model.TCityList;

import java.util.List;

public interface TCityListMapper {
    public List<TCityList> qureyByPramas(TCityListDto bean);
    public TCityList getByPramas(TCityListDto bean);
    public Integer save(TCityListDto bean);
    public Integer delete(Integer id);
    public Integer updateById(TCityListDto bean);
    public Integer count(TCityListDto bean);
    List<TCityList> getDistrictListByCityCode(String cityCode);
}