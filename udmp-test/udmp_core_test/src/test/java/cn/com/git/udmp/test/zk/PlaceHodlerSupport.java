package cn.com.git.udmp.test.zk;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author <a href="mailto:wangyuxuan@dangdang.com">Yuxuan Wang</a>
 *
 */
public class PlaceHodlerSupport {
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = null;
		try {
//			context = new ClassPathXmlApplicationContext("classpath*:META-INF/spring/spring-udmp-context-zk-support.xml");
			context = new ClassPathXmlApplicationContext("classpath*:META-INF/spring/spring-udmp-context-zk-support.xml","classpath*:META-INF/spring/spring-udmp-context-zk-support-test.xml");
			context.registerShutdownHook();
			context.start();
			ExampleBean bean = context.getBean(ExampleBean.class);
			System.out.println(bean);
		} finally {
			if (context != null) {
				context.close();
			}    
		}
	}

}
