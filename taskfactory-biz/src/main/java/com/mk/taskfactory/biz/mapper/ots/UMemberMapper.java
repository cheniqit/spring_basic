package com.mk.taskfactory.biz.mapper.ots;

import com.mk.taskfactory.api.dtos.TRoomTypeDto;
import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.biz.umember.model.UMember;
import com.mk.taskfactory.model.TRoomType;

import java.util.HashMap;
import java.util.List;

@MyBatisRepository
public interface UMemberMapper {
    public List<UMember> selectCpsUserByComeFrom(HashMap hm);
    public UMember selectByMid(UMember bean);

}
