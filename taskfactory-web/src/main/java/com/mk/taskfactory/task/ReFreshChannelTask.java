package com.mk.taskfactory.task;


import com.dianping.cat.Cat;
import com.mk.taskfactory.api.ValidRateTaskService;
import com.mk.taskfactory.biz.utils.DateUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class ReFreshChannelTask {
    public static Logger log = org.slf4j.LoggerFactory.getLogger(ReFreshChannelTask.class);
    @Autowired
    private ValidRateTaskService validRateTaskService;

    public void run() {
        reFlashBlackListData();
    }

    public void reFlashBlackListData() {
        Calendar cal = Calendar.getInstance();
        cal.add(cal.HOUR, 1);
        Date startTime = cal.getTime();
        Cat.logEvent("validRateTaskService ", "�����" + DateUtils.dateToString(startTime, "yyyy-MM-dd HH:mm:ss"));
        validRateTaskService.updateOnline(startTime);
    }
}
