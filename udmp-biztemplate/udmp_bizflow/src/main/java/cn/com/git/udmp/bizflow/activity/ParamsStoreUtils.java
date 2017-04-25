package cn.com.git.udmp.bizflow.activity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.com.git.udmp.bizflow.FlowMessage;
import cn.com.git.udmp.bizflow.FlowSession;
import cn.com.git.udmp.bizflow.data.DataObject;

/**
 * 参数保存工具类
 * <p>帮助活动把当前的参数保存在session中，方便其它活动使用
 * <p>start将保存输入参数，localService保存响应参数
 * <p>一个活动可以被多次调用，当活动被第二次调用时，保存的对象就会成为一个list
 * @author updated by Spring Cao
 *
 */
public class ParamsStoreUtils {
	public final static String ACTIVITY_PARAMS_STORE ="activityParamsStore";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void saveParam(String name,FlowMessage flowMessage){
		FlowSession session = flowMessage.getSession();
		DataObject ds = getStore(session);
		Object d = ds.get(name);
		if(d == null){
			getStore(session).set(name, flowMessage.getPayload());
		}else if(d instanceof Collection<?>){
			((Collection) d).add(flowMessage.getPayload());
		}else{
			List<Object> o = new ArrayList<Object>();
			o.add(d);
			o.add(flowMessage.getPayload());
			getStore(session).set(name, o);
		}
	}

	public static DataObject getStore(FlowSession session){
		DataObject paramsStore =   (DataObject) session.getProperty(ACTIVITY_PARAMS_STORE);
		if(paramsStore == null){
			synchronized (session) {
				paramsStore = (DataObject) session.getProperty(ACTIVITY_PARAMS_STORE);
				if(paramsStore == null){
					paramsStore = new DataObject();
					session.addProperty(ACTIVITY_PARAMS_STORE, paramsStore);
				}
			}
		}
		return paramsStore;
	}
}
