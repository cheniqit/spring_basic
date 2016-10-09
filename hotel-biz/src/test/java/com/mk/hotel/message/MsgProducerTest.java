package com.mk.hotel.message;

import com.mk.common.BaseTest;
import com.mk.hotel.common.Constant;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by chenqi on 16/10/9.
 */
public class MsgProducerTest extends BaseTest{

    @Autowired
    private MsgProducer msgProducer;

    @Test
    public void testSendMsg() throws Exception {
        msgProducer.sendMsg(Constant.TOPIC_ROOMTYPE_PRICE, "test");
    }
}