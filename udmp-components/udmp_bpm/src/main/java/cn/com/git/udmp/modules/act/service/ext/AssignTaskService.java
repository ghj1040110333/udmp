package cn.com.git.udmp.modules.act.service.ext;

import cn.com.git.udmp.modules.act.entity.Act;

/**
 * 任务分配服务
 * 在人工节点之前调用的，将人工任务分配给用户
 * @author guosg
 *
 */
public interface AssignTaskService {
	/**
	 * 
	 * @param act
	 * @return
	 */
	public String assign(Act act);
}
