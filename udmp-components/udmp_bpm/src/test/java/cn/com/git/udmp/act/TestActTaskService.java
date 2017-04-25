package cn.com.git.udmp.act;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import cn.com.git.udmp.common.utils.ElUtils;

/**
 * 任务服务
 * @author guosg
 *
 */
public class TestActTaskService {
	
	
	@Test
	public void testFormkeyElExpress(){
		Map<String, Object> data = new HashMap<String,Object>();
		data.put("userId", "userId2");
		data.put("customer", "customer2");
		ElUtils utils = ElUtils.getInstance(data);
		String s = (String) utils.getValue("/fes/test");
		Assert.assertEquals("/fes/test", s);
		
		s = (String) utils.getValue("/fes/test?customer=${customer}&userId=${userId}");
		Assert.assertEquals("/fes/test?customer=customer2&userId=userId2", s);
	}
	
}
