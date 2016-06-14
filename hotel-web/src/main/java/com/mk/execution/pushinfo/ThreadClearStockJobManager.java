package com.mk.execution.pushinfo;

import org.slf4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * Created by 振涛 on 2016/2/18.
 */
class ThreadClearStockJobManager implements Runnable {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ThreadClearStockJobManager.class);

    private final ExecutorClearStockManager executorManager;

    ThreadClearStockJobManager() {
        executorManager = new ExecutorClearStockManager();
    }

    @Override
    public void run() {
        try {

            executorManager.start();
            //休眠20小时
            try {
                TimeUnit.HOURS.sleep(20);
            } catch (InterruptedException e1) {

            }

        } catch (Exception e) {
            LOGGER.error("error: ", e);
        }
    }
}
