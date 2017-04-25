package cn.com.git.udmp.component.batch.core.server.job;

import org.apache.commons.lang3.StringUtils;

import cn.com.git.udmp.common.utils.SpringContextHolder;
import cn.com.git.udmp.component.batch.common.constants.CommandEnum;

/** 
 * @description 任务控制器工厂
 * @author liuliang liuliang_wb@newchina.live 
 * @date 2015年5月14日 下午2:48:56  
*/
public class JobControllerFactory {
    /**
     *  TODO batch--这里可以通过在配置文件中配置
     * @description 获取任务控制器
     * @param command 指令
     * @return 任务控制器
     */
    public static IJobController getJobController(CommandEnum command) {
        String beanName = "";
        switch (command) {
            case START:
                beanName = "runJobController";
                break;
            case PAUSE:
                beanName = "pauseJobController";
                break;
            case ABORT:
                beanName = "stopJobController";
                break;
            case DISPATCH:
                beanName = "dispatchJobController";
                break;
            case HEARTBEAT:
                beanName = "heartBeatController";
            default:
                break;
        }
        if (StringUtils.isEmpty(beanName)) {
            throw new RuntimeException("指令获取异常");
        }
        return (IJobController) SpringContextHolder.getBean(beanName);
    }
}
