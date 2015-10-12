package com.mk.channel.biz.mapper.umember;

import com.mk.channel.biz.repository.MyBatisRepository;

import java.util.List;
@MyBatisRepository
public interface UMemberMapper {
    List<UMember> getUMemberByParams(UMemberDto memberDto);
    List<UMember> getAgentPerformance(UMemberDto memberDto);
}
