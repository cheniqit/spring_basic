package com.mk.execution.pushinfo;

import com.dianping.cat.Cat;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by 振涛 on 2016/2/18.
 */
class ExecutorClearStockManager {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ExecutorClearStockManager.class);

    private final ThreadPoolExecutor CLEAR_POOL;

    private class QueueListener implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    ClearRefresh clearRefresh = new ClearRefresh();
                    CLEAR_POOL.execute(clearRefresh);
                } catch (RejectedExecutionException e) {
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e1) {
                        break;
                    }
                }catch (Exception e){
                    Cat.logError(e);
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e1) {
                        break;
                    }
                    e.printStackTrace();
                }
            }
        }
    }

    private List<Thread> queueListeners = new ArrayList<Thread>();

    ExecutorClearStockManager() {
        CLEAR_POOL = new ThreadPoolExecutor(
                Config.POOL_SIZE,
                Config.POOL_SIZE,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(Config.POOL_SIZE),
                new PushInfoRefreshFactory()
        );

        for (int i = 0; i < 5; i++) {
            Thread queueListener = new Thread(new QueueListener(), "clear-job-queue-listener");
            queueListeners.add(queueListener);
        }

    }

    void start() {

        for (Thread queueListener: queueListeners) {
            if (queueListener != null){
                queueListener.setName("clear-refresh-thread" + queueListener.getId());
                queueListener.setDaemon(true);
                queueListener.start();
            }

        }

        LOGGER.info("clear refresh execution start.");

    }

    void stop() {

        for (Thread queueListener: queueListeners) {
            if (queueListener != null){
                queueListener.interrupt();
            }

        }
        CLEAR_POOL.shutdownNow();

        LOGGER.info("clear refresh execution stop.");

        CLEAR_POOL.shutdownNow();

        LOGGER.info("clear refresh execution stop.");

    }

}