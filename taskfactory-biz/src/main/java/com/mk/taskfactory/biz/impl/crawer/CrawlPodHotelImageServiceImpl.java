package com.mk.taskfactory.biz.impl.crawer;

import com.mk.taskfactory.api.crawer.CrawlPodHotelImageService;
import com.mk.taskfactory.api.dtos.crawer.CrawlPodHotelImageDto;
import com.mk.taskfactory.biz.mapper.crawer.CrawlPodHotelImageMapper;
import com.mk.taskfactory.model.crawer.CrawlPodHotelImage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class CrawlPodHotelImageServiceImpl implements CrawlPodHotelImageService {
    @Autowired
    private CrawlPodHotelImageMapper mapper;

    public List<CrawlPodHotelImageDto> qureyByPramas(CrawlPodHotelImageDto bean){
        List<CrawlPodHotelImage> list = mapper.qureyByPramas(bean);
        if (CollectionUtils.isEmpty(list)){
            return  null;
        }
        List<CrawlPodHotelImageDto> resultList = new ArrayList<CrawlPodHotelImageDto>();

        for (CrawlPodHotelImage model : list) {
            resultList.add(buildDto(model));
        }
        return resultList;
    }
    public CrawlPodHotelImageDto getByPramas(CrawlPodHotelImageDto bean){
        return buildDto(mapper.getByPramas(bean));
    }
    public Integer save(CrawlPodHotelImageDto bean){
        return mapper.save(bean);
    }
    public Integer delete(Integer id){
        return mapper.delete(id);
    }
    public Integer updateById(CrawlPodHotelImageDto bean){
        return mapper.updateById(bean);
    }
    public Integer count(CrawlPodHotelImageDto bean){
        return mapper.count(bean);
    }
    private CrawlPodHotelImageDto buildDto(CrawlPodHotelImage bean) {
        if (bean==null){
            return new CrawlPodHotelImageDto();
        }
        CrawlPodHotelImageDto resultDto=new CrawlPodHotelImageDto();
        BeanUtils.copyProperties(bean, resultDto);
        return resultDto;
    }
}
