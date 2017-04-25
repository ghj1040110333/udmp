package cn.com.git.udmp.bizflow.activity;

import cn.com.git.udmp.bizflow.FlowMessage;
/**
 * 启始活动表明活动的开始，通过此活动来调用一个流程
 * 用于支持可视化设计器
 * @author updated by Spring Cao
 *
 */
public class StartActivity extends Activity{

	@Override
	protected FlowMessage doProcess(FlowMessage flowMessage) throws Exception {
		ParamsStoreUtils.saveParam(getName(), flowMessage);
		return getConnections().get(0).process(flowMessage);
	}

	@Override
	public void initialise() {
		super.initialise();
		if(getConnections()==null || getConnections().size()==0 || getConnections().size()>1){
			throw new RuntimeException("start的连线不能为空,且只能有一条连线");
		}
	}
}
