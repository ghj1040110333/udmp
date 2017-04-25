package cn.com.git.udmp.bizflow.mapping;

import cn.com.git.udmp.bizflow.FlowSession;
import cn.com.git.udmp.bizflow.data.DataObject;

/**
 * 流程内值对象
 * @author updated by Spring Cao
 * @version v1.0 2013-4-28
 */
public class ValueObject{
	private FlowSession session;
	private DataObject paramsStore = null;

	Object cur = null;

	public FlowSession getSession() {
		return session;
	}
	public void setSession(FlowSession session) {
		this.session = session;
	}
	public Object getCur() {
		return cur;
	}
	public void setCur(Object cur) {
		this.cur = cur;
	}
	public DataObject getParamsStore() {
		return paramsStore;
	}
	public void setParamsStore(DataObject paramsStore) {
		this.paramsStore = paramsStore;
	}
	@Override
	protected ValueObject clone(){
		ValueObject vo = new ValueObject();
		vo.setParamsStore(paramsStore);
		vo.setSession(session);
		return vo;
	}
}
