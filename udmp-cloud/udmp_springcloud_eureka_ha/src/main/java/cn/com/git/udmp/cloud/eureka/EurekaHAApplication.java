package cn.com.git.udmp.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.git.udmp.boot.UdmpApplication;
import cn.com.git.udmp.core.logging.ILog;

@SpringBootApplication
@EnableEurekaServer
@RestController
public class EurekaHAApplication implements ILog{
    
    @RequestMapping("/ha")
    public String echo(){
        return "This is Eureka HA solution ！";
    }
    
    public static void main(String[] args) {
        SpringApplication.run(UdmpApplication.class, args);
//        SpringApplication.run(EurekaHAApplication.class, args);
//        new SpringApplicationBuilder(EurekaHAApplication.class).web(true).run(args);
      }
}
