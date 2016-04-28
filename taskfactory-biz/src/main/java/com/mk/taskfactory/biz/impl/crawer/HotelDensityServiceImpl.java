package com.mk.taskfactory.biz.impl.crawer;

import com.mk.taskfactory.api.dtos.crawer.HotelDensityDto;
import com.mk.taskfactory.api.crawer.HotelDensityService;
import com.mk.taskfactory.biz.mapper.crawer.HotelDensityMapper;
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
    public Integer updateByHotelId(HotelDensityDto bean){
        return mapper.updateByHotelId(bean);
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
        Integer result = -1;
        try {
            Integer hotelCount = this.count(bean);
            if (hotelCount != null && hotelCount > 0){
                result =  updateByHotelId(bean);
            }else {
                result = save(bean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Integer deleteByHotelId(String hotelId) {
        return mapper.deleteByHotelId(hotelId);
    }
}