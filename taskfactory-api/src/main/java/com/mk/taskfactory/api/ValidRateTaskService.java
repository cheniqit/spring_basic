package com.mk.taskfactory.api;

import java.util.Date;

/**
 * Created by admin on 2015/9/22.
 */
public interface ValidRateTaskService {
    public void validRateTaskRun();

    public void updateOnline(Date runTime);
}
