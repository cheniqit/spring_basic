package com.mk.hotel.consume;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.mk.framework.DateUtils;
import com.mk.framework.JsonUtils;
import com.mk.hotel.common.Constant;
import com.mk.hotel.hotelinfo.dto.HotelDto;
import com.mk.hotel.hotelinfo.service.impl.HotelServiceImpl;
import com.mk.hotel.message.MsgProducer;
import com.mk.hotel.roomtype.model.RoomType;
import com.mk.hotel.roomtype.service.impl.RoomTypeServiceImpl;
import com.mk.hotel.roomtype.service.impl.RoomTypeStockServiceImpl;
import com.mk.hotel.roomtypestock.dto.RoomTypeStockSpecialDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by chenqi on 16/10/9.
 */
@Component
public class RoomTypeStockConsume implements InitializingBean,DisposableBean {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private DefaultMQPushConsumer consumer;

    @Autowired
    private MsgProducer msgProducer;

    @Autowired
    private RoomTypeServiceImpl roomTypeService;

    @Autowired
    private HotelServiceImpl hotelService;

    @Autowired
    private RoomTypeStockServiceImpl roomTypeStockService;

    private static String CONSUMER_GROUP_NAME = "hotelRoomTypeStockConsumer";

    @Override
    public void afterPropertiesSet(){
        try {
            /**
             * 一个应用创建一个Consumer，由应用来维护此对象，可以设置为全局对象或者单例<br>
             * 注意：ConsumerGroupName需要由应用来保证唯一
             */
            consumer = new DefaultMQPushConsumer(
                    CONSUMER_GROUP_NAME);
            consumer.setNamesrvAddr(msgProducer.getNamesrvAddr());
            logger.info("RoomTypeStockConsume name addr: "+msgProducer.getNamesrvAddr());
            consumer.setInstanceName(CONSUMER_GROUP_NAME);

            consumer.subscribe(Constant.TOPIC_ROOMTYPE_STOCK, "*");

            consumer.registerMessageListener(new MessageListenerConcurrently() {
                /**
                 * 默认msgs里只有一条消息，可以通过设置consumeMessageBatchMaxSize参数来批量接收消息
                 */
                public ConsumeConcurrentlyStatus consumeMessage(
                        List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    MessageExt messageExt = msgs.get(0);
                    try {
                        if("special".equals(messageExt.getTags())){
                            String msg = null;
                            try {
                                msg = new String(messageExt.getBody(), "UTF-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            logger.info(msg);
                            RoomTypeStockSpecialDto roomTypeStockSpecial = JsonUtils.fromJson(msg, DateUtils.FORMAT_DATETIME, RoomTypeStockSpecialDto.class);
                            //查找对应的fangid
                            RoomType roomType = roomTypeService.selectRoomTypeById(roomTypeStockSpecial.getRoomTypeId());
                            if(roomType == null){
                                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                            }
                            HotelDto hotelDto = hotelService.findById(roomType.getHotelId());
                            if(hotelDto == null){
                                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                            }

                            //放入redis
                            roomTypeStockService.updateRedisStockByTotal(hotelDto.getId(), roomTypeStockSpecial.getRoomTypeId(), roomTypeStockSpecial.getDay(),
                                    roomTypeStockSpecial.getTotalNumber().intValue(), 0);
                        }else{

                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
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
