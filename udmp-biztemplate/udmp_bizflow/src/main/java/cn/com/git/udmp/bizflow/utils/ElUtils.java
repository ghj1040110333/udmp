package cn.com.git.udmp.bizflow.utils;

import java.util.Map.Entry;

import javax.el.ELContext;
import javax.el.ExpressionFactory;

import cn.com.git.udmp.bizflow.FlowMessage;
import cn.com.git.udmp.bizflow.FlowSession;
import cn.com.git.udmp.bizflow.activity.ParamsStoreUtils;
import cn.com.git.udmp.bizflow.data.DataObject;
import de.odysseus.el.ExpressionFactoryImpl;
import de.odysseus.el.util.SimpleContext;
/**
 * EL表达式的工具方法
 *
 * @author updated by Spring Cao
 *
 */
public class ElUtils {
	public final static String EL_HELPER = "elUtils.elHelper";

	public static Object getValue(String expression,FlowMessage msg){
		return curElHelper(msg.getSession()).getValue(expression,msg);
	}

	public static void setValue(String name,Object obj,FlowMessage msg){
		curElHelper(msg.getSession()).setValue(name, obj);
	}

	private static ElHelper curElHelper(FlowSession session){
		ElHelper helper = (ElHelper) session.getProperty(EL_HELPER);
		if(helper == null){
			synchronized (session) {
				helper = (ElHelper) session.getProperty(EL_HELPER);
				if(helper == null){
					helper = new ElHelper(session);
				}
			}
		}
		return helper;
	}

	public static class ElHelper{
		ExpressionFactory f = new ExpressionFactoryImpl();

		FlowSession session = null;

		ELContext context = new SimpleContext();

		public  ElHelper(FlowSession session){
			this.session = session;
			this.session.addProperty(EL_HELPER, this);
		}

		public Object getValue(String expression,FlowMessage msg) {

			context.getELResolver().setValue(context, null, "session", session);
			DataObject obj = ParamsStoreUtils.getStore(msg.getSession());
			for(Entry<String,Object> entry : obj.getData().entrySet()){
				context.getELResolver().setValue(context, null, entry.getKey(), entry.getValue());
			}
			return f.createValueExpression(context, expression, Object.class).getValue(context);
		}

		public void setValue(String name,Object obj){
			context.getELResolver().setValue(context, null, name, obj);
		}
	}
}
