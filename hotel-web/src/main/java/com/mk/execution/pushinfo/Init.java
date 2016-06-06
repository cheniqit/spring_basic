package com.mk.execution.pushinfo;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by 振涛 on 2016/2/19.
 */
@Component
@Scope("singleton")
public class Init implements ApplicationListener<ContextRefreshedEvent>, DisposableBean {

    private Thread jobManagerThread;

    Init(){
        jobManagerThread = new Thread(new ThreadJobManager(), "init-pushinfo-manager-thread");
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        //防止调用两次
//        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {
            jobManagerThread.setDaemon(true);
            jobManagerThread.start();
//        }
    }

    @Override
    public void destroy() throws Exception {
        if (jobManagerThread != null){
            jobManagerThread.interrupt();
        }

    }
}
