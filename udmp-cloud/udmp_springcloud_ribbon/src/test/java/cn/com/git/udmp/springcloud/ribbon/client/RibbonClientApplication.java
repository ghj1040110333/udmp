package cn.com.git.udmp.springcloud.ribbon.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
   *	@author  Liang
   *	@since 2016年10月18日
**/
@Configuration
@RibbonClient(name = "foo", configuration = FooConfiguration.class)
public class RibbonClientApplication {
	public static void main(String[] args) {
        new SpringApplicationBuilder(RibbonClientApplication.class).web(true).run(args);
    }
}
