package cn.git.ecif.flow.user.vo;

import cn.com.git.udmp.bizflow.data.DataObject;

public class CheckUserResponseVO extends DataObject{
	
	public boolean isExist() {
		return getBoolean("exist");
	}
	public void setExist(boolean exist) {
		setBoolean("exist", exist);
	}
	public String getEcifId() {
		return getString("ecifId");
	}
	public void setEcifId(String ecifId) {
		setString("ecifId", ecifId);
	}
	
	
}
