

package cn.com.git.udmp.boot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import cn.com.git.udmp.boot.UdmpApplication;
import cn.com.git.udmp.boot.test.modules.demo.service.BatchTestDestService;

/**
 * @description 测试xml配置方式的Druid数据源注入
 * @author liang
 * 2016年9月5日
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= UdmpApplication.class,webEnvironment=WebEnvironment.NONE)
public class MybatisJunitTest {
    @Autowired
    BatchTestDestService service;
    
    @Test
    public void test1(){
        System.out.println(123);
        service.selectByPrimaryKey(1L);
//        System.out.println(MybatisTest.class.isAssignableFrom(CommandLineRunner.class));
    }
}
