package com.mk.channel.biz.impl;

import com.mk.channel.api.CountAgentSpreadService;
import com.mk.channel.api.ValidRateTaskService;
import com.mk.channel.api.BOtaOrderService;
import com.mk.channel.api.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class ValidRateTaskServiceImpl implements ValidRateTaskService {

    @Autowired
    private CountAgentSpreadService countAgentSpreadService;

    @Autowired
    private BOtaOrderService orderService;

    @Autowired
    private MemberService memberService;

    public void validRateTaskRun(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);    //得到前一个月
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateBegin= sdf.format(calendar.getTime());
        UMemberDto memberDto=new UMemberDto();
        memberDto.setCreatetime(dateBegin);
        List<UMemberDto> agentPerformances=memberService.getAgentPerformance(memberDto);//一月代理的业绩情况
         for (UMemberDto agentPerformance:agentPerformances){
             if(agentPerformance.getComefrom()==null){
                 continue;
             }
            memberDto=new UMemberDto();
            memberDto.setComefrom(agentPerformance.getComefrom());
            memberDto.setCreatetime(dateBegin);
            List<UMemberDto> freshMemberList=memberService.getUMemberByParams(memberDto);//一月代理推广用户
             int monthOrdersUserCount=0;
            for (UMemberDto freshMember:freshMemberList){//计算一个月代理推荐且下单的用户数量
                if (freshMember.getMid()==null){
                    continue;
                }
                BOtaOrderDto orderDto=new BOtaOrderDto();
                orderDto.setMid(freshMember.getMid());
                int orders=orderService.getMemberIsOrder(orderDto);
                if (orders>0){
                    monthOrdersUserCount++;
                }
            }
             CountAgentSpreadDto countAgentSpreadDto=new CountAgentSpreadDto();
             countAgentSpreadDto.setAgentNo(agentPerformance.getComefrom());
             CountAgentSpreadDto agentSpreadDto= countAgentSpreadService.getCountAgentSpread(countAgentSpreadDto);//得到当前代理推广情况
             if(agentSpreadDto!=null&&agentSpreadDto.getId()!=null){
                 agentSpreadDto.setMonthRegisterCount(agentPerformance.getMonthRegisterCount());
                 agentSpreadDto.setMonthOrdersUserCount(monthOrdersUserCount);
                 countAgentSpreadService.updateCountAgentSpread(agentSpreadDto);
             }
         }
    }
}
