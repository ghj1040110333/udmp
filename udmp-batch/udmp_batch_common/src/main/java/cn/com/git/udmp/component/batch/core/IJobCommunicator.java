package cn.com.git.udmp.component.batch.core;

import cn.com.git.udmp.component.batch.common.constants.CommandEnum;
import cn.com.git.udmp.component.batch.core.ICloser;
import cn.com.git.udmp.component.batch.model.AgentWithJob;

/** 
 * @description 任务通信接口
 * @author liuliang liuliang_wb@newchina.live 
 * @date 2015年7月7日 下午4:52:06  
*/
public interface IJobCommunicator extends ICloser{
    /**
     * @description 发送任务
     * @author liuliang liuliang@newchinalife.com
     * @param agent agent和任务信息
     * @param command 任务指令
    */
    public void sendJob(AgentWithJob agent, CommandEnum command);
}
