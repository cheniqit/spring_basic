package com.mk.channel.biz.impl;

import com.mk.channel.api.MemberService;
import com.mk.channel.biz.mapper.umember.UMemberMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2015/9/22.
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private UMemberMapper membertMapper;

    public  List<UMemberDto> getUMemberByParams(UMemberDto memberDto){
        List<UMember> memberList=membertMapper.getUMemberByParams(memberDto);
        if (memberList==null){
            return  null;
        }
        List<UMemberDto> memberDtos=new ArrayList<UMemberDto>();
        for (UMember member:memberList){
            memberDtos.add(buildUMemberDto(member));
        }
        return  memberDtos;
    }

    public  List<UMemberDto> getAgentPerformance(UMemberDto memberDto){
        List<UMember> memberList=membertMapper.getAgentPerformance(memberDto);
        if (memberList==null){
            return  null;
        }
        List<UMemberDto> memberDtos=new ArrayList<UMemberDto>();
        for (UMember member:memberList){
            memberDtos.add(buildUMemberDto(member));
        }
        return  memberDtos;
    }

    private UMemberDto buildUMemberDto(UMember bean) {
        if (bean==null){
            return new UMemberDto();
        }
        UMemberDto memberDto=new UMemberDto();
        BeanUtils.copyProperties(bean, memberDto);
        return memberDto;
    }
}
