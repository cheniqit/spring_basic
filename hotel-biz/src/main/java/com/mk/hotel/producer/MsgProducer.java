package com.mk.hotel.producer;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.dianping.cat.Cat;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.UnsupportedEncodingException;

/**
 * Created by chenqi on 16/9/12.
 */
@Component
public class MsgProducer {
    @Value("${namesrvAddr}")
    private String namesrvAddr;

    @Value("${producerGroupName}")
    private String producerGroupName;

    @Value("${instanceName}")
    private String instanceName;
    private DefaultMQProducer producer;


    public void sendMsg(String topic, String tag, String key, String message)
            throws UnsupportedEncodingException, InterruptedException, RemotingException, MQClientException, MQBrokerException {
        if(StringUtils.isBlank(message)){
            return;
        }
        Message msg = new Message(topic,// topic
                tag,// tag
                key,// key
                (message).getBytes("UTF-8"));// body
        this.getProducer().send(msg);
    }

    public void sendMsg(String topic, String tag, String key, String message, int delayTimeLevel)
            throws UnsupportedEncodingException, InterruptedException, RemotingException, MQClientException, MQBrokerException {
        if(StringUtils.isBlank(message)){
            return;
        }
        Message msg = new Message(topic,// topic
                tag,// tag
                key,// key
                (message).getBytes("UTF-8"));// body
        msg.setDelayTimeLevel(delayTimeLevel);
        this.getProducer().send(msg);
    }

    public void sendMsg(String topic, String message) {
        try {
            sendMsg(topic, null, null, message);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void shutdownProducer(){
        this.getProducer().shutdown();
    }

    @PostConstruct
    public void startProducer(){
        /**
         * 一个应用创建一个Producer，由应用来维护此对象，可以设置为全局对象或者单例<br>
         * 注意：ProducerGroupName需要由应用来保证唯一<br>
         * ProducerGroup这个概念发送普通的消息时，作用不大，但是发送分布式事务消息时，比较关键，
         * 因为服务器会回查这个Group下的任意一个Producer
         */
        DefaultMQProducer producer = new DefaultMQProducer(producerGroupName);
        producer.setNamesrvAddr(namesrvAddr);
        producer.setInstanceName(instanceName);

        /**
         * Producer对象在使用之前必须要调用start初始化，初始化一次即可<br>
         * 注意：切记不可以在每次发送消息时，都调用start方法
         */
        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
            Cat.logError(e.getMessage(), e);
        }
        this.setProducer(producer);
    }

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public String getProducerGroupName() {
        return producerGroupName;
    }

    public void setProducerGroupName(String producerGroupName) {
        this.producerGroupName = producerGroupName;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public DefaultMQProducer getProducer() {
        return producer;
    }

    public void setProducer(DefaultMQProducer producer) {
        this.producer = producer;
    }
}
