package cn.com.git.udmp.bizflow.activity;

import cn.com.git.udmp.bizflow.FlowMessage;
import cn.com.git.udmp.bizflow.line.Line;
import cn.com.git.udmp.bizflow.utils.ElUtils;
/**
 * 内容路由活动
 * <p>可以通过参数的内容来，决定要走哪条连线
 * @author updated by Spring Cao
 *
 */
public class ContentBaseRouter extends Activity{
	/**运行的下一下活动的名称，targetName是一个EL表达式*/
	String targetName = null;
	@Override
	protected FlowMessage doProcess(FlowMessage flowMessage) throws Exception {
		//通过EL表达式，获得下一个活动节点的名称/
		String toName = (String) ElUtils.getValue(targetName, flowMessage);
		Line line = findLine(toName);
		if(line!=null)
			return line.process(flowMessage);
		else
			return flowMessage;
	}
	public String getTargetName() {
		return targetName;
	}
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

}
