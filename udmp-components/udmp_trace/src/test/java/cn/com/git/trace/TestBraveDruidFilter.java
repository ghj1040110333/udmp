package cn.com.git.trace;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class TestBraveDruidFilter {
	ApplicationContext applicationContext;
	JdbcTemplate jdbcTemplate;
	
	
	@Before
	public void befor(){
		applicationContext = new ClassPathXmlApplicationContext(new String[] {"spring-context-brave.xml"});
		jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
		
		try{
			jdbcTemplate.execute("DROP TABLE sys_user;");
		}catch(Exception ex){}
		
		jdbcTemplate.execute("CREATE TABLE sys_user(NAME VARCHAR);");
	}
	
	@Test
	public void test(){
		jdbcTemplate.execute("INSERT INTO sys_user ('NAME') VALUES ('hello')");
	}
}
