

package cn.com.git.udmp.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import cn.com.git.udmp.boot.UdmpApplication;
import cn.com.git.udmp.core.logging.ILog;

/**
 * Eureka Runtime Server的Spring Boot启动类
 * 
 * @description
 * @author Spring Cao
 * @date 2016年10月12日 下午2:23:00
 */
@SpringBootApplication
@EnableEurekaServer
//@EnableDiscoveryClient //when only Eureka available
public class EurekaApplication implements ILog {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(UdmpApplication.class, args);
    }
}
