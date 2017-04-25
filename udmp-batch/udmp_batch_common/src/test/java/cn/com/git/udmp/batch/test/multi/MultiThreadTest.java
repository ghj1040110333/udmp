package cn.com.git.udmp.batch.test.multi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

/**
 * 多线程测试
   *	@author  Liang
   *	@since 2016年9月23日
**/

public class MultiThreadTest {
	
	/**
	 * 测试线程池会不会丢失堵塞的线程（结论：不会滴，内部应该有个堵塞队列）
	 */
	@Test
	public void testThreadPool(){
		 ExecutorService es = Executors.newFixedThreadPool(30);//通信接收线程池
		 for(int i=0;i<100;i++){
			 System.out.println("启动"+i);
			 es.submit(new TestThread(i));
		 }
		 try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}

class TestThread implements  Runnable {
	int i;
	public TestThread(int i) {
		this.i = i;
	}

	public void run() {
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(i);
	}
}
