
package cn.com.git.udmp.springboot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import cn.com.git.udmp.boot.UdmpApplication;
import cn.com.git.udmp.core.logging.ILog;

/** 
 * @description 测试用单元测试
 * @author Liang liuliang1@git.com.cn 
 * @date 2016年10月12日 上午10:13:01  
*/
@RunWith(SpringRunner.class)
@SpringBootTest(classes= UdmpApplication.class,webEnvironment=WebEnvironment.NONE)
public class DemoDruidJunitTest implements ILog{
    @Test
    public void test1(){
        logger.debug("测试demo");
        System.out.println("测试demo");
    }
}
