package cn.com.git.udmp.bizflow.activity;

import java.util.Collection;
import java.util.Iterator;

import cn.com.git.udmp.bizflow.FlowMessage;
import cn.com.git.udmp.bizflow.line.Line;

/**
 * 循环活动标签
 * 用于支持可视化设计器
 * @author updated by Spring Cao
 *
 */
public class LoopActivity extends Activity{

	@Override
	protected FlowMessage doProcess(FlowMessage flowMessage) throws Exception {
		Object params = flowMessage.getPayload();
		Line line = getConnections().get(0);
		if(params instanceof Collection){
			for(Iterator<?> it = ((Collection<?>) params).iterator();it.hasNext();){
				flowMessage.setPayload(it.next());
				line.process(flowMessage);
			}
		}else if(params instanceof Object[]){
			Object[] ap = (Object[])params;
			for(int i=0;i<ap.length;i++){
				flowMessage.setPayload(ap[i]);
				line.process(flowMessage);
			}
		}
		return null;
	}

	@Override
	public void initialise() {
		super.initialise();
		if(getConnections()==null || getConnections().size() !=1){
			throw new RuntimeException("此结构必需要一个连线");
		}
	}

}
