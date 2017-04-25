

package cn.com.git.udmp.boot.config;

import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.web.servlet.ErrorPageRegistrar;
import org.springframework.boot.web.servlet.ErrorPageRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * 全局异常跳转页面
 * 
 * @author liang 2016年8月19日
 */
@Configuration
public class RedirectPageConfig {
    @Bean
    public ErrorPageRegistrar errorPageRegistrar() {
        return new MyErrorPageRegistrar();
    }

    private static class MyErrorPageRegistrar implements ErrorPageRegistrar {
        @Override
        public void registerErrorPages(ErrorPageRegistry registry) {
            registry.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/WEB-INF/views/error/400.jsp"));
            registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/WEB-INF/views/error/404.jsp"));
            registry.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/WEB-INF/views/error/500.jsp"));
        }
    }
}
