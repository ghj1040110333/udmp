package cn.com.git.udmp.test.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * activeMQ的xml版消费者测试类
 * @author liang
 * 2016年6月27日
 */
@ContextConfiguration(locations = { "classpath*:/META-INF/spring-udmp-test-context-jms-core.xml","classpath*:/META-INF/spring-udmp-test-context-jms-consume.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class JmsXmlConsumerTest {
    @Test
    public void testJmsConsume() throws InterruptedException{
        System.out.println("============加载spring xml，自动启动xml版消费者监听============");
        Thread.currentThread().sleep(100000);
    }
}
