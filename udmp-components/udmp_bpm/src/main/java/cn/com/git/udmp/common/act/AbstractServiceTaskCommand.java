package cn.com.git.udmp.common.act;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.impl.bpmn.parser.FieldDeclaration;

public abstract class  AbstractServiceTaskCommand implements ActServiceTaskCommand {
	Map<String,FieldDeclaration> allConfig = new HashMap<String,FieldDeclaration>();
	
	Map<String,String> requestConfig = new HashMap<String,String>();
	
	Map<String,String> responseConfig = new HashMap<String,String>();
	
	Map<String,String> metaConfig = new HashMap<String,String>();
	
	@Override
	public Object invok(Object params) throws Exception {
		return doInvok(params);
	}
	
	@Override
	public void init() throws Exception {
		
	}
	
	public abstract Object doInvok(Object params) throws Exception;
	

	public Map<String, FieldDeclaration> getAllConfig() {
		return allConfig;
	}

	
	@Override
	public void setAllConfig(Map<String, FieldDeclaration> allConfig) {
		this.allConfig = allConfig;
	}

	
	public Map<String, String> getRequestConfig() {
		return requestConfig;
	}

	
	@Override
	public void setRequestConfig(Map<String, String> requestConfig) {
		this.requestConfig = requestConfig;
	}

	
	public Map<String, String> getResponseConfig() {
		return responseConfig;
	}

	
	@Override
	public void setResponseConfig(Map<String, String> responseConfig) {
		this.responseConfig = responseConfig;
	}

	
	public Map<String, String> getMetaConfig() {
		return metaConfig;
	}

	
	@Override
	public void setMetaConfig(Map<String, String> metaConfig) {
		this.metaConfig = metaConfig;
	}
	
	
}
