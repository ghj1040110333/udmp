package cn.com.git.trace.mock;

/**
 * 
 * @author guosg
 *
 */
public class DubboServiceServer implements DubboService{

	@Override
	public String sayHello(String name) {
		return "hello "+name;
	}

}
