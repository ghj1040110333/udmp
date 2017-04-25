package cn.com.git.udmp.common.act;

import java.util.Map;

import org.activiti.engine.impl.bpmn.parser.FieldDeclaration;

/**
 * 
 * @author guosg
 *
 */
public interface ActServiceTaskCommand {
	
	/**
	 * 
	 * @param params
	 * @throws Exception 
	 */
	public Object invok(Object params) throws Exception;
	
	/**
	 * @throws Exception 
	 * 
	 */
	public void init() throws Exception;
	
	
	public Object createReqeustObject() throws Exception;
	


	void setAllConfig(Map<String, FieldDeclaration> allConfig);


	void setRequestConfig(Map<String, String> requestConfig);


	void setResponseConfig(Map<String, String> responseConfig);


	void setMetaConfig(Map<String, String> metaConfig);

}
