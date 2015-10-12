package com.mk.taskfactory.api;

import java.util.List;

public interface CountAgentSpreadService {
    List<CountAgentSpreadDto> queryCountAgentSpread(CountAgentSpreadDto bean);
    CountAgentSpreadDto getCountAgentSpread(CountAgentSpreadDto bean);
    Integer saveCountAgentSpread(CountAgentSpreadDto bean);
    Integer updateCountAgentSpread(CountAgentSpreadDto bean);
    Integer deleteCountAgentSpread(Integer id);
}
