package com.mk.taskfactory.biz.mapper;


import com.mk.taskfactory.biz.order.model.OtaOrder;
import com.mk.taskfactory.biz.repository.MyBatisRepository;

import java.util.HashMap;
import java.util.List;

@MyBatisRepository
public interface OtaOrderMapper {

       public List<OtaOrder>  selectByMidLessThenMaxTime(HashMap map);

       public List<OtaOrder>  selectByMidMoreThenMaxTime(HashMap map);

       public   long   getFirstOrderIdByMid(Long  mid);

}
