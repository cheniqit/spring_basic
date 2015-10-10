package com.mk.channel.biz.mapper;

import com.mk.channel.biz.mapper.channel.CountAgentSpreadMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created with IntelliJ IDEA.
 * User: Thinkpad
 * Date: 15-9-15
 * Time: 上午11:51
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:root-context.xml")
public class BlackListMapperTest {
    @Autowired
    private CountAgentSpreadMapper blackListMapperMock;

    @Test
    public void query() throws Exception {

    }
}
