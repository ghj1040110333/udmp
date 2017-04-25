

package cn.com.git.udmp.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPoolConfig;

/**
 * jedis配置
 * @author liang
 * 2016年8月17日
 */
@Configuration
@EnableConfigurationProperties(JedisProperties.class)
public class JedisConfig {
    
    @Autowired
    JedisProperties jedisProperties;
    
     @Bean
     public JedisPoolConfig jedisPoolConfig(){
         System.out.println("jedis初始化:"+jedisProperties.getMaxIdle());
         return new JedisPoolConfig();
     }
     
     
     public static void main(String[] args) {
         ApplicationContext ctx = new AnnotationConfigApplicationContext(JedisConfig.class);
         JedisPoolConfig myService = ctx.getBean(JedisPoolConfig.class);
         JedisProperties bean = ctx.getBean(JedisProperties.class);
         System.out.println(bean);
    }
}
