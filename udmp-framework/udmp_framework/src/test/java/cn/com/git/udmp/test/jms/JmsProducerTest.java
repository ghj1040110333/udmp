package cn.com.git.udmp.test.jms;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.git.udmp.test.jms.service.ProducerServiceImpl;

@ContextConfiguration(locations = { "classpath*:/META-INF/spring-udmp-test-context-jms-core.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class JmsProducerTest {

    @Autowired
    ProducerServiceImpl service;

    @Autowired
    ActiveMQQueue queue;


    public ActiveMQQueue getQueue() {
        return queue;
    }

    public void setQueue(ActiveMQQueue queue) {
        this.queue = queue;
    }

    public ProducerServiceImpl getService() {
        return service;
    }

    public void setService(ProducerServiceImpl service) {
        this.service = service;
    }

    @Test
    public void testJmsProduce() throws InterruptedException{
        service.sendMessage(queue, "test");
    }

}
