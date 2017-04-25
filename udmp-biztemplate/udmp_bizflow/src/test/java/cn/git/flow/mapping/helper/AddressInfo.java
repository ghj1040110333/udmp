package cn.git.flow.mapping.helper;

import cn.com.git.udmp.bizflow.data.DataObject;

public class AddressInfo extends DataObject{

	public String getAddress() {
		return getString("address");
	}

	public void setAddress(String address) {
		setString("address", address);
	}
	
	public String getEcifId(){
		return getString("ecifId");
	}
	public void setEcifId(String ecifId){
		setString("ecifId", ecifId);
	}
}
