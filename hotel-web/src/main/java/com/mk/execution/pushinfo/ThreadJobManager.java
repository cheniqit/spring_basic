package com.mk.execution.pushinfo;

import org.slf4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * Created by 振涛 on 2016/2/18.
 */
class ThreadJobManager implements Runnable {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ThreadJobManager.class);

    private final ExecutorManager executorManager;

    ThreadJobManager(){
        executorManager = new ExecutorManager();
    }


    @Override
    public void run() {
        try {

            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e1) {

            }
            executorManager.start();
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        }
    }
}
