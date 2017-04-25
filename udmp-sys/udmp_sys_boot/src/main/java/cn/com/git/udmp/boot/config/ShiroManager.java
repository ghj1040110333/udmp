

package cn.com.git.udmp.boot.config;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.DefaultResourceLoader;

import cn.com.git.udmp.common.security.shiro.session.CacheSessionDAO;
import cn.com.git.udmp.common.security.shiro.session.SessionManager;

/**
 * @author liang
 * 2016年8月23日
 */
@EnableAutoConfiguration
public class ShiroManager {
    /**
     * 保证实现了Shiro内部lifecycle函数的bean执行
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    @ConditionalOnMissingBean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean(name = "defaultAdvisorAutoProxyCreator")
    @ConditionalOnMissingBean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;

    }
    
//    @Bean(name="cacheManager")
//    public EhCacheCacheManager ehCacheCacheManager(){
//        return new EhCacheCacheManager(ehCacheManagerFactoryBean().getObject());
//    }

    
    /**
     * 用户授权信息Cache
     */
    @Bean(name = "shiroCacheManager")
//    @DependsOn(value="ehcache")
    @ConditionalOnMissingBean
    public CacheManager cacheManager(EhCacheManagerFactoryBean ehcache) {
        EhCacheManager manager = new EhCacheManager();
        manager.setCacheManager(ehcache.getObject());
        return manager;
    }
    
    
    
//    @Deprecated
//    @Bean(name = "shiroCacheManager")
//    @ConditionalOnMissingBean
//    public CacheManager cacheManager() {
//        return new MemoryConstrainedCacheManager();
//    }

    @Bean(name = "securityManager")
    @ConditionalOnMissingBean
    public DefaultSecurityManager securityManager(CacheManager shiroCacheManager,SessionManager sessionManager) {
        DefaultSecurityManager sm = new DefaultWebSecurityManager();
        sm.setCacheManager(shiroCacheManager);
        sm.setSessionManager(sessionManager);
        return sm;
    }

    @Bean
    @ConditionalOnMissingBean
//    public SessionManager sessionManager(){
        public SessionManager sessionManager(CacheSessionDAO sessionDAO){
        SessionManager sessionManager = new SessionManager();
        sessionManager.setSessionDAO(sessionDAO);
        sessionManager.setGlobalSessionTimeout(1800000L);
        sessionManager.setSessionValidationInterval(120000L);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdCookie(new SimpleCookie("jeesite.session.id"));
        sessionManager.setSessionIdCookieEnabled(true);
        return sessionManager;
    }
    
    @Bean
    @ConditionalOnMissingBean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return new AuthorizationAttributeSourceAdvisor();
    }
}
