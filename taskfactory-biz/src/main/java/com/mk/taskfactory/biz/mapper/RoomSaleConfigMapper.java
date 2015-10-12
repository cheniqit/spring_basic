package com.mk.taskfactory.biz.mapper;


import com.mk.taskfactory.biz.repository.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface RoomSaleConfigMapper {
    List<CountAgentSpread> queryCountAgentSpreadByParams(CountAgentSpreadDto bean);
    CountAgentSpread getCountAgentSpread(CountAgentSpreadDto bean);
    public int countCountAgentSpreadByParams(CountAgentSpreadDto bean);
    public Integer saveCountAgentSpread(CountAgentSpreadDto bean);
    public Integer deleteCountAgentSpread(Integer id);
    public Integer updateCountAgentSpread(CountAgentSpreadDto bean);

}
