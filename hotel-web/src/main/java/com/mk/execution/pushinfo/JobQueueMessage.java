package com.mk.execution.pushinfo;

/**
 * Created by 振涛 on 2016/2/20.
 */
public class JobQueueMessage {
    /**
     * 消息
     */
    public PushInfo pushInfo;


    public JobQueueMessage(PushInfo pushInfo) {
        this.pushInfo = pushInfo;
    }
}
