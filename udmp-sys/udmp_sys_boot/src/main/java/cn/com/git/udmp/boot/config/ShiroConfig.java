

package cn.com.git.udmp.boot.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;

import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.google.common.collect.Maps;

import cn.com.git.udmp.modules.sys.security.FormAuthenticationFilter;

/**
 * @author liang
 * 2016年8月23日
 */
@Configuration
@EnableAutoConfiguration
@Import(ShiroManager.class)
public class ShiroConfig {
    
    /**
     * shiro的xml版filter配置,使用springboot配置后无用
     */
//    @Bean
//    @Deprecated
//    public FilterRegistrationBean fileUploadFilter(){
//        Filter filter = new DelegatingFilterProxy();
//        FilterRegistrationBean bean = new FilterRegistrationBean();
//        bean.setFilter(filter);
//        bean.addUrlPatterns("/*");
//        bean.addInitParameter("targetFilterLifecycle", "true");
//        return bean;
//    }
//    
    
    
    
//    @Bean(name = "realm")
//    @DependsOn("lifecycleBeanPostProcessor")
//    @ConditionalOnMissingBean
//    public Realm realm() {
//        Class<?> relmClass = SystemAuthorizingRealm.class;
//        return (Realm) BeanUtils.instantiate(relmClass);
//    }
    
    /**
     * 
     * @title ShiroFilter的包装
     *
     * @description 使用Spring的Filter注册机制来包装ShiroFilter，设置顺序号为1
     * 
     * @return
     */
    @Bean  
    public FilterRegistrationBean filterRegistrationBean() {  
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();  
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));   
        filterRegistration.setEnabled(true);  
        filterRegistration.addUrlPatterns("/*");   
        filterRegistration.setOrder(1);
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);  
        return filterRegistration;  
    } 

    @Bean(name = "shiroFilter")
    @DependsOn("securityManager")
    @ConditionalOnMissingBean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultSecurityManager securityManager, Realm realm) {
        securityManager.setRealm(realm);

        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        shiroFilter.setLoginUrl("/a/login");
        shiroFilter.setSuccessUrl("/a?login");
        
        //设置filter映射规则
        Map<String, String> filterChainDefinitionMap = Maps.newHashMap();
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/userfiles/**", "anon");
        filterChainDefinitionMap.put("/a/cas", "cas");
        filterChainDefinitionMap.put("/a/login", "authc");
        filterChainDefinitionMap.put("/a/logout", "logout");
        filterChainDefinitionMap.put("/a/**", "user");
//        filterChainDefinitionMap.put("/m/**", "mobileUser");
        filterChainDefinitionMap.put("/act/rest/service/editor/**", "perms[act:model:edit]");
        filterChainDefinitionMap.put("/act/rest/service/model/**", "perms[act:model:edit]");
        filterChainDefinitionMap.put("/act/rest/service/**", "user");
        filterChainDefinitionMap.put("/ReportServer/**", "user");
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap );
        
        //设置 各类filter(cas,authc,mobiluser)
        Map<String, Filter> filters = new HashMap<String, Filter>();
        filters.put("cas", casFilter());
        filters.put("authc", formAuthenticationFilter());
//        filters.put("mobileUser", mobileUserFilter());
        shiroFilter.setFilters(filters );
        return shiroFilter;
    }
    
    /**
     * 配置cas的filter类
     * @return
     */
    public CasFilter casFilter(){
        CasFilter filter = new CasFilter();
        filter.setFailureUrl("login");
        return filter;
    }

    
    public FormAuthenticationFilter formAuthenticationFilter(){
        FormAuthenticationFilter filter = new FormAuthenticationFilter();
        return filter;
    }
    
    
//    public AbstractFilter mobileUserFilter(){
//        System.out.println("初始化myfilter了");
//        return new MobileUserFilter();
//    }

}
