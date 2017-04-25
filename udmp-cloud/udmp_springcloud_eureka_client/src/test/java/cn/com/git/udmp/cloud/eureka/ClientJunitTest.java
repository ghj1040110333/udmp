package cn.com.git.udmp.cloud.eureka;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import cn.com.git.udmp.cloud.eureka.client.EurekaClientApplication;

/**
   *	@author  Liang
   *	@since 2016年10月17日
**/
@RunWith(SpringRunner.class)
@SpringBootTest(classes=EurekaClientApplication.class,webEnvironment=WebEnvironment.DEFINED_PORT)
public class ClientJunitTest {
	@Test
	public void test() throws InterruptedException{
		System.out.println("eureka client测试用例");
		Thread.sleep(100000);
	}
}
