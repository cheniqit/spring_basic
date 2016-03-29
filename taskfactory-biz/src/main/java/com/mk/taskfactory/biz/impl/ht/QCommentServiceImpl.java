package com.mk.taskfactory.biz.impl.ht;

import com.mk.taskfactory.api.dtos.ht.QCommentDto;
import com.mk.taskfactory.api.ht.QCommentService;
import com.mk.taskfactory.biz.mapper.ht.QCommentMapper;
import com.mk.taskfactory.model.ht.QComment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class QCommentServiceImpl implements QCommentService {
    @Autowired
    private QCommentMapper mapper;

    public List<QCommentDto> qureyByPramas(QCommentDto bean){
        List<QComment> list = mapper.qureyByPramas(bean);
        if (CollectionUtils.isEmpty(list)){
            return  null;
        }
        List<QCommentDto> resultList = new ArrayList<QCommentDto>();

        for (QComment model : list) {
            resultList.add(buildDto(model));
        }
        return resultList;
    }
    public QCommentDto getByPramas(QCommentDto bean){
        return buildDto(mapper.getByPramas(bean));
    }
    public Integer save(QCommentDto bean){
        return mapper.save(bean);
    }
    public Integer delete(Integer id){
        return mapper.delete(id);
    }
    public Integer updateById(QCommentDto bean){
        return mapper.updateById(bean);
    }
    public Integer count(QCommentDto bean){
        return mapper.count(bean);
    }
    private QCommentDto buildDto(QComment bean) {
        if (bean==null){
            return new QCommentDto();
        }
        QCommentDto resultDto=new QCommentDto();
        BeanUtils.copyProperties(bean, resultDto);
        return resultDto;
    }
}
