package cn.com.git.udmp.boot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages={"cn.com.git.udmp.boot"})
@SpringBootApplication
public class UdmpApplication extends SpringBootServletInitializer implements CommandLineRunner{
    
    
    /* （非 Javadoc）
     * @see org.springframework.boot.web.support.SpringBootServletInitializer#configure(org.springframework.boot.builder.SpringApplicationBuilder)
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(UdmpApplication.class);
    }

    public static void main(String[] args) {
        //不启动web容器
        SpringApplication.run(UdmpApplication.class, args);
//        ConfigurableApplicationContext context = new SpringApplicationBuilder(UdmpApplication.class).web(true).run(args);
    }

    @Override
    public void run(String... args) throws Exception {
//        logger.info("spring容器启动了...");
    }
}