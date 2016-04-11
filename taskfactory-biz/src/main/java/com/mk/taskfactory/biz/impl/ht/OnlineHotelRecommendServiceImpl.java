package com.mk.taskfactory.biz.impl.ht;

import com.mk.taskfactory.api.dtos.ht.OnlineHotelRecommendDto;
import com.mk.taskfactory.api.ht.OnlineHotelRecommendService;
import com.mk.taskfactory.biz.mapper.ht.OnlineHotelRecommendMapper;
import com.mk.taskfactory.model.ht.OnlineHotelRecommend;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class OnlineHotelRecommendServiceImpl implements OnlineHotelRecommendService {
    @Autowired
    private OnlineHotelRecommendMapper mapper;

    public List<OnlineHotelRecommendDto> qureyByPramas(OnlineHotelRecommendDto bean){
        List<OnlineHotelRecommend> list = mapper.qureyByPramas(bean);
        if (CollectionUtils.isEmpty(list)){
            return  null;
        }
        List<OnlineHotelRecommendDto> resultList = new ArrayList<OnlineHotelRecommendDto>();

        for (OnlineHotelRecommend model : list) {
            resultList.add(buildDto(model));
        }
        return resultList;
    }
    public OnlineHotelRecommendDto getByPramas(OnlineHotelRecommendDto bean){
        return buildDto(mapper.getByPramas(bean));
    }
    public Integer save(OnlineHotelRecommendDto bean){
        return mapper.save(bean);
    }
    public Integer delete(Integer id){
        return mapper.delete(id);
    }
    public Integer updateById(OnlineHotelRecommendDto bean){
        return mapper.updateById(bean);
    }
    public Integer count(OnlineHotelRecommendDto bean){
        return mapper.count(bean);
    }
    private OnlineHotelRecommendDto buildDto(OnlineHotelRecommend bean) {
        if (bean==null){
            return new OnlineHotelRecommendDto();
        }
        OnlineHotelRecommendDto resultDto=new OnlineHotelRecommendDto();
        BeanUtils.copyProperties(bean, resultDto);
        return resultDto;
    }
}
