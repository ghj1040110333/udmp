package cn.com.git.udmp.bizflow.activity;


import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.beanutils.MethodUtils;
import org.springframework.cglib.core.ReflectUtils;

import cn.com.git.udmp.bizflow.FlowMessage;
import cn.com.git.udmp.bizflow.data.RequestDataObject;
import cn.com.git.udmp.bizflow.data.ResponseDataObject;
import cn.com.git.udmp.bizflow.lifecycle.Initialisable;
/***
 * 调用本地方法活动
 * <p>此活动需要与报文映射动作相配合使用，如果此活动没有配置连线，
 * 那么就需要进行返回报文映射动作
 * @author updated by Spring Cao
 *
 */
public class LocalService extends Activity implements Initialisable{
	/**spring bean的名称*/
	private String beanName = null;
	/**spring bean的方法名称*/
	private String method = null;
	private Object bean = null;

	@Override
	protected FlowMessage doProcess(FlowMessage flowMessage) throws Exception {
		Object result = null;
		Object[] params =  null;
		if(flowMessage.getPayload() instanceof RequestDataObject){
			RequestDataObject r = (RequestDataObject) flowMessage.getPayload();
			Collection<?> l = r.getValues();
			params = new Object[l.size()];
			int i=0;
			for(Object o : l){
				params[i] = o;
				i++;
			}
		}else{
			params = new Object[]{flowMessage.getPayload()};
		}
		try {
			result = MethodUtils.invokeMethod(bean, method, params);
		} catch (NoSuchMethodException e) {
			throw e;
		} catch (IllegalAccessException e) {
			throw e;
		} catch (InvocationTargetException e) {
			if(e.getTargetException()!=null &&  e.getTargetException() instanceof Exception){
				throw (Exception)e.getTargetException();
			}else{
				throw e;
			}
		}
		flowMessage.setPayload(result);
		ParamsStoreUtils.saveParam(getName(), flowMessage);
		return flowMessage;
	}

	@Override
	public FlowMessage afterProcess(FlowMessage flowMessage) throws Exception {
		flowMessage = super.afterProcess(flowMessage);
		Object result = flowMessage.getPayload();
		if(result instanceof ResponseDataObject){
			ResponseDataObject r = (ResponseDataObject) result;
			Iterator<?> it = r.getValues().iterator();
			if(it.hasNext()){
				flowMessage.setPayload(it.next());
			}
		}

		if(getConnections()!=null && getConnections().size()>0){
			flowMessage = getConnections().get(0).process(flowMessage);
		}

		return flowMessage;
	}


	public String getBeanName() {
		return beanName;
	}
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	@Override
	public void initialise() {
		super.initialise();
		if(beanName == null || method == null)
			throw new RuntimeException("beanName或method不能为空");
		bean = getFlowScope().getFlowContext().getBeanFactory().getBean(beanName);
	}
}