package com.mk.channel.api;

import com.mk.channel.api.dtos.UMemberDto;

import java.util.List;

/**
 * Created by kxl on 2015/9/22.
 */
public interface MemberService {
    List<UMemberDto> getUMemberByParams(UMemberDto memberDto);
    List<UMemberDto> getAgentPerformance(UMemberDto memberDto);
}
