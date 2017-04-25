package cn.git.ecif.flow.user.vo;

import cn.com.git.udmp.bizflow.data.DataObject;

public class CreateUserRequesterVO extends DataObject{
	
	public String getName() {
		return getString("name");
	}
	public void setName(String name) {
		setString("name", name);
	}
	public String getAddress() {
		return getString("address");
	}
	public void setAddress(String address) {
		setString("address", address);
	}
	
}
