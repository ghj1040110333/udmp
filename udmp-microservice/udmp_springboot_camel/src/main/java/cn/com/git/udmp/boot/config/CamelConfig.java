package cn.com.git.udmp.boot.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import cn.com.git.udmp.core.logging.ILog;


@SpringBootApplication
@ImportResource(locations = { "classpath*:META-INF/**/spring-camel*.xml" })
public class CamelConfig implements CommandLineRunner, ILog {

    @Override
    public void run(String... arg0) throws Exception {
        logger.info("camel配置启动...");
    }

}
