package cn.git.flow.mapping.helper;

import java.util.List;

import cn.com.git.udmp.bizflow.data.DataObject;

public class CustomerInfo extends DataObject{
	public String getEcifId(){
		return getString("ecifId");
	}
	public void setEcifId(String ecifId){
		setString("ecifId", ecifId);
	}

	public CustomerBaseInfo getCustomerBaseInfo() {
		return (CustomerBaseInfo) get("customerBaseInfo") ;
	}

	public void setCustomerBaseInfo(CustomerBaseInfo customerBaseInfo) {
		set("customerBaseInfo", customerBaseInfo);
	}

	@SuppressWarnings("unchecked")
	public List<AddressInfo> getAddressInfos() {
		return (List<AddressInfo>) get("addressInfos");
	}

	public void setAddressInfo(List<AddressInfo> addressInfos) {
		set("addressInfos", addressInfos);
	}
	
	
}
