package com.mk.hotel.hotelinfo.exception.mapper;

import com.mk.hotel.hotelinfo.exception.model.LogPush;
import com.mk.hotel.hotelinfo.exception.model.LogPushExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogPushMapper {
    int countByExample(LogPushExample example);

    int deleteByExample(LogPushExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LogPush record);

    int insertSelective(LogPush record);

    List<LogPush> selectByExampleWithBLOBs(LogPushExample example);

    List<LogPush> selectByExample(LogPushExample example);

    LogPush selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LogPush record, @Param("example") LogPushExample example);

    int updateByExampleWithBLOBs(@Param("record") LogPush record, @Param("example") LogPushExample example);

    int updateByExample(@Param("record") LogPush record, @Param("example") LogPushExample example);

    int updateByPrimaryKeySelective(LogPush record);

    int updateByPrimaryKeyWithBLOBs(LogPush record);

    int updateByPrimaryKey(LogPush record);
}