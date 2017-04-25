package cn.com.git.udmp.common.utils;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.sun.mail.handlers.message_rfc822;

import cn.com.git.udmp.common.utils.ElUtils;
/**
 * 
 * @author guosg
 *
 */
public class TestElUtils {
	
	@Test
	public void test(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "value");
		
//		Message message = new Message();
//		message.setMessage("message");
//		map.put("msg", message);
		
		ElUtils el = ElUtils.getInstance(map);
		Assert.assertEquals("value", el.getValue("${name}")); 
		Assert.assertEquals("name", el.getValue("name")); 
		Assert.assertEquals("message", el.getValue("${msg.message}")); 
	}
}
