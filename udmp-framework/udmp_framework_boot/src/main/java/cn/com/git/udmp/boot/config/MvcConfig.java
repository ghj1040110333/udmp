

package cn.com.git.udmp.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author liang
 * 2016年8月24日
 */
@Configuration  
public class MvcConfig {
//    @Bean
    public InternalResourceViewResolver internalResourceViewResolver(){
        System.out.println("resolver启动了");
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setSuffix(".jsp");
        resolver.setPrefix("/META-INF/resources/WEB-INF/views/");
        return resolver;
    }
}
