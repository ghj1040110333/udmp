package cn.com.git.udmp.springcloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/** 
 * @description 配置中心的client端样例
 * @author git_man git_man@git.com.cn 
 * @date 2016年10月26日 上午10:02:00  
*/
@SpringBootApplication
@RestController
public class ConfigClientApplication {
    @RequestMapping("/")
    public String home() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }
}
