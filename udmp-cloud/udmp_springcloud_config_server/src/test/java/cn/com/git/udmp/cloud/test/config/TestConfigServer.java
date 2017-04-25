package cn.com.git.udmp.cloud.test.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import cn.com.git.udmp.cloud.config.UdmpConfigServerApplication;

@SpringBootTest(classes=UdmpConfigServerApplication.class,webEnvironment=WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
public class TestConfigServer {
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    /**
     * @title
     * @description 测试config server是否配置成功
     *  
    */
    @Test
    public void testConfigServer() {
        System.out.println("config servert startUp");
        String body = this.restTemplate.getForObject("/foo/development", String.class);
        System.out.println("====================================================");
        System.out.println("获取到的配置是:");
        System.out.println(body);
        System.out.println("====================================================");
    }
}
