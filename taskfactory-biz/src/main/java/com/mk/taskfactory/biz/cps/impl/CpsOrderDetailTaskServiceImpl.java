package com.mk.taskfactory.biz.cps.impl;

import com.mk.taskfactory.api.cps.CpsOrderDetailTaskService;
import com.mk.taskfactory.biz.cps.model.CpsChannel;
import com.mk.taskfactory.biz.cps.model.CpsChannelExample;
import com.mk.taskfactory.biz.cps.model.CpsOrderList;
import com.mk.taskfactory.biz.cps.model.CpsOrderListExample;
import com.mk.taskfactory.biz.mapper.cps.CpsChannelMapper;
import com.mk.taskfactory.biz.mapper.cps.CpsOrderListMapper;
import com.mk.taskfactory.biz.order.impl.OtaOrderServiceImpl;
import com.mk.taskfactory.biz.mapper.OtaOrderMapper;
import com.mk.taskfactory.biz.order.model.OtaOrder;
import com.mk.taskfactory.biz.mapper.UMemberMapper;
import com.mk.taskfactory.biz.umember.model.UMember;
import com.mk.taskfactory.biz.utils.DateUtils;
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
    private CpsOrderListMapper cpsOrderMapper;

    @Autowired
    private CpsChannelMapper cpsChannelMapper;

    @Autowired
    private  UMemberMapper umemberMapper;

    @Autowired
    private OtaOrderMapper otaOrderMapper;

    @Autowired
    private  CpsOrderListMapper  cpsOrderListMapper;

    @Autowired
    private OtaOrderServiceImpl otaOrderServiceImpl;


    public  void    cpsOrderProduce(){
        logger.info(" begin cpsOrderProduce []");
        CpsChannelExample cpsChannelExample = new CpsChannelExample();
        cpsChannelExample.createCriteria().andValidEqualTo(1);
        List<CpsChannel>  cpsChannelList = cpsChannelMapper.selectByExample(cpsChannelExample);
        if(CollectionUtils.isEmpty(cpsChannelList)){
            logger.info("query  cpsChannel : [cpsChannelList = null]  " );
            return ;
        }
        for(CpsChannel cpsChannle:cpsChannelList){
            HashMap<Long,UMember> memberMap = new  HashMap<Long,UMember>();
            String  channelCode = cpsChannle.getChannelcode();
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
                umember.setCpsName(cpsChannle.getChannelname());
                memberMap.put(umember.getMid(), umember);
                mids = mids  + "," + umember.getMid();
            }
            if(mids.length()>0&&mids.startsWith(",")){
                mids = mids.substring(1,mids.length());
            }
            Date  maxTime =  cpsOrderListMapper.getMaxCheckOutTime();
            List<CpsOrderList>  cpsOrderListCollection =queryOrderByMid(mids,maxTime,channelCode,cpsChannle.getChannelname(),cpsChannle.getTypeid());
        }
    }

    public   List<CpsOrderList>  queryOrderByMid(String  mids,Date maxTime,String channelCode,String  channelName,Integer typeId){
        List<CpsOrderList>  resultOrderList = new ArrayList<CpsOrderList>();
        HashMap map = new HashMap();
        map.put("mids",mids);
        List<OtaOrder>  orderList = null;
        if(null == maxTime){
            map.put("maxTime",maxTime);
            orderList =  otaOrderMapper.selectByMidLessThenMaxTime(map);
        }else{
            map.put("maxTime", maxTime);
            orderList =  otaOrderMapper.selectByMidMoreThenMaxTime(map);
        }
        if(CollectionUtils.isEmpty(orderList)){
            return null ;
        }
        for(OtaOrder  otaOrder:orderList){
            CpsOrderList   cpsOrderListEntity = new  CpsOrderList();
            cpsOrderListEntity.setMid(otaOrder.getMid());
            cpsOrderListEntity.setChannelcode(channelCode);
            cpsOrderListEntity.setChannelname(channelName);
            cpsOrderListEntity.setChanneltype(typeId);
            cpsOrderListEntity.setUpdateTime(otaOrder.getUpdateTime());
            cpsOrderListEntity.setCreatetime(new Date());
            cpsOrderListEntity.setHotelid(otaOrder.getHotelId());
            cpsOrderListEntity.setIsnew("T");
            cpsOrderListEntity.setOrderid(otaOrder.getId());
            cpsOrderListEntity.setOrderprice(otaOrder.getTotalPrice());
          if(otaOrderServiceImpl.isFirstOrder(otaOrder)){
            cpsOrderListEntity.setIsfirst("T");
          }else{
              cpsOrderListEntity.setIsfirst("F");
          }
            resultOrderList.add(cpsOrderListEntity);
        }
        return  resultOrderList;
    }

    public  Boolean  saveCpsOrderList(List<CpsOrderList>  cpsOrderList){
        if(CollectionUtils.isEmpty(cpsOrderList)){
            return  false;
        }
        int  effectLine = cpsOrderListMapper.addCpsOrderListBatch(cpsOrderList);
        return  true;
    }


}
