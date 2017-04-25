package cn.com.git.udmp.calc.camel.routes;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.git.udmp.calc.camel.job.impl.Job1;



@Component
public class CalcRouteBuilder extends SpringRouteBuilder{

    @Autowired
    Job1 job1 ;
    @Override
    public void configure() throws Exception {
        from("direct:A")
//        .transform(simple("A1[ ${body} ]"))
//        .to("direct:B")
//        .transform(simple("A2[ ${body} ]"))
        .bean(Job1.class,"doProcess")
        .bean(Job1.class,"doProcess")
        .bean(Job1.class,"doProcess")
        .bean(Job1.class,"doProcess")
        .bean(job1,"doProcess")
        .bean(job1,"doProcess")
        .bean(job1,"doProcess")
        .bean(job1,"doProcess")
        .to("mock:endA");
        
        from("direct:B")
//        .to("dozer:transformOrder?mappingFile=/META-INF/mappings/start2order-dozer-mapping.xml&sourceModel=cn.com.git.udmp.calc.camel.mappings.Start&targetModel=cn.com.git.udmp.calc.camel.mappings.Order")
//        .to("dozer:transformOrder?mappingFile=/META-INF/mappings/test-dozer-mapping.xml&targetModel=cn.com.git.udmp.calc.camel.mappings.Order")
//        .to("dozer:transformOrder?mappingFile=/META-INF/mappings/order2report-dozer-mapping.xml&sourceModel=cn.com.git.udmp.calc.camel.mappings.Order&targetModel=cn.com.git.udmp.calc.camel.mappings.Report")
        .to("mock:endB");
    }

}
