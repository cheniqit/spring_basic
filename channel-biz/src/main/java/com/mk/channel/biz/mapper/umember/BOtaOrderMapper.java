package com.mk.channel.biz.mapper.umember;

import com.mk.channel.api.dtos.BOtaOrderDto;
import com.mk.channel.biz.repository.MyBatisRepository;

@MyBatisRepository
public interface BOtaOrderMapper {
    Integer getMemberIsOrder(BOtaOrderDto bean);
}
