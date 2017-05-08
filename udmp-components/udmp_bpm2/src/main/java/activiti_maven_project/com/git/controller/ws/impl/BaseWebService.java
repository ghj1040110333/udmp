package activiti_maven_project.com.git.controller.ws.impl;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import activiti_maven_project.com.git.common.exception.BpmException;

public class BaseWebService {
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	protected Map<String,Object>  getInputDate(String _variablesJson,String requiredStr)throws BpmException{
		if(null==_variablesJson || "".equals(_variablesJson) ){
			throw new BpmException("启动流程方法传入参数为空！");
		}
		String[] rs=requiredStr.split(",");
		Map<String, Object> map=jackson2Map(_variablesJson);
		for(String r :rs){
			if(!map.containsKey(r)){
				throw new BpmException("传入参数异常，请确认必要变量:【"+requiredStr+"】已全部传递");
			}
		}
		return map;
	}
	
	protected Map<String,Object> jackson2Map(String _jackson) throws BpmException{
		 try {
			Map<String,Object> userData = mapper.readValue(_jackson, Map.class);
			return userData;
		} catch (JsonParseException e) {
			e.printStackTrace();
			throw new BpmException("["+_jackson+"] to Map is error!",e);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			throw new BpmException("["+_jackson+"] to Map is error!",e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new BpmException("["+_jackson+"] to Map is error!",e);
		}
	}
	
	protected String map2Jackson(Map<String,Object> _map) throws BpmException{
		try {
			String json=mapper.writeValueAsString(_map);
			return json;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new BpmException("["+_map+"] to json is error!",e);
		}
	}

	protected String object2Jackson(Object obj) throws BpmException{
		try {
			String json=mapper.writeValueAsString(obj);
			return json;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new BpmException("["+obj+"] to json is error!",e);
		}
	}
	
    public Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {    
        if (map == null)  
            return null;  
  
        Object obj = beanClass.newInstance();  
  
        org.apache.commons.beanutils.BeanUtils.populate(obj, map);  
  
        return obj;  
    }    
      
    public Map<?, ?> objectToMap(Object obj) {  
        if(obj == null)  
            return null;   
  
        return new org.apache.commons.beanutils.BeanMap(obj);  
    }   
}
