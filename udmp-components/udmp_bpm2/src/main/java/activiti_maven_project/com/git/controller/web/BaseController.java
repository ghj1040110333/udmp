package activiti_maven_project.com.git.controller.web;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ActivitiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import activiti_maven_project.com.git.common.exception.BpmException;
import activiti_maven_project.com.git.common.util.JsonUtil;
import activiti_maven_project.com.git.common.vo.ResultVO;
import activiti_maven_project.com.git.user.Users;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@SessionAttributes("user") 
public abstract class BaseController {
	
	@Value("${productName}")
	private String productName;
	
	
	
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	

	@ExceptionHandler({BpmException.class})
    public Object exception(HttpServletResponse response, Exception ex) {  
          
	    ResultVO vo = new ResultVO();
	    vo.setMessage(ex.getMessage());
	    vo.setErrorType(ResultVO.ERROR_TYPE_BUSINESS);
	    
	    try {
			return this.renderString(response,vo);
		} catch (BpmException e) {
			e.printStackTrace();
			return null;
		}
    } 
	
	@ExceptionHandler({ActivitiException.class})
	public Object activitiException(HttpServletResponse response, Exception ex) {
//		ex.printStackTrace();
//		return "error/500";
		
		String rootCauseMessage = org.apache.commons.lang.exception.ExceptionUtils.getRootCause(ex).getMessage();
				
		ResultVO vo = new ResultVO();
	    vo.setMessage(rootCauseMessage);
	    vo.setErrorType(ResultVO.ERROR_TYPE_BUSINESS);
	    
	    try {
			return this.renderString(response,vo);
		} catch (BpmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 全局绑定异常
	 */
	@ExceptionHandler({Exception.class})
	public Object wholeException(HttpServletResponse response, Exception ex) {
//		ex.printStackTrace();
//		return "error/500";
		
//		String rootCauseMessage = org.apache.commons.lang.exception.ExceptionUtils.getRootCauseMessage(ex);
				
	    ResultVO vo = new ResultVO();
	    vo.setMessage(ex.getMessage());
	    vo.setErrorType(ResultVO.ERROR_TYPE_BUSINESS);
	    
	    try {
			return this.renderString(response,vo);
		} catch (BpmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	


	
	/**
	 * 客户端返回字符串
	 * @param response
	 * @param string
	 * @return
	 */
	protected String renderString(HttpServletResponse response, String string, String type) {
		try {
			response.reset();
	        response.setContentType(type);
	        response.setCharacterEncoding("utf-8");
			response.getWriter().print(string);
			return null;
		} catch (IOException e) {
			return null;
		}
	}
	
	
	/**
	 * 客户端返回JSON字符串
	 * @param response
	 * @param object
	 * @return
	 * @throws BpmException 
	 */
	protected String renderString(HttpServletResponse response, Object object) throws BpmException {
		try {
			String re=renderString(response, this.toJsonString(object), "application/json");
			return re;
		} catch (JsonProcessingException e) {
			throw new BpmException(e.getMessage());
		}
	}
	
	private String toJsonString(Object object) throws JsonProcessingException{
		return new ObjectMapper().writeValueAsString(object);
	}
	
	/**
	 * 对象转为map
	 * @param obj
	 * @return
	 */
	protected Map<String, Object> object2map(Object obj){
		Map<String, Object> map=new HashMap<String, Object>();
		
		JSONObject json = JSONObject.fromObject(obj);
		JsonUtil.JsonToHashMap(json, map);
		return map;
	}
	
	protected Map<String, Object> str2mapSimple(String str) throws BpmException{
		ObjectMapper objectMapper = new ObjectMapper();  
		try {
			Map<String, Object> map = objectMapper.readValue(str, Map.class);
			return map;
		} catch (JsonParseException e) {
			throw new BpmException(e.getMessage());
		} catch (JsonMappingException e) {
			throw new BpmException(e.getMessage());
		} catch (IOException e) {
			throw new BpmException(e.getMessage());
		}  
	}
	
	protected Map<String, Object> str2map(String str){
		Map<String, Object> map=new HashMap<String, Object>();
		
		String[] tmp=str.split("&");
		for(String tt:tmp){
			String[] tmp2=tt.split("=");
			if(tmp2.length>1){
				if(tmp2[0].indexOf(".string")>0){
					map.put(tmp2[0].substring(0, tmp2[0].indexOf(".string")), tmp2[1]);
				}else if(tmp2[0].indexOf(".double")>0){
					map.put(tmp2[0].substring(0, tmp2[0].indexOf(".double")), new Double(tmp2[1]).doubleValue());
				}else if(tmp2[0].indexOf(".long")>0){
					map.put(tmp2[0].substring(0, tmp2[0].indexOf(".long")), new Long(tmp2[1]).longValue());
				}else if(tmp2[0].indexOf(".date")>0){
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					try {
						Date date = format.parse(tmp2[1].toString());
						map.put(tmp2[0].substring(0, tmp2[0].indexOf(".date")),date);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					
				}else{
					map.put(tmp2[0], tmp2[1]);
				}
			}
		}
		return map;
	}
	
	
	protected boolean isNumeric(String str){ 
		   Pattern pattern = Pattern.compile("[0-9]*"); 
		   Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false; 
		   } 
		   return true; 
		}
}
