package cn.com.git.udmp.springcloud.ribbon.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.PingUrl;

/**
 * @description ribbon的配置类
 * @author Liang
 * @since 2016年10月18日
 **/
@Configuration
public class FooConfiguration {
	@Bean
	public IPing ribbonPing(IClientConfig config) {
		return new PingUrl();
	}
}
