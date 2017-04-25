package cn.com.git.udmp.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @description 测试配置中心
 * @author Liang
 * @since 2016年10月14日
 **/
@SpringBootApplication
@EnableConfigServer
public class UdmpConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UdmpConfigServerApplication.class, args);
    }
}
