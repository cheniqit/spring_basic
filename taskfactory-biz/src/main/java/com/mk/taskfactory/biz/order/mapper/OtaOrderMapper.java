package com.mk.taskfactory.biz.order.mapper;


import com.mk.taskfactory.biz.cps.model.CpsChannel;
import com.mk.taskfactory.biz.repository.MyBatisRepository;

import java.util.HashMap;
import java.util.List;

@MyBatisRepository
public interface OtaOrderMapper {

       public List<CpsChannel> selectChannelByParme(HashMap map);


}
