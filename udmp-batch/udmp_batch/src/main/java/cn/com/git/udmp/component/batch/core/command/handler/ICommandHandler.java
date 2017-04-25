package cn.com.git.udmp.component.batch.core.command.handler;

import cn.com.git.udmp.component.batch.common.constants.CommandEnum;
import cn.com.git.udmp.component.batch.context.JobSessionContext;

/**
 * @description 命令处理类
 * @author Liang liuliang1@git.com.cn
 * @date 2017年2月8日 上午10:06:34
 */
public interface ICommandHandler {
    public JobSessionContext handle(JobSessionContext jobSessionContext);
    
    public CommandEnum getCommand();
}
