package cn.com.git.udmp.boot.camel.test.builder;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.git.udmp.boot.camel.test.process.Job1;

@Component
public class CamelBuilder extends SpringRouteBuilder{

    @Autowired
    Job1 job1 ;
    
    @Override
    public void configure() throws Exception {
        from("direct:A")
        .bean(Job1.class,"process")
        .bean(Job1.class,"process")
        .bean(Job1.class,"process")
        .bean(Job1.class,"process")
        .bean(job1,"process")
        .bean(job1,"process")
        .bean(job1,"process")
        .bean(job1,"process").end();
        
        from("direct:B").to("direct:inout");
    }

}
