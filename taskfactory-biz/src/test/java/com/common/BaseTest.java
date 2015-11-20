package com.common;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Thinkpad on 2015/11/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
/*@WebAppConfiguration(value = "src/main/webapp")*/
@ContextConfiguration(locations = {"classpath:root-context.xml"})
public class BaseTest extends AbstractJUnit4SpringContextTests {
}
