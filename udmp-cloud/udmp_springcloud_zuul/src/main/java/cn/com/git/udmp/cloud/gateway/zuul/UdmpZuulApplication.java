package cn.com.git.udmp.cloud.gateway.zuul;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import cn.com.git.udmp.cloud.gateway.zuul.filter.AccessFilter;

@EnableZuulProxy
@SpringCloudApplication
public class UdmpZuulApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(UdmpZuulApplication.class).web(true).run(args);
    }
    
    @Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }
}
