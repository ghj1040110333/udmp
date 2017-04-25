package cn.com.git.udmp.boot.camel.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import cn.com.git.udmp.boot.UdmpApplication;
import cn.com.git.udmp.boot.camel.entity.Message;
import cn.com.git.udmp.boot.camel.service.impl.LineCamelService;

@SpringBootApplication
public class ApplicationTest implements CommandLineRunner{
    
    @Autowired
    LineCamelService service;
    
    public static void main(String[] args) {
        new SpringApplicationBuilder(UdmpApplication.class).web(true).run(args);
    }

    @Override
    public void run(String... arg0) throws Exception {
        System.out.println("camel启动了");
        service.execRoute("A", new Message());
    }
}
