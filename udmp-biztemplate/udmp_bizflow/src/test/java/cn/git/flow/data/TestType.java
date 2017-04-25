package cn.git.flow.data;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;

import cn.com.git.udmp.bizflow.data.Property;
import cn.com.git.udmp.bizflow.data.Type;
import cn.com.git.udmp.bizflow.data.TypeBuidler;
import junit.framework.TestCase;

public class TestType extends TestCase{
	
	public void test() throws IOException{
		ClassPathResource resource = new ClassPathResource("helper/testType.xml",this.getClass());
		TypeBuidler tb = new TypeBuidler();
		Type type = tb.build(resource.getInputStream()).getProperty("request").getType();
		assertEquals(3, type.getProperties().size());
		
		Property ecifProperty = type.getProperty("ecif");
		assertEquals("String", ecifProperty.getType().getName()) ;
		
		assertEquals("AddressInfo", type.getProperty("addressInfos").getType().getName());
	}
}
