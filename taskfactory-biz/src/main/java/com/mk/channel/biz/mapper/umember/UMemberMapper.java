package com.mk.channel.biz.mapper.umember;

import com.mk.channel.api.dtos.UMemberDto;
import com.mk.channel.biz.repository.MyBatisRepository;
import com.mk.channel.model.UMember;

import java.util.List;
@MyBatisRepository
public interface UMemberMapper {
    List<UMember> getUMemberByParams(UMemberDto memberDto);
    List<UMember> getAgentPerformance(UMemberDto memberDto);
}
