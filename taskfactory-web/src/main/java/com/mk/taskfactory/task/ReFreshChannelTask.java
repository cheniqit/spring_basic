package com.mk.taskfactory.task;


import com.mk.taskfactory.api.ValidRateTaskService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        validRateTaskService.updateOnline(new Date());
        //this.validRateTaskService.dateReback();
    }
}
