package cn.com.git.udmp.test.commons.dubbox.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import cn.com.git.udmp.test.commons.dubbox.rest.support.User;


/**
   *	@author  liang
   *	@since 2016年2月2日
**/
public class DubboxRestClientTest {
    
    @Test
    public void test() throws Exception{
    	User user = new User();
    	user.setName("liang");

    	Client client = ClientBuilder.newClient();
    	WebTarget target = client.target("http://localhost:8080/users/register");
    	Response response = target.request().post(Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));

    	try {
    	    if (response.getStatus() != 200) {
    	        throw new RuntimeException("Failed with HTTP error code : " + response.getStatus());
    	    }
    	} finally {
    	    response.close();
    	    client.close(); // 在真正开发中不要每次关闭client，比如HTTP长连接是由client持有的
    	}
    }
}
