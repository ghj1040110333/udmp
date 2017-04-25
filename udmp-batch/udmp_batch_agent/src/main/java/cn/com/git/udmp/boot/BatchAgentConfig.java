package cn.com.git.udmp.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import cn.com.git.udmp.component.batch.communication.protobuf.IAccepter;
import cn.com.git.udmp.core.logging.ILog;

/**
 * 批处理执行代理配置
 * @author liang
 * 2016年7月26日
 */
@SpringBootApplication
@ImportResource(locations = { "classpath*:spring-context-batch*.xml" })
@ComponentScan(basePackages={"cn.com.git.udmp.component.batch"})
@EnableConfigurationProperties(AgentServerProperties.class)
@EnableAsync
public class BatchAgentConfig implements CommandLineRunner,ILog{
    
    @Autowired
    AgentServerProperties serverProperties;
    
    @Autowired
    @Qualifier("agentSocketServer")
    IAccepter agent;
    /**
     * @title
     * @description 根据agent的properties配置自启动agent端
     *
     * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
     * @param args
     * @throws Exception 
    */
    @Override
    @Async
    public void run(String... args) throws Exception {
        logger.debug("===================================>>agent端启动中");
        agent.accept(serverProperties.getServerPort());
        logger.debug("===================================>>agent端启动完毕");
    }
}