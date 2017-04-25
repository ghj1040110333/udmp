package cn.com.git.udmp.test.commons.dubbox.rpc.support;

/**
   *	@author  liang
   *	@since 2016年2月2日
**/

public class DemoServiceImpl implements DemoService {

	@Override
	public String sayHello(String name) {
	    System.out.println("from dubbo client endpoint");
		return "Hello " + name;
	}

}
