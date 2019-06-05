package com.touch.spring;

import com.touch.utils.ActiveMQUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring_jms.xml")
public class TestProducer {
    @Autowired
    Producer producer;
    @Before
    public void checkServer() {
        ActiveMQUtil.checkServer();
    }
    @Test
    public void testsend(){
        for (int i = 0; i <100 ; i++) {
            producer.sendTextMessage("消息"+i);
        }
    }
}
