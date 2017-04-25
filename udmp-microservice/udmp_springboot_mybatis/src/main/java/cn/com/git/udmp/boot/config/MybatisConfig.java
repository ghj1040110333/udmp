package cn.com.git.udmp.boot.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import cn.com.git.udmp.core.logging.ILog;


@Configuration
public class MybatisConfig implements CommandLineRunner,ILog{

    @Override
    public void run(String... arg0) throws Exception {
        logger.info("Mybatis加载了");
    }
    
}