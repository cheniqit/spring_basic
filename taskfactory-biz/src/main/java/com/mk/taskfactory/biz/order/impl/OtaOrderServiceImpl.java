package com.mk.taskfactory.biz.order.impl;

import com.mk.taskfactory.biz.mapper.OtaOrderMacMapper;
import com.mk.taskfactory.biz.mapper.OtaOrderMapper;
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
            //当前会员的第一条离店订单记录
            if(id==getFirstOrderIdByMid(mid)){
                //当前设备的第一条离店订单记录
              return  getFirstOrderMac(otaOrder);
            }else{
                return  false;
            }
    }

    public long getFirstOrderIdByMid(Long   mid){
        return  otaOrderMapper.getFirstOrderIdByMid(mid);
    }

    //查找当前设备的第一条离店记录
    public Boolean  getFirstOrderMac(OtaOrder otaOrder){
        if(null==otaOrder){
            return  true;
        }
        List<OtaOrderMac>  otaOrderMacList = otaOrderMacMapper.selectByOrderId(otaOrder.getId());
        if(CollectionUtils.isEmpty(otaOrderMacList)){
            return  true;
        }
        if(4==otaOrder.getOrderMethod()){//当前为ios设备
           String uuid =  otaOrderMacList.get(0).getUuid();
            if(StringUtils.isEmpty(uuid)){
                return true;
            }
            List<OtaOrderMac>  oOMacIOSList =  otaOrderMacMapper.selectIOSByUuid(uuid);
            if(null!=oOMacIOSList&&oOMacIOSList.size()>1){
                return  false;
            }
        }else if  (5==otaOrder.getOrderMethod()){//当前为android的订单
            String  deviceimei =  otaOrderMacList.get(0).getDeviceimei();
            if(StringUtils.isEmpty(deviceimei)){
                return true;
            }
            List<OtaOrderMac>  oOMacAndroidList =  otaOrderMacMapper.selectIOSByUuid(deviceimei);
            if(null!=oOMacAndroidList&&oOMacAndroidList.size()>1){
                return  false;
            }
        }else{
            //非app下的订单不录入cps
            return false;
        }
        return   true;
    }
}
