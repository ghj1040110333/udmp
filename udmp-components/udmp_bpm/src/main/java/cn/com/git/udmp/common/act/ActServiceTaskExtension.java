package cn.com.git.udmp.common.act;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.impl.bpmn.parser.FieldDeclaration;
import org.activiti.engine.impl.el.FixedValue;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.git.udmp.common.utils.ElUtils;
import cn.com.git.udmp.common.utils.StringUtils;
/**
 * 
 * @author guosg
 *
 */
public class ActServiceTaskExtension implements JavaDelegate,ActParamAddExtension{
	public static final Logger logger = LoggerFactory.getLogger(ActServiceTaskExtension.class);
	public static final String REQUEST_PREFIX = "r_";
	public static final String RESPONSE_PREFIX = "p_";
	public static final String META_PREFIX = "m_";
	public static final String TYPE="type";
	public static final String ALL_RESPONSE="allResponse";
	public static final String ALL_REQUEST="allRequest";
	
	private Map<String, Class<? extends ActServiceTaskCommand>> actServiceTaskCommand  = new HashMap<String, Class<? extends ActServiceTaskCommand>>();
	
	private Map<String,FieldDeclaration> allConfig = new HashMap<String,FieldDeclaration>();
	
	private Map<String,String> requestConfig = new HashMap<String,String>();
	
	private Map<String,String> responseConfig = new HashMap<String,String>();
	
	private Map<String,String> metaConfig = new HashMap<String,String>();
	
	private ActServiceTaskCommand command = null;
	
	private boolean allResponse = false;
	
	private boolean allRequest = false;
	
	private String type = null;
	private final Lock lock = new ReentrantLock();

	
	public ActServiceTaskExtension(){
		actServiceTaskCommand.put("localDrools", DroolsCommand.class);
		actServiceTaskCommand.put("bean", CallBeanCommand.class);
	}
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.putAll(execution.getVariables());
		paramsMap.putAll(execution.getVariablesLocal());
		paramsMap.put("businessKey", execution.getProcessBusinessKey());
		
		ElUtils elUtils = ElUtils.getInstance(paramsMap);
		Map<String, Object> rmap = new HashMap<String, Object>();
		for(Entry<String, String> entry : requestConfig.entrySet()){
			if(logger.isDebugEnabled())
				logger.debug("name: "+entry.getKey()+";value:"+entry.getValue()+";value2："+elUtils.getValue(entry.getValue()));
			rmap.put(entry.getKey(), elUtils.getValue(entry.getValue()));
		}
		
		Object ro = getCommand().createReqeustObject();
		if(ro != null){
			if(isAtomicObject(ro)){
				ro = rmap.get("default");
			}else if(ro instanceof Map){
				ro = rmap;
			} else if(!allRequest){
				BeanUtils.copyProperties(ro, rmap);
			} else{
				BeanUtils.copyProperties(ro, paramsMap);
			}
		}
		
		Object po = getCommand().invok(ro);
		if(po !=null){
			Map<String, Object> pmap = transBean2Map(po);
			elUtils.setValues(pmap);
			if(isAtomicObject(po) && responseConfig.size() ==1){
				Entry<String, String> entry = responseConfig.entrySet().iterator().next();
				execution.setVariable(entry.getKey(),po);
			}else if(!allResponse){
				for(Entry<String, String> entry : responseConfig.entrySet()){
					if(logger.isDebugEnabled())
						logger.debug("name: "+entry.getKey()+";value:"+entry.getValue()+";value2："+elUtils.getValue(entry.getValue()));
					execution.setVariable(entry.getKey(),elUtils.getValue(entry.getValue()));
				}
			}else{
				for(  Entry<String, Object> entry : pmap.entrySet()){
					if(isAtomicObject(entry.getValue())){
						if(logger.isDebugEnabled())
							logger.debug("name: "+entry.getKey()+";value:"+entry.getValue());
						execution.setVariable(entry.getKey(), entry.getValue());
					}else{
						if(logger.isDebugEnabled())
							logger.debug("不是内置的类型：name: "+entry.getKey()+";value:"+(entry.getValue()!=null?entry.getValue().getClass():null));
					}
				}
			}
		}
	}
	
	private boolean isAtomicObject(Object obj){
		return 	(obj == null) 
				|| (obj instanceof String) 
				||  (obj instanceof Integer)
				||  (obj instanceof Double)
				||  (obj instanceof Boolean)
				||  (obj instanceof BigDecimal)
				||  (obj instanceof Date);
	}
	

	@Override
	public void add(FieldDeclaration declaration) {
		allConfig.put(declaration.getName(),declaration);
		FixedValue valueEl = (FixedValue) declaration.getValue();
		
		String name = declaration.getName();
		if(name.indexOf(REQUEST_PREFIX)>-1){
			requestConfig.put(getName(name, REQUEST_PREFIX),valueEl.getExpressionText() );
		}else if(name.lastIndexOf(RESPONSE_PREFIX)>-1){
			responseConfig.put(getName(name, RESPONSE_PREFIX),valueEl.getExpressionText());
		}else if(name.lastIndexOf(META_PREFIX)>-1){
			metaConfig.put(getName(name, META_PREFIX),valueEl.getExpressionText());
		}else if(TYPE.equals(name)){
			type = valueEl.getExpressionText();
		}else if(ALL_RESPONSE.equals(name)){
			allResponse = (valueEl.getExpressionText()!=null && valueEl.getExpressionText().toLowerCase().equals("true"))?true:false;
		}else if(ALL_REQUEST.equals(name)){
			allRequest = (valueEl.getExpressionText()!=null && valueEl.getExpressionText().toLowerCase().equals("true"))?true:false;
		}
		
	}
	
	private String getName(String name ,String refix){
		return name.substring(refix.length());
	}
	
	protected ActServiceTaskCommand getCommand() throws Exception{
		if(command == null){
			lock.lock();
			try{
				if(command ==null){
					if(StringUtils.isNotBlank(type)){
						 Class<? extends ActServiceTaskCommand> clazz =actServiceTaskCommand.get(type); 
						 if(clazz != null){
							command = clazz.newInstance();
							command.setAllConfig(allConfig);
							command.setMetaConfig(metaConfig);
							command.setResponseConfig(responseConfig);
							command.init();
						 }else 
							 throw new Exception("service task不支持"+type+"的type");
					}else{
						throw new Exception("service task必须要有一个type的配置");
					}
				}
			}finally{
				lock.unlock();
			}
		}
		return command;
	}
	
	// Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map  
    public static Map<String, Object> transBean2Map(Object obj) {  
  
        if(obj == null){  
            return null;  
        }          
        Map<String, Object> map = new HashMap<String, Object>();  
        try {  
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (PropertyDescriptor property : propertyDescriptors) {  
                String key = property.getName();  
  
                // 过滤class属性  
                if (!key.equals("class")) {  
                    // 得到property对应的getter方法  
                    Method getter = property.getReadMethod();  
                    Object value = getter.invoke(obj);  
  
                    map.put(key, value);  
                }  
  
            }  
        } catch (Exception e) {  
            logger.error(e.getMessage(),e); 
        }  
  
        return map;  
  
    }  
	
	
}
