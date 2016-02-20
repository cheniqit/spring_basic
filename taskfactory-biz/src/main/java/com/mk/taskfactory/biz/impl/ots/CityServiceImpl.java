package com.mk.taskfactory.biz.impl.ots;

import com.mk.taskfactory.api.ots.CityService;
import com.mk.taskfactory.api.dtos.TCityDto;
import com.mk.taskfactory.biz.mapper.ots.CityMapper;
import com.mk.taskfactory.model.TCity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

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
    public List<TCityDto> qureyByPramas(TCityDto bean){
        List<TCity> list = cityMapper.qureyByPramas(bean);
        if (CollectionUtils.isEmpty(list)){
            return  null;
        }
        List<TCityDto> resultList = new ArrayList<TCityDto>();

        for (TCity model : list) {
            resultList.add(buildDto(model));
        }
        return resultList;
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
