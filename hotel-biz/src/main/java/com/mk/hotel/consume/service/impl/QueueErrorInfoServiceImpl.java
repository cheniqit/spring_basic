package com.mk.hotel.consume.service.impl;

import com.mk.hotel.consume.enums.TopicEnum;
import com.mk.hotel.consume.mapper.QueueErrorInfoMapper;
import com.mk.hotel.consume.model.QueueErrorInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by chenqi on 16/10/11.
 */
@Service
public class QueueErrorInfoServiceImpl {
    @Autowired
    private QueueErrorInfoMapper queueErrorInfoMapper;

    public void saveQueueErrorInfo(String msg, TopicEnum topicEnum){
        QueueErrorInfo record  = new QueueErrorInfo();
        record.setCreateDate(new Date());
        record.setMsg(msg);
        record.setType(topicEnum.getCode().longValue());
        record.setTopic(topicEnum.getName());
        queueErrorInfoMapper.insert(record);
    }
}
