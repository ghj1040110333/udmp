package cn.com.git.udmp.bizflow.line;

import cn.com.git.udmp.bizflow.FlowMessage;
import cn.com.git.udmp.bizflow.FlowProcessor;
import cn.com.git.udmp.bizflow.activity.Activity;
import cn.com.git.udmp.bizflow.lifecycle.Initialisable;

/**
 * 连线的作用是把几个活动连接起来
 * @author updated by Spring Cao
 *
 */
public class Line implements FlowProcessor,Initialisable{
	private Activity to;

	public Activity getTo() {
		return to;
	}

	public void setTo(Activity to) {
		this.to = to;
	}

	public FlowMessage process(FlowMessage flowMessage) throws Exception {
		return to.process(flowMessage);
	}

	public void initialise() {
		if(to == null){
			throw new RuntimeException("to 不能为空");
		}
	}

}
