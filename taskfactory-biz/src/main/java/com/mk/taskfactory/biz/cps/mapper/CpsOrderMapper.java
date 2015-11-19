package com.mk.taskfactory.biz.cps.mapper;


import com.mk.taskfactory.api.cps.dtos.CpsChannelDto;
import com.mk.taskfactory.api.dtos.TRoomSaleConfigInfoDto;
import com.mk.taskfactory.biz.cps.model.CpsChannel;
import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.TRoomSaleConfigInfo;

import java.util.HashMap;
import java.util.List;

@MyBatisRepository
public interface CpsOrderMapper {

       public List<CpsChannel> selectChannelByParme(HashMap map);


}
