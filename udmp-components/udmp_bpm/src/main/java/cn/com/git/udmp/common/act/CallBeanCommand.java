package cn.com.git.udmp.common.act;

import java.lang.reflect.Method;

import cn.com.git.udmp.common.utils.SpringContextHolder;
import cn.com.git.udmp.common.utils.StringUtils;

public class CallBeanCommand extends AbstractServiceTaskCommand{
	private static final String	BEAN_NAME = "beanName";
	private static final String	METHOD_NAME = "mName";
	public static final String PARAM_CLASS = "paramClass";
	private String beanName = null;
	private String methodName = null;
	private Object bean = null;
	private Method method = null;
	private Class<?> paramClazz = null;
	
	@Override
	public void init() throws Exception {
		super.init();
		if(StringUtils.isNotBlank(getMetaConfig().get(BEAN_NAME))){
			beanName = getMetaConfig().get(BEAN_NAME);
		}else{
			throw new Exception("调用本地节点需要配置"+ActServiceTaskExtension.META_PREFIX+BEAN_NAME);
		}
		
		if(StringUtils.isNotBlank(getMetaConfig().get(METHOD_NAME))){
			methodName = getMetaConfig().get(METHOD_NAME);
		}else{
			throw new Exception("调用本地节点需要配置"+ActServiceTaskExtension.META_PREFIX+METHOD_NAME);
		}
		
		bean = SpringContextHolder.getApplicationContext().getBean(beanName);
		if(bean == null){
			throw new Exception("spring的上下文中没有"+BEAN_NAME+"的配置");
		}
		
		Class<?> clazz = bean.getClass();
		
		String className = getMetaConfig().get(PARAM_CLASS);
		if(StringUtils.isNotBlank(className)){
			try{
				paramClazz = Class.forName(className);
			}catch(Exception ex){
				throw new Exception("无法找到类"+className,ex);
			}
			if(paramClazz !=null){
				method = clazz.getMethod(methodName, paramClazz);
			}
		}
		
		
		if(method == null){
			for(Method method: clazz.getMethods()){
				if(method.getName().equals(methodName)){
					this.method = method;
				}
			}
		}
		
		if(this.method == null){
			throw new Exception("spring的Bean "+BEAN_NAME+"没有"+methodName+"的方法");
		}
		if(StringUtils.isBlank(className)){
			Class<?>[] paramsClazzes = method.getParameterTypes();
			if(paramsClazzes.length>1){
				throw new Exception("不支持多参数的方法，spring的Bean "+BEAN_NAME+"方法 "+methodName);
			}else if(paramsClazzes.length == 1){
				paramClazz = paramsClazzes[0];
			}
		}
	}

	@Override
	public Object createReqeustObject() throws Exception {
		if(paramClazz!=null){
			try{
			return paramClazz.newInstance();
			}catch(Exception e){
				throw new Exception("无法实例化内"+paramClazz,e);
			}
		}
		return null;
	}

	@Override
	public Object doInvok(Object params) throws Exception {
		if(params!=null)
			return method.invoke(bean, params);
		else
			return method.invoke(bean);
	}

}
