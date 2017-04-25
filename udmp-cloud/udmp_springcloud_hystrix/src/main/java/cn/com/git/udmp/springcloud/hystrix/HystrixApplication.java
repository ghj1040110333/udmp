package cn.com.git.udmp.springcloud.hystrix;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
   *	@author  Liang
   *	@since 2016年10月17日
**/
@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableEurekaClient
@EnableTurbine
public class HystrixApplication {
	  public static void main(String[] args) {
	        new SpringApplicationBuilder(HystrixApplication.class).web(true).run(args);
	    }
}
