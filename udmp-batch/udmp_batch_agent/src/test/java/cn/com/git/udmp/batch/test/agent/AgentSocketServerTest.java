package cn.com.git.udmp.batch.test.agent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import cn.com.git.udmp.component.batch.core.agent.communicate.AgentServer;
import cn.com.git.udmp.component.batch.core.agent.communicate.AgentSocketServer;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations={"classpath*:/META-INF/spring/spring-udmp-context*.xml","classpath*:/META-INF/**/spring-context*.xml","classpath*:/**/spring*.xml"})
public class AgentSocketServerTest {
    
    @Autowired
    AgentServer server;
    @Test
    public void test1() throws InterruptedException{
        new Thread(new Runnable() {
            @Override
            public void run() {
                server.accept("9999");
            }
        }).start();
        Thread.sleep(10000);
        server.stopAccepter();
        Thread.sleep(10000);
    }
}
