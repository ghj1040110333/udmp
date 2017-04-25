package cn.com.git.udmp.test.commons.dubbox.rest.support;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("users")
public class UserServiceImpl implements UserService{

	@POST
	@Path("register")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Override
	public void registerUser(User user) {
		System.out.println("registed");
	}
	
	@GET
    @Path("show")
	@Override
	public String show(){
	    System.out.println(">>>>>>>>>>>>>>>>>>this is rest show<<<<<<<<<<<<<<<<<<<<");
	    return "Hello,World!";
	}
}
