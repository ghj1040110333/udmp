package cn.git.ecif.flow.user.vo;

import cn.com.git.udmp.bizflow.data.DataObject;

public class CreateUserResponserVO extends DataObject{
	public String getEcifId() {
		return getString("ecifId");
	}
	public void setEcifId(String ecifId) {
		setString("ecifId", ecifId);
	}
}
