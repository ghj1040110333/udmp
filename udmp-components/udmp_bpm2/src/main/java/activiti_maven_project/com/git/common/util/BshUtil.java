package activiti_maven_project.com.git.common.util;

import java.util.HashMap;
import java.util.Map;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import activiti_maven_project.com.git.common.exception.BpmException;
import de.odysseus.el.ExpressionFactoryImpl;
import de.odysseus.el.util.SimpleContext;
import de.odysseus.el.util.SimpleResolver;

public class BshUtil {
	static Logger logger = LogManager.getLogger();
	
	
	public static boolean eval(String condition,Map<String,Object> variables)throws BpmException{
		try{
			logger.debug("condition: [{}]",condition);
			boolean flag=false;
			ExpressionFactory factory = new ExpressionFactoryImpl();
			SimpleContext context = new SimpleContext(new SimpleResolver());
			for(String key:variables.keySet()){
				if(condition.indexOf(key)>0){
					logger.debug("{} setValue : {}'s {}",condition,key, variables.get(key));
					factory.createValueExpression(context, "#{"+key+"}", String.class).setValue(context, variables.get(key));
				}
			}
			
			ValueExpression expr1 = factory.createValueExpression(context, condition, boolean.class);
			flag=(boolean) expr1.getValue(context);
			logger.debug("flag: [{}]",flag);
			return flag;
		}catch(Exception e){
			throw new BpmException("",e);
		}
	}
	
	
	
	
	
	
//	public static boolean eval2(String condition,Map<String,Object> variables) throws BpmException{
//		Interpreter interpreter = new Interpreter();  
//		boolean flag=false;
//		condition=condition.replace("${", "(");
//		condition=condition.substring(0, condition.indexOf("}"));
//		condition=condition+")";
//		String s = "${userName==\"wzy\"}";
//		s=s.replace("${", "(");
//		s=s.substring(0, s.indexOf("}"));
//		s=s+")";		
//		
//		logger.debug("s: [{}]",s);
//		logger.debug("condition: [{}]",condition);
//		logger.debug(condition.equals(s));
//		try{
//			for(String key:variables.keySet()){
//				logger.debug("key:["+key+"] value:["+variables.get(key)+"]");
//				if(key.equals("userName")){
//					logger.debug("{} indexof {} :{}",condition,key,condition.indexOf(key));
//					String var="\""+(String)variables.get(key)+"\"";
//					logger.debug("var:{}",var); 
//					String code=getEncoding(var);
//					logger.debug("var.code:{}",code); 
//					interpreter.set(key, var);
////					condition=condition.replace(key, "\""+variables.get(key).toString()+"\"");
////					logger.debug("condition: [{}]",condition);
//				}
//			}
//			flag=(boolean)interpreter.eval(condition);
//			boolean flag2=(boolean)interpreter.eval(s);
//			logger.debug("string.code:{}",getEncoding("wzy")); 
//			logger.debug("condition.code:{}",getEncoding(condition)); 
//			interpreter.set("userName", "wzy");
//			boolean flag3=(boolean)interpreter.eval(condition);
//			
//			logger.debug("flag: [{}]",flag);
//			logger.debug("flag2: [{}]",flag2);
//			logger.debug("flag3: [{}]",flag3);
//		}catch(EvalError e){
//			throw new BpmException("BshUtil.eval["+condition+"] variables["+variables+"] is error !");
//		}catch(Exception e){
//			throw new BpmException("BshUtil.eval["+condition+"] variables["+variables+"] is error !");
//		}
//		return flag;
//	}
	
	
//	public static void main(String[] str) throws BpmException, JsonProcessingException, EvalError {
//		String s = "${userName==\"wzy\"}";
//		Map<String,Object> map=new HashMap<String,Object>();
//		map.put("userName", "wzy");
//		map.put("age", 1);
//		map.put("userId", "admin001");
//		map.put("creater", "admin001");
//		boolean f=BshUtil.eval(s, map);
//		System.out.println(f);
//	}
	

	 public static String getEncoding(String str) {    
         String encode = "GB2312";    
        try {    
            if (str.equals(new String(str.getBytes(encode), encode))) {    
                 String s = encode;    
                return s;    
             }    
         } catch (Exception exception) {    
         }    
         encode = "ISO-8859-1";    
        try {    
            if (str.equals(new String(str.getBytes(encode), encode))) {    
                 String s1 = encode;    
                return s1;    
             }    
         } catch (Exception exception1) {    
         }    
         encode = "UTF-8";    
        try {    
            if (str.equals(new String(str.getBytes(encode), encode))) {    
                 String s2 = encode;    
                return s2;    
             }    
         } catch (Exception exception2) {    
         }    
         encode = "GBK";    
        try {    
            if (str.equals(new String(str.getBytes(encode), encode))) {    
                 String s3 = encode;    
                return s3;    
             }    
         } catch (Exception exception3) {    
         }    
        return "";    
     }    


}
