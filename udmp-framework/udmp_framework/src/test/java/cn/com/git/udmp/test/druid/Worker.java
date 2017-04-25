package cn.com.git.udmp.test.druid;

import java.util.concurrent.Callable;

public class Worker implements Callable<Long>{
	private TableOperator test;

	public Worker(TableOperator test){
		this.test = test;
	}
	
	@Override
	public Long call() throws Exception {
		long begin = System.currentTimeMillis();
		try {
			//create some data by multiThread
			test.insert();
		} catch (Exception e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		return (end - begin);
	}
}
