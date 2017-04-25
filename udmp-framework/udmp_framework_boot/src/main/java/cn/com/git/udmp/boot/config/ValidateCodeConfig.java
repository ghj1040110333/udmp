

package cn.com.git.udmp.boot.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.com.git.udmp.common.servlet.ValidateCodeServlet;

/**
 * 随机验证码的servlet配置
 * @author liang 2016年8月22日
 */
@Configuration
public class ValidateCodeConfig {
    @Bean
    public ServletRegistrationBean userfilesDownloadServlet() {
        ValidateCodeServlet servlet = new ValidateCodeServlet();
        
        ServletRegistrationBean bean = new ServletRegistrationBean(servlet,
                "/servlet/validateCodeServlet");
        return bean;
    }
}
