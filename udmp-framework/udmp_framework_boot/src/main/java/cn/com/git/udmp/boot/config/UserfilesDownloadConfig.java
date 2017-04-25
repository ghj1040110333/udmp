

package cn.com.git.udmp.boot.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.com.git.udmp.common.servlet.UserfilesDownloadServlet;

/**
 * 用户文件下载的servlet配置
 * @author liang 2016年8月22日
 */
@Configuration
public class UserfilesDownloadConfig {
    @Bean
    public ServletRegistrationBean userfilesDownloadServlet() {
        UserfilesDownloadServlet servlet = new UserfilesDownloadServlet();
        
        ServletRegistrationBean bean = new ServletRegistrationBean(servlet,
                "/userfiles/*");
        return bean;
    }
}
