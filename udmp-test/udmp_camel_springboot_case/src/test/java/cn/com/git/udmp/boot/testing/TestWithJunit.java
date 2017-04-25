package cn.com.git.udmp.boot.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.git.udmp.boot.UdmpApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UdmpApplication.class)
public class TestWithJunit {
	@Test
	public void haha(){
		System.out.println("AAAAAA");
	}
}
