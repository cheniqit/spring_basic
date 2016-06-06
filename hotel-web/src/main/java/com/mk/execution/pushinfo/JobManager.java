package com.mk.execution.pushinfo;

import com.mk.hotel.log.enums.LogPushTypeEnum;
import org.apache.commons.lang.StringUtils;

/**
 * Created by 振涛 on 2016/2/18.
 */
public class JobManager {

    public static void addPushInfoToRefreshJob(String body , LogPushTypeEnum type) {
        if (StringUtils.isBlank(body)) {
            throw new IllegalArgumentException("hotelId can not be blank.");
        }
        PushInfo pushInfo = new PushInfo(body, type);
        JobQueue.getInstance().push(new JobQueueMessage(pushInfo));
    }

}
