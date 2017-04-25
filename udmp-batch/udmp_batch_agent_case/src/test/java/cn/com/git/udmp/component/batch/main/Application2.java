package cn.com.git.udmp.component.batch.main;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.util.AssertionErrors;
import org.springframework.transaction.annotation.Transactional;

import cn.com.git.udmp.boot.UdmpApplication;
import cn.com.git.udmp.common.utils.SpringContextHolder;
import cn.com.git.udmp.component.batch.core.agent.communicate.AgentSocketServer;
import cn.com.git.udmp.modules.batch.dao.BatchTestSouceMapper;
import cn.com.git.udmp.modules.batch.entity.BatchTestSouce;

//@EnableAutoConfiguration
@ComponentScan(basePackages={"cn.com.git.udmp.common.utils","cn.com.git.udmp.component.batch","cn.com.git.udmp.modules.batch"})
@SpringBootApplication
public class Application2 {
    

    @Autowired
    private BatchTestSouceMapper testSouceMapper;

    
    
    public static void main(String[] args) {
        //不启动web容器
        ConfigurableApplicationContext context = new SpringApplicationBuilder(UdmpApplication.class).web(false).run(args);
    }

}