package com.mk.channel.biz.impl;

import com.mk.channel.api.CountAgentSpreadService;
import com.mk.channel.api.dtos.CountAgentSpreadDto;
import com.mk.channel.biz.mapper.channel.CountAgentSpreadMapper;
import com.mk.channel.model.CountAgentSpread;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountAgentSpreadServiceImpl implements CountAgentSpreadService {

    @Autowired
    private CountAgentSpreadMapper countAgentSpreadMapper;

    public List<CountAgentSpreadDto> queryCountAgentSpread(CountAgentSpreadDto bean) {
        List<CountAgentSpread> countAgentSpreadList = countAgentSpreadMapper.queryCountAgentSpreadByParams(bean);
        if(countAgentSpreadList==null){
            return null;
        }
        List<CountAgentSpreadDto> countAgentSpreadDtos=new ArrayList<CountAgentSpreadDto>();
        for (CountAgentSpread countAgentSpread:countAgentSpreadList){
            countAgentSpreadDtos.add(buildCountAgentSpreadDto(countAgentSpread));
        }
        return  countAgentSpreadDtos;
    }

    public CountAgentSpreadDto getCountAgentSpread(CountAgentSpreadDto bean) {
        CountAgentSpread countAgentSpread = countAgentSpreadMapper.getCountAgentSpread(bean);
        if(countAgentSpread==null){
            return null;
        }
        return  buildCountAgentSpreadDto(countAgentSpread);
    }

    public Integer saveCountAgentSpread(CountAgentSpreadDto bean) {
        return countAgentSpreadMapper.saveCountAgentSpread(bean);
    }

    public Integer updateCountAgentSpread(CountAgentSpreadDto bean) {
        return countAgentSpreadMapper.updateCountAgentSpread(bean);
    }

    public Integer deleteCountAgentSpread(Integer id) {
        return countAgentSpreadMapper.deleteCountAgentSpread(id);
    }


    private CountAgentSpreadDto buildCountAgentSpreadDto(CountAgentSpread bean) {
        if (bean==null){
            return new CountAgentSpreadDto();
        }
        CountAgentSpreadDto countAgentSpreadDto=new CountAgentSpreadDto();
        BeanUtils.copyProperties(bean, countAgentSpreadDto);
        return countAgentSpreadDto;
    }
}
