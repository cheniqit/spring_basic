package com.mk.taskfactory.biz.order.impl;

import com.mk.taskfactory.biz.order.mapper.OtaOrderMacMapper;
import com.mk.taskfactory.biz.order.mapper.OtaOrderMapper;
import com.mk.taskfactory.biz.order.model.OtaOrder;
import com.mk.taskfactory.biz.order.model.OtaOrderMac;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class OtaOrderServiceImpl  {

    @Autowired
    private OtaOrderMapper otaOrderMapper;
    @Autowired
    private OtaOrderMacMapper otaOrderMacMapper;

    public  Boolean  isFirstOrder(OtaOrder otaOrder){
            Long id =  otaOrder.getId();
            Long mid = otaOrder.getMid();
            //判断是否是当前会员的第一单
            if(id==getFirstOrderIdByMid(mid)){
             //判断是否是当前手机的第一单
              return  getFirstOrderMac(otaOrder);
            }else{
                return  false;
            }
    }

    public long getFirstOrderIdByMid(Long   mid){
        return  otaOrderMapper.getFirstOrderIdByMid(mid);
    }

    //判断当前订单的预定手机是否是首单
    public Boolean  getFirstOrderMac(OtaOrder otaOrder){
        if(null==otaOrder){
            return  true;
        }
        List<OtaOrderMac>  otaOrderMacList = otaOrderMacMapper.selectByOrderId(otaOrder.getId());
        if(CollectionUtils.isEmpty(otaOrderMacList)){
            return  true;
        }
        if(4==otaOrder.getOrderMethod()){//ios预定
           String uuid =  otaOrderMacList.get(0).getUuid();
            if(StringUtils.isEmpty(uuid)){
                return true;
            }
            List<OtaOrderMac>  oOMacIOSList =  otaOrderMacMapper.selectIOSByUuid(uuid);
            if(null!=oOMacIOSList&&oOMacIOSList.size()>1){
                return  false;
            }
        }else if  (5==otaOrder.getOrderMethod()){//android预定
            String  deviceimei =  otaOrderMacList.get(0).getDeviceimei();
            if(StringUtils.isEmpty(deviceimei)){
                return true;
            }
            List<OtaOrderMac>  oOMacAndroidList =  otaOrderMacMapper.selectIOSByUuid(deviceimei);
            if(null!=oOMacAndroidList&&oOMacAndroidList.size()>1){
                return  false;
            }
        }else{ //其它平台预定不参与pcs统计
            return false;
        }
        return   true;
    }
}
