package cn.git.ecif.flow;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import cn.git.ecif.flow.user.ucc.UserUCC;
import cn.git.ecif.flow.user.vo.CreateUserRequesterBO;
import cn.git.ecif.flow.user.vo.CreateUserRequesterVO;
import cn.git.ecif.flow.user.vo.CreateUserResponserVO;

import junit.framework.TestCase;

public class TestUserUCC extends TestCase{

	public void testCreate() throws Exception{
//		BeanFactory beanFactory =new XmlBeanFactory(new ClassPathResource("applicationContext.xml",this.getClass()));
//		UserUCC ucc = (UserUCC) beanFactory.getBean("userUCC");
//
//		//用户不存在，需要创建
//		CreateUserRequesterVO vo = new CreateUserRequesterVO();
//		vo.setName("test");
//		vo.setAddress("AAA");
//		CreateUserResponserVO rvo =  ucc.create(vo);
//		assertEquals("bbbb", rvo.getEcifId());
//
//		//用户存在，直接返回
//		vo = new CreateUserRequesterVO();
//		vo.setName("xaomin");
//		vo.setAddress("AAA");
//		rvo =  ucc.create(vo);
//		assertEquals("aaaaa", rvo.getEcifId());
	}

	public void testBeanUtils() throws IllegalAccessException, InvocationTargetException{
		CreateUserRequesterVO vo = new CreateUserRequesterVO();
		vo.setName("est");
		CreateUserRequesterBO vo2 = new CreateUserRequesterBO();
		BeanUtils.copyProperties(vo2, vo);
		System.out.println(vo2.getName());
		assertEquals("est", vo2.getName());
	}
}
