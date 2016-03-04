package com.mk.taskfactory.biz.mapper.ods;

import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.ods.TRoomTypeOnlinePrice;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by fisher.wu on 16/3/3.
 */
@MyBatisRepository
public interface RoomTypeOnlinePriceMapper {

    public Integer insert(TRoomTypeOnlinePrice tRoomTypeOnlinePrice);

    public Integer deleteByCreateDate(String todayDateStr);
}
