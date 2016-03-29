package com.mk.taskfactory.biz.impl.ots;

import com.mk.taskfactory.api.dtos.TCityListDto;
import com.mk.taskfactory.api.ots.TCityListService;
import com.mk.taskfactory.biz.mapper.ots.TCityListMapper;
import com.mk.taskfactory.model.TCityList;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class TCityListServiceImpl implements TCityListService {
    @Autowired
    private TCityListMapper mapper;

    public List<TCityListDto> qureyByPramas(TCityListDto bean){
        List<TCityList> list = mapper.qureyByPramas(bean);
        if (CollectionUtils.isEmpty(list)){
            return  null;
        }
        List<TCityListDto> resultList = new ArrayList<TCityListDto>();

        for (TCityList model : list) {
            resultList.add(buildDto(model));
        }
        return resultList;
    }
    public TCityListDto getByPramas(TCityListDto bean){
        return buildDto(mapper.getByPramas(bean));
    }
    public Integer save(TCityListDto bean){
        return mapper.save(bean);
    }
    public Integer delete(Integer id){
        return mapper.delete(id);
    }
    public Integer updateById(TCityListDto bean){
        return mapper.updateById(bean);
    }
    public Integer count(TCityListDto bean){
        return mapper.count(bean);
    }
    private TCityListDto buildDto(TCityList bean) {
        if (bean==null){
            return new TCityListDto();
        }
        TCityListDto resultDto=new TCityListDto();
        BeanUtils.copyProperties(bean, resultDto);
        resultDto.setCityCode(bean.getCode());
        return resultDto;
    }
}
