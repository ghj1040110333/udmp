package cn.com.git.udmp.common.act;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.kie.api.KieServices;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import cn.com.git.udmp.common.utils.StringUtils;
/**
 * 调用本地规则
 * @author guosg
 *
 */
public class DroolsCommand extends AbstractServiceTaskCommand{
	public static final String SESSION_NAME="sessionName";
	public static final String PARAM_CLASS = "paramClass";
	private Class<?> reqeustClazz = null;
	private static final Lock lock = new ReentrantLock();
	private static KieContainer kc = null;
	private String sessionName = null;
	
	@Override
	public void init() throws Exception {
		super.init();
		String cl = getMetaConfig().get(PARAM_CLASS);
		reqeustClazz = Class.forName(cl);
		if(StringUtils.isNotBlank(getMetaConfig().get(SESSION_NAME))){
			lock.lock();
			try{
		        KieServices ks = KieServices.Factory.get();
		        kc = ks.getKieClasspathContainer();
			}finally{
				lock.unlock();
			}
		}else{
			throw new Exception("本地规格引擎需要配置"+ActServiceTaskExtension.META_PREFIX+reqeustClazz);
		}
		if(StringUtils.isNotBlank(getMetaConfig().get(SESSION_NAME))){
			sessionName = getMetaConfig().get(SESSION_NAME);
		}else{
			throw new Exception("本地规格引擎需要配置"+ActServiceTaskExtension.META_PREFIX+SESSION_NAME);
		}
		
	}
	
	@Override
	public Object createReqeustObject() throws Exception{
		return reqeustClazz.newInstance();
	}
	@Override
	public Object doInvok(Object params) throws Exception {
		try{
			KieSession ksession = kc.newKieSession(sessionName);
			ksession.insert(params);
			ksession.fireAllRules();
			ksession.destroy();
		}catch(Exception ex){
			throw new Exception("规则"+sessionName+"计算出现异常",ex);
		}
		return params;
	}
	


}
