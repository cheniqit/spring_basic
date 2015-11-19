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
            //�ж��Ƿ��ǵ�ǰ��Ա�ĵ�һ��
            if(id==getFirstOrderIdByMid(mid)){
             //�ж��Ƿ��ǵ�ǰ�ֻ��ĵ�һ��
              return  getFirstOrderMac(otaOrder);
            }else{
                return  false;
            }
    }

    public long getFirstOrderIdByMid(Long   mid){
        return  otaOrderMapper.getFirstOrderIdByMid(mid);
    }

    //�жϵ�ǰ������Ԥ���ֻ��Ƿ����׵�
    public Boolean  getFirstOrderMac(OtaOrder otaOrder){
        if(null==otaOrder){
            return  true;
        }
        List<OtaOrderMac>  otaOrderMacList = otaOrderMacMapper.selectByOrderId(otaOrder.getId());
        if(CollectionUtils.isEmpty(otaOrderMacList)){
            return  true;
        }
        if(4==otaOrder.getOrderMethod()){//iosԤ��
           String uuid =  otaOrderMacList.get(0).getUuid();
            if(StringUtils.isEmpty(uuid)){
                return true;
            }
            List<OtaOrderMac>  oOMacIOSList =  otaOrderMacMapper.selectIOSByUuid(uuid);
            if(null!=oOMacIOSList&&oOMacIOSList.size()>1){
                return  false;
            }
        }else if  (5==otaOrder.getOrderMethod()){//androidԤ��
            String  deviceimei =  otaOrderMacList.get(0).getDeviceimei();
            if(StringUtils.isEmpty(deviceimei)){
                return true;
            }
            List<OtaOrderMac>  oOMacAndroidList =  otaOrderMacMapper.selectIOSByUuid(deviceimei);
            if(null!=oOMacAndroidList&&oOMacAndroidList.size()>1){
                return  false;
            }
        }else{ //����ƽ̨Ԥ��������pcsͳ��
            return false;
        }
        return   true;
    }
}
