package com.mk.hotel.consume.mapper;

import com.mk.hotel.consume.model.QueueErrorInfo;
import com.mk.hotel.consume.model.QueueErrorInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QueueErrorInfoMapper {
    int countByExample(QueueErrorInfoExample example);

    int deleteByExample(QueueErrorInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(QueueErrorInfo record);

    int insertSelective(QueueErrorInfo record);

    List<QueueErrorInfo> selectByExampleWithBLOBs(QueueErrorInfoExample example);

    List<QueueErrorInfo> selectByExample(QueueErrorInfoExample example);

    QueueErrorInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") QueueErrorInfo record, @Param("example") QueueErrorInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") QueueErrorInfo record, @Param("example") QueueErrorInfoExample example);

    int updateByExample(@Param("record") QueueErrorInfo record, @Param("example") QueueErrorInfoExample example);

    int updateByPrimaryKeySelective(QueueErrorInfo record);

    int updateByPrimaryKeyWithBLOBs(QueueErrorInfo record);

    int updateByPrimaryKey(QueueErrorInfo record);
}