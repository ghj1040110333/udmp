package cn.git.flow.mapping.helper;

import cn.com.git.udmp.bizflow.data.DataObject;

public class CustomerBaseInfo extends DataObject{
	public String getEcifId(){
		return getString("ecifId");
	}
	public void setEcifId(String ecifId){
		setString("ecifId", ecifId);
	}
	
	public String getName() {
		return getString("name");
	}

	public void setName(String name) {
		setString("name", name);
	}
}
