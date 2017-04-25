

package cn.com.git.udmp.boot.config;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.opensymphony.sitemesh.webapp.SiteMeshFilter;

/**
 * @author liang 2016年8月19日
 */
@Configuration
public class SiteMeshConfig{
    @Bean
    public FilterRegistrationBean siteMeshFilter() {
        Filter filter = new SiteMeshFilter();
        FilterRegistrationBean registrationBean  = new FilterRegistrationBean();
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns("/a/*");
        registrationBean.addUrlPatterns("/f/*");
        registrationBean.setOrder(0);
        return registrationBean;
    }
    
    
}
