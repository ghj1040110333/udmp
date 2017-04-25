package cn.com.git.udmp.cloud.eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Liang
 * @since 2016年10月17日
 **/
@SpringBootApplication
@EnableEurekaClient
@RestController
public class EurekaClientApplication {
	@RequestMapping("/")
	public String home() {
		return "Hello World";
	}
	@RequestMapping("/hello")
	public String hello() {
		return "hello,liang";
	}

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientApplication.class, args);
	}
}
