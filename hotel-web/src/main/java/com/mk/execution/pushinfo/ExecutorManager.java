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
class ExecutorManager {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ExecutorManager.class);

    private final ThreadPoolExecutor PUSH_INFO_REFRESH_POOL;

    private class QueueListener implements Runnable {
        @Override
        public void run() {
            while (true) {

                LOGGER.info("+++++++++++++ refresh hotel is running... ++++++++++++++");

                try {
                    JobQueueMessage jobQueueMessage = JobQueue.getInstance().brpop();
                    if (jobQueueMessage != null){
                        PushInfoRefresh pushInfoRefresh = new PushInfoRefresh(jobQueueMessage);
                        PUSH_INFO_REFRESH_POOL.execute(pushInfoRefresh);
                        LOGGER.info("+++++++++++++ add {} to refresh indexer... ++++++++++++++", jobQueueMessage.pushInfo.body);
                    }else {
                        LOGGER.info("no push info need to refresh~");
                        try {
                            TimeUnit.SECONDS.sleep(5);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                } catch (RejectedExecutionException e) {
                    Cat.logError(e);
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

    ExecutorManager() {
        PUSH_INFO_REFRESH_POOL = new ThreadPoolExecutor(
                Config.POOL_SIZE,
                Config.POOL_SIZE,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(Config.POOL_SIZE),
                new PushInfoRefreshFactory()
        );

        for (int i = 0; i < 5; i++) {
            Thread queueListener = new Thread(new QueueListener(), "push-info-job-queue-listener");
            queueListeners.add(queueListener);
        }

    }

    void start() {

        for (Thread queueListener: queueListeners) {
            if (queueListener != null){
                queueListener.setName("push-info-refresh-thread" + queueListener.getId());
                queueListener.setDaemon(true);
                queueListener.start();
            }

        }

        LOGGER.info("push-info refresh execution start.");

    }

    void stop() {

        for (Thread queueListener: queueListeners) {
            if (queueListener != null){
                queueListener.interrupt();
            }

        }
        PUSH_INFO_REFRESH_POOL.shutdownNow();

        LOGGER.info("push-info refresh execution stop.");

        PUSH_INFO_REFRESH_POOL.shutdownNow();

        LOGGER.info("push-inforefresh execution stop.");

    }

}