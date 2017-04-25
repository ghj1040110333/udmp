package cn.com.git.udmp.common.web;

import java.util.Map;

public class CacheData {

	private String defaultPassword;
	private Map<String,Map<String,Object>> paramTypMap;
	
	public CacheData() {
	}
	
	public CacheData(Map<String, Map<String, Object>> paramTypMap) {
		super();
		this.paramTypMap = paramTypMap;
	}

	public String getDefaultPassword() {
		return defaultPassword;
	}
	public void setDefaultPassword(String defaultPassword) {
		this.defaultPassword = defaultPassword;
	}
	public Map<String, Map<String, Object>> getParamTypMap() {
		return paramTypMap;
	}
	public void setParamTypMap(Map<String, Map<String, Object>> paramTypMap) {
		this.paramTypMap = paramTypMap;
	}
	
	
}
