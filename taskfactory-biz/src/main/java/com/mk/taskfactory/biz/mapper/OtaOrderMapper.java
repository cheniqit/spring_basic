package com.mk.taskfactory.biz.mapper;


import com.mk.taskfactory.biz.order.model.OtaOrder;
import com.mk.taskfactory.biz.repository.MyBatisRepository;

import java.util.HashMap;
import java.util.List;

@MyBatisRepository
public interface OtaOrderMapper {

       public List<OtaOrder> selectByMid(HashMap map);

       public   long   getFirstOrderIdByMid(Long  mid);

}
