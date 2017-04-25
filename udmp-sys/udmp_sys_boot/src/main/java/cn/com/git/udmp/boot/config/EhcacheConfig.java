

package cn.com.git.udmp.boot.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;

import cn.com.git.udmp.core.logging.ILog;

/**
 * @author liang
 * 2016年8月25日
 */
@Configuration
@EnableAutoConfiguration
public class EhcacheConfig implements ILog{
    @Bean(name="ehcache")
    @ConditionalOnMissingBean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
        logger.info("aplus ehcache init");
        EhCacheManagerFactoryBean bean = new EhCacheManagerFactoryBean();
        bean.setConfigLocation(new DefaultResourceLoader().getResource("classpath:META-INF/cache/ehcache.xml"));
        return bean;
    }
}
