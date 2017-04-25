package cn.com.git.udmp.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

import cn.com.git.udmp.boot.UdmpApplication;
import cn.com.git.udmp.boot.camel.service.impl.LineCamelService;

@SpringBootApplication
@ComponentScan(basePackages={"cn.com.git.udmp.calc","cn.com.git.udmp.kafka","cn.com.git.udmp.web"})
public class CalcConfig implements CommandLineRunner{
    @Autowired
    LineCamelService service;
    
    public static void main(String[] args) {
        new SpringApplicationBuilder(UdmpApplication.class).web(false).run(args);
    }

    @Override
    public void run(String... arg0) throws Exception {
        System.out.println("calc启动了...");
    }
}
