package cn.com.git.udmp.boot.test.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/** 
 * @description 当前测试工程启动类
 * @author Liang liuliang1@git.com.cn 
 * @date 2017年2月28日 下午4:42:57  
*/
@SpringBootApplication
@ComponentScan("cn.com.git")
public class TestMain {
    public static void main(String[] args) {
        SpringApplication.run(TestMain.class, args);
    }
}
