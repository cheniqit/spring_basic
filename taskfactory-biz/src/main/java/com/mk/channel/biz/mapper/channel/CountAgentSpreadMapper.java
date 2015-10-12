package com.mk.channel.biz.mapper.channel;


import com.mk.channel.biz.repository.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface CountAgentSpreadMapper {
    List<CountAgentSpread> queryCountAgentSpreadByParams(CountAgentSpreadDto bean);
    CountAgentSpread getCountAgentSpread(CountAgentSpreadDto bean);
    public int countCountAgentSpreadByParams(CountAgentSpreadDto bean);
    public Integer saveCountAgentSpread(CountAgentSpreadDto bean);
    public Integer deleteCountAgentSpread(Integer id);
    public Integer updateCountAgentSpread(CountAgentSpreadDto bean);

}
