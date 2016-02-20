package com.mk.taskfactory.biz.impl.crawer;

import com.mk.taskfactory.api.crawer.QCommentDetailService;
import com.mk.taskfactory.api.crawer.QHotelService;
import com.mk.taskfactory.api.dtos.crawer.QCommentDetailDto;
import com.mk.taskfactory.api.dtos.crawer.QHotelDto;
import com.mk.taskfactory.biz.mapper.crawer.QCommentDetailMapper;
import com.mk.taskfactory.biz.mapper.crawer.QHotelMapper;
import com.mk.taskfactory.model.crawer.QCommentDetail;
import com.mk.taskfactory.model.crawer.QHotel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class QCommentDetailServiceImpl implements QCommentDetailService {
    @Autowired
    private QCommentDetailMapper mapper;

    public List<QCommentDetailDto> qureyByPramas(QCommentDetailDto bean){
        List<QCommentDetail> list = mapper.qureyByPramas(bean);
        if (CollectionUtils.isEmpty(list)){
            return  null;
        }
        List<QCommentDetailDto> resultList = new ArrayList<QCommentDetailDto>();

        for (QCommentDetail model : list) {
            resultList.add(buildDto(model));
        }
        return resultList;
    }
    public QCommentDetailDto getByPramas(QCommentDetailDto bean){
        return buildDto(mapper.getByPramas(bean));
    }
    public Integer save(QCommentDetailDto bean){
        return mapper.save(bean);
    }
    public Integer delete(Integer id){
        return mapper.delete(id);
    }
    public Integer updateById(QCommentDetailDto bean){
        return mapper.updateById(bean);
    }
    public Integer count(QCommentDetailDto bean){
        return mapper.count(bean);
    }
    private QCommentDetailDto buildDto(QCommentDetail bean) {
        if (bean==null){
            return new QCommentDetailDto();
        }
        QCommentDetailDto resultDto=new QCommentDetailDto();
        BeanUtils.copyProperties(bean, resultDto);
        return resultDto;
    }
}
