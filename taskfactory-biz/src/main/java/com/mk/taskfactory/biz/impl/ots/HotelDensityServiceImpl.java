package com.mk.taskfactory.biz.impl.ots;

import com.mk.taskfactory.api.dtos.HotelDensityDto;
import com.mk.taskfactory.api.ots.HotelDensityService;
import com.mk.taskfactory.biz.mapper.ots.HotelDensityMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelDensityServiceImpl implements HotelDensityService {
    @Autowired
    private HotelDensityMapper mapper;

    public List<HotelDensityDto> qureyByPramas(HotelDensityDto bean){
        List<HotelDensityDto> list = mapper.qureyByPramas(bean);
        if (CollectionUtils.isEmpty(list)){
            return  null;
        }
        List<HotelDensityDto> resultList = new ArrayList<HotelDensityDto>();

        for (HotelDensityDto model : list) {
            resultList.add(buildDto(model));
        }
        return resultList;
    }
    public HotelDensityDto getByPramas(HotelDensityDto bean){
        return buildDto(mapper.getByPramas(bean));
    }
    public Integer save(HotelDensityDto bean){
        return mapper.save(bean);
    }
    public Integer delete(Integer id){
        return mapper.delete(id);
    }
    public Integer updateById(HotelDensityDto bean){
        return mapper.updateById(bean);
    }
    public Integer count(HotelDensityDto bean){
        return mapper.count(bean);
    }
    private HotelDensityDto buildDto(HotelDensityDto bean) {
        if (bean==null){
            return new HotelDensityDto();
        }
        HotelDensityDto resultDto=new HotelDensityDto();
        BeanUtils.copyProperties(bean, resultDto);
        return resultDto;
    }

    @Override
    public Integer saveOrUpdate(HotelDensityDto bean) {
        return mapper.saveOrUpdate(bean);
    }

    @Override
    public Integer deleteByHotelId(String hotelId) {
        return mapper.deleteByHotelId(hotelId);
    }
}
