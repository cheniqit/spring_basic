package com.mk.hotel.consume;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.mk.framework.DateUtils;
import com.mk.framework.DistributedLockUtil;
import com.mk.framework.JsonUtils;
import com.mk.hotel.common.Constant;
import com.mk.hotel.consume.enums.TopicEnum;
import com.mk.hotel.consume.service.impl.QueueErrorInfoServiceImpl;
import com.mk.hotel.message.MsgProducer;
import com.mk.hotel.roomtypeprice.dto.RoomTypePriceSpecialDto;
import com.mk.hotel.roomtypeprice.service.impl.RoomTypePriceSpecialServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by chenqi on 16/10/9.
 */
@Component
public class RoomTypePriceConsume implements InitializingBean,DisposableBean {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private DefaultMQPushConsumer consumer;

    @Autowired
    private MsgProducer msgProducer;

    @Autowired
    private QueueErrorInfoServiceImpl queueErrorInfoService;

    @Autowired
    private RoomTypePriceSpecialServiceImpl roomTypePriceSpecialService;

    private static String CONSUMER_GROUP_NAME = "hotelRoomTypePriceConsumer";

    @Override
    public void afterPropertiesSet(){
        try {
            /**
             * 一个应用创建一个Consumer，由应用来维护此对象，可以设置为全局对象或者单例<br>
             * 注意：ConsumerGroupName需要由应用来保证唯一
             */
            consumer = new DefaultMQPushConsumer(
                    CONSUMER_GROUP_NAME);
            logger.info("RoomTypePriceConsume name addr: "+msgProducer.getNamesrvAddr());
            consumer.setNamesrvAddr(msgProducer.getNamesrvAddr());
            consumer.setInstanceName(CONSUMER_GROUP_NAME);
            final TopicEnum topicEnum = TopicEnum.ROOM_TYPE_PRICE;
            consumer.subscribe(topicEnum.getName(), "*");

            consumer.registerMessageListener(new MessageListenerConcurrently() {
                /**
                 * 默认msgs里只有一条消息，可以通过设置consumeMessageBatchMaxSize参数来批量接收消息
                 */
                public ConsumeConcurrentlyStatus consumeMessage(
                        List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    MessageExt messageExt = msgs.get(0);
                    String msg = null;
                    String lockValue = null;
                    String lockKey = null;
                    //
                    String messageKey = messageExt.getKeys();
                    String messageValue = DistributedLockUtil.tryLock(messageKey, Constant.MSG_KEY_LOCK_EXPIRE_TIME);
                    if(messageValue == null){
                        logger.info("topic name:{} msg :{} messageValue is null success", topicEnum.getName(), msg);
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }

                    //
                    try {
                        msg = new String(messageExt.getBody(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    logger.info(topicEnum.getName()+" msg :"+msg);
                    try {
                        if("special".equals(messageExt.getTags())){
                            RoomTypePriceSpecialDto roomTypePriceSpecial = JsonUtils.fromJson(msg, DateUtils.FORMAT_DATETIME, RoomTypePriceSpecialDto.class);
                            lockKey = "hotel_roomtype_price_lock" + roomTypePriceSpecial.getRoomTypeId()+roomTypePriceSpecial.getDay();
                            lockValue = DistributedLockUtil.tryLock(lockKey, 40);
                            if(lockValue ==null){
                                logger.info("topic name:{} msg :{} lockValue is null RECONSUME_LATER", topicEnum.getName(), msg);
                                DistributedLockUtil.releaseLock(messageKey, messageValue);
                                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                            }
                            roomTypePriceSpecialService.executeConsumeMessage(roomTypePriceSpecial, topicEnum);
                        }else{

                        }
                    }catch (Exception e){
                        queueErrorInfoService.saveQueueErrorInfo(msg, topicEnum);
                        e.printStackTrace();
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }finally {
                        if(lockKey != null && lockValue != null){
                            DistributedLockUtil.releaseLock(lockKey, lockValue);
                        }
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });

            /**
             * Consumer对象在使用之前必须要调用start初始化，初始化一次即可<br>
             */
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void destroy() throws Exception {
        if(consumer != null){
            consumer.shutdown();
        }
    }
}
