

package cn.com.git.udmp.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author liang
 * 2016年8月24日
 */
@SpringBootApplication
@EnableWebMvc
public class Application {
    public static void main(String[] args) {
        new SpringApplication(UdmpApplication.class).run(args);
    }
}
