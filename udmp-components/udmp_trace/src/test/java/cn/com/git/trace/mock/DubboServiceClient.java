package cn.com.git.trace.mock;

/**
 * 
 * @author guosg
 *
 */
public class DubboServiceClient {
	
	public DubboService dubboService;
	
	public String askServer(String name){
		return dubboService.sayHello(name);
	}

	public DubboService getDubboService() {
		return dubboService;
	}

	public void setDubboService(DubboService dubboService) {
		this.dubboService = dubboService;
	}
	
	
	
}
