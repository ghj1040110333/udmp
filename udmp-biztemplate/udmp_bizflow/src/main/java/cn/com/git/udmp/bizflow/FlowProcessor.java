package cn.com.git.udmp.bizflow;

import cn.com.git.udmp.bizflow.action.Action;
import cn.com.git.udmp.bizflow.activity.Activity;

/**
 * 流程处理器
 * <p>流程处理器，通过流程处理器来处理流程中的消息变量。
 * <p>流程处理器分为两类一类是Action活动一类是Activity
 * @author updated by Spring Cao
 * @see Action
 * @see Activity
 */
public interface FlowProcessor {

	FlowMessage process(FlowMessage flowMessage) throws Exception;

}
