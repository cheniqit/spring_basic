package com.mk.taskfactory.biz.impl.crawer;

import com.mk.taskfactory.api.crawer.CrawerCommentImgService;
import com.mk.taskfactory.api.dtos.crawer.TExCommentImgDto;
import com.mk.taskfactory.biz.mapper.crawer.CrawerCommentImgMapper;
import com.mk.taskfactory.model.crawer.TExCommentImg;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class CrawerCommentImgServiceImpl implements CrawerCommentImgService {
    @Autowired
    private CrawerCommentImgMapper mapper;

    public List<TExCommentImgDto> qureyByPramas(TExCommentImgDto bean){
        List<TExCommentImg> list = mapper.qureyByPramas(bean);
        if (CollectionUtils.isEmpty(list)){
            return  null;
        }
        List<TExCommentImgDto> resultList = new ArrayList<TExCommentImgDto>();

        for (TExCommentImg model : list) {
            resultList.add(buildDto(model));
        }
        return resultList;
    }
    public TExCommentImgDto getByPramas(TExCommentImgDto bean){
        return buildDto(mapper.getByPramas(bean));
    }
    public Integer save(TExCommentImgDto bean){
        return mapper.save(bean);
    }
    public Integer delete(Integer id){
        return mapper.delete(id);
    }
    public Integer updateById(TExCommentImgDto bean){
        return mapper.updateById(bean);
    }
    public Integer count(TExCommentImgDto bean){
        return mapper.count(bean);
    }
    private TExCommentImgDto buildDto(TExCommentImg bean) {
        if (bean==null){
            return new TExCommentImgDto();
        }
        TExCommentImgDto resultDto=new TExCommentImgDto();
        BeanUtils.copyProperties(bean, resultDto);
        return resultDto;
    }
}
