

package cn.com.git.udmp.boot.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.com.git.udmp.common.web.CKFinderConnectorServlet;

/**
 * ckFinder的servlet配置
 * @author liang 2016年8月22日
 */
@Configuration
public class CKFinderConfig {
    @Bean
    public ServletRegistrationBean ckFinderConnectorServlet() {
        CKFinderConnectorServlet servlet = new CKFinderConnectorServlet();
        ServletRegistrationBean bean = new ServletRegistrationBean(servlet,
                "/static/ckfinder/core/connector/java/connector.java");
        bean.addInitParameter("XMLConfig", "/META-INF/resources/WEB-INF/ckfinder.xml");
        bean.addInitParameter("debug", "false");
        bean.addInitParameter("configuration", "cn.com.git.udmp.common.web.CKFinderConfig");
        return bean;
    }
}
