package com.mk.taskfactory.biz.mapper;


import com.mk.taskfactory.biz.order.model.OtaOrder;
import com.mk.taskfactory.biz.order.model.OtaOrderMac;
import com.mk.taskfactory.biz.repository.MyBatisRepository;

import java.util.HashMap;
import java.util.List;

@MyBatisRepository
public interface OtaOrderMacMapper {

       public List<OtaOrderMac> selectByOrderId(Long orderId);

       public List<OtaOrderMac> selectAndroidByDeviceimei(String deviceimei);

       public List<OtaOrderMac> selectIOSByUuid(String uuid);

}
