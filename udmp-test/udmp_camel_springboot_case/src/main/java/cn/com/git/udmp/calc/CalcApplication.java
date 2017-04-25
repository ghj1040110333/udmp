package cn.com.git.udmp.calc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import cn.com.git.udmp.boot.UdmpApplication;
import cn.com.git.udmp.boot.camel.entity.Message;
import cn.com.git.udmp.boot.camel.service.impl.LineCamelService;
import cn.com.git.udmp.calc.camel.mappings.Report;
import cn.com.git.udmp.calc.camel.mappings.Start;
import cn.com.git.udmp.calc.modules.demo.dao.BatchTestSouceMapper;
import cn.com.git.udmp.calc.modules.demo.entity.BatchTestSouce;
import cn.com.git.udmp.common.model.DataObject;
import cn.com.git.udmp.core.logging.ILog;
import cn.com.git.udmp.web.service.AService;
import cn.com.git.udmp.web.service.BService;

@SpringBootApplication
public class CalcApplication implements CommandLineRunner, ILog {

    @Autowired
    LineCamelService service;

    @Autowired
    private BatchTestSouceMapper testSouceMapper;

    @Autowired
    AService a;
    
    @Autowired
    BService b;
    
    @Override
    public void run(String... arg0) throws Exception {
        System.out.println("启动了");
        a.a();
        b.c();
    }
//    @Override
//    public void run(String... arg0) throws Exception {
//        logger.info("初始化成功");
//        /** 测试规则是否好使 */
//        DataObject result = service.execRoute("A", new Message());
//        System.out.println("处理后的结果:" + result.getData());
//        
//        System.out.println("_______________________________");
//        
//        Start start = new Start();
//        start.setCustId("7400");
//        start.setAge(30);
//        start.setName("Augest");
//        Message msg = new Message();
//        msg.setPayload(start);
//        Report route = (Report)service.execRoute("B", new Message());
//        System.out.println(">>>>>>>>>>>>>>>>> "+route.getRepid());
//        
//        BatchTestSouce resultTest = testSouceMapper.selectByPrimaryKey(2L);
//        System.out.println(resultTest.getCharB());
//    }

    public static void main(String[] args) {
        /** with server */
//         SpringApplication.run(CalcApplication.class, args);
        /** without server */
        new SpringApplicationBuilder(UdmpApplication.class).web(true).run(args);
    }

}
