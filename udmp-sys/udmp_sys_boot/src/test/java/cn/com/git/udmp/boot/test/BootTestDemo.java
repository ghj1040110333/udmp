

package cn.com.git.udmp.boot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import cn.com.git.udmp.boot.UdmpApplication;

/**
 * spring-boot的简单测试用例
 * @author liang
 * 2016年8月30日
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = UdmpApplication.class)
public class BootTestDemo {
    @Test
    public void test(){
        System.out.println(123);
    }
}
