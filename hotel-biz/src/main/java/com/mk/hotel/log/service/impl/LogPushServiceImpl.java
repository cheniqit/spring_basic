package com.mk.hotel.log.service.impl;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import com.mk.hotel.log.LogPushService;
import com.mk.hotel.log.dto.LogPushDto;
import com.mk.hotel.log.mapper.LogPushMapper;
import com.mk.hotel.log.model.LogPush;
import com.mk.hotel.log.model.LogPushExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LogPushServiceImpl implements LogPushService {

    private static final Logger logger = LoggerFactory.getLogger(LogPushServiceImpl.class);
    @Autowired
    private LogPushMapper logPushMapper;

    public int save(LogPushDto logPushDto) {

        Transaction t = Cat.newTransaction("saveLogPush", "LogPushServiceImpl.save");
        try {
            if (null != logPushDto) {

                LogPush logPush = new LogPush();
                BeanUtils.copyProperties(logPushDto, logPush);

                logPush.setCreateDate(new Date());
                return this.logPushMapper.insert(logPush);
            }
        } finally {
            t.complete();
        }
        return -1;
    }

    public LogPushDto getById(Long id) {
        LogPushExample example = new LogPushExample();
        example.createCriteria().andIdEqualTo(id);
        List<LogPush> push = this.logPushMapper.selectByExampleWithBLOBs(example);

        if (push.isEmpty()) {
            return null;
        } else {

            LogPush logPush = push.get(0);
            LogPushDto logPushDto = new LogPushDto();
            BeanUtils.copyProperties(logPush, logPushDto);
            return logPushDto;
        }
    }

    public List<LogPushDto> getByTime(Date start, Date end, Long id) {
        LogPushExample example = new LogPushExample();
        LogPushExample.Criteria criteria = example.createCriteria();

        List<Long> types = new ArrayList<Long>();
        types.add(100l);
        types.add(200l);

        criteria.andTypeIn(types);
        if (null != id) {
            criteria.andIdEqualTo(id);
        }

        if (null != start && null != end) {
            criteria.andCreateDateBetween(start, end);
        }

        //from db
        List<LogPush> pushList = this.logPushMapper.selectByExampleWithBLOBs(example);

        //result list
        List<LogPushDto> resultList = new ArrayList<LogPushDto>();
        for (LogPush logPush : pushList) {
            LogPushDto logPushDto = new LogPushDto();
            BeanUtils.copyProperties(logPush, logPushDto);
            resultList.add(logPushDto);
        }

        return resultList;
    }

    @Override
    public Integer countByTime(Date start, Date end, Long id) {
        LogPushExample example = new LogPushExample();
        LogPushExample.Criteria criteria = example.createCriteria();

        if (null != id) {
            criteria.andIdEqualTo(id);
        }

        if (null != start && null != end) {
            criteria.andCreateDateBetween(start, end);
        }

        return this.logPushMapper.countByExample(example);
    }
}
