package cn.com.git.udmp.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="cn.com.git.udmp")
@ImportResource(locations={"classpath*:/META-INF/spring/spring-udmp-*.xml","classpath*:/META-INF/**/spring-context*.xml"})
public class FrameworkMain{
    
//    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(FrameworkMain.class);
        return application;
//        return application.sources(FrameworkMain.class);
    }
    
    public static void main(String[] args) {
//        SpringApplication.run(FrameworkMain.class, args).start();
        
//        String dbType = Global.getConfig("jdbc.type");
//        System.out.println("dbType:"+dbType);
        
        SpringApplication.run(UdmpApplication.class, args).start();
        
        
//        ApplicationContext ctx = new AnnotationConfigApplicationContext(JedisConfig.class);
//        JedisProperties bean = ctx.getBean(JedisProperties.class);
//        System.out.println(bean.getAddress());
    }
}
