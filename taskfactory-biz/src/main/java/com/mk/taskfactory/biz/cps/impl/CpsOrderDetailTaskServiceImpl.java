package com.mk.taskfactory.biz.cps.impl;

import com.mk.taskfactory.api.cps.CpsOrderDetailTaskService;
import com.mk.taskfactory.biz.mapper.cps.CpsOrderMapper;
import com.mk.taskfactory.biz.cps.model.CpsChannel;
import com.mk.taskfactory.biz.order.impl.OtaOrderServiceImpl;
import com.mk.taskfactory.biz.mapper.OtaOrderMapper;
import com.mk.taskfactory.biz.order.model.OtaOrder;
import com.mk.taskfactory.biz.mapper.UMemberMapper;
import com.mk.taskfactory.biz.umember.model.UMember;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class CpsOrderDetailTaskServiceImpl implements CpsOrderDetailTaskService {
    private static Logger logger = LoggerFactory.getLogger(CpsOrderDetailTaskServiceImpl.class);

    @Autowired
    private  CpsOrderMapper cpsOrderMapper;

    @Autowired
    private  UMemberMapper umemberMapper;

    @Autowired
    private OtaOrderMapper otaOrderMapper;

    @Autowired
    private OtaOrderServiceImpl otaOrderServiceImpl;


    public  void    cpsOrderProduce(){
        logger.info(" begin cpsOrderProduce []");
        HashMap hm = new  HashMap();
        hm.put("valid",'F');
        List<CpsChannel>  cpsChannelList = cpsOrderMapper.selectChannelByParme(hm);
        if(CollectionUtils.isEmpty(cpsChannelList)){
            logger.info("query  cpsChannel : [cpsChannelList = null]  " );
            return ;
        }
        for(CpsChannel cpsChannle:cpsChannelList){
            HashMap<Long,UMember> memberMap = new  HashMap<Long,UMember>();
            String  channelCode = cpsChannle.getChannelCode();
            if(StringUtils.isEmpty(channelCode)){
                continue;
            }
            HashMap  hmap = new HashMap();
            hmap.put("comefrom", channelCode);
            List<UMember>  umemberList =   umemberMapper.selectCpsUserByComeFrom(hmap);
            if(CollectionUtils.isEmpty(cpsChannelList)){
                continue ;
            }
            String mids = "";
            for(UMember umember:umemberList){
                umember.setCpsName(cpsChannle.getChannelName());
                memberMap.put(umember.getMid(), umember);
                mids = mids  + "," + umember.getMid();
            }
            if(mids.length()>0&&mids.startsWith(",")){
                mids = mids.substring(1,mids.length());
            }
        }
    }

    public   List<OtaOrder>  queryOrderByMid(String  mids,Date maxTime){
        List<OtaOrder>  resultOrderList = new ArrayList<OtaOrder>();
        HashMap map = new HashMap();
        map.put("mids",mids);
        map.put("maxTime", maxTime);
        List<OtaOrder>  orderList =  otaOrderMapper.selectByMid(map);
        if(CollectionUtils.isEmpty(orderList)){
            return null ;
        }
        for(OtaOrder  otaOrder:orderList){
          if(otaOrderServiceImpl.isFirstOrder(otaOrder)){
              resultOrderList.add(otaOrder);
          }
        }
        return null;
    }

    public void saveOrderSummary(){
        //查找对应orderList数据

    }
}
