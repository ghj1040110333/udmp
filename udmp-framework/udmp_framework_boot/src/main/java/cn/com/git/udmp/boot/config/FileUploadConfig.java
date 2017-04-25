

package cn.com.git.udmp.boot.config;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ckfinder.connector.FileUploadFilter;

/**
 * 文件上传配置
 * @author liang
 * 2016年8月22日
 */
@Configuration
public class FileUploadConfig {
    @Bean
    public FilterRegistrationBean fileUploadFilter(){
        Filter filter = new FileUploadFilter();
        
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(filter);
        bean.addUrlPatterns("/static/ckfinder/core/connector/java/connector.java");
        bean.addInitParameter("sessionCookieName", "JSESSIONID");
        bean.addInitParameter("sessionParameterName", "jsessionid");
        return bean;
    }
}
