package cn.com.git.udmp.component.batch.template.job;

import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.context.SessionContext;

/**
 * @description 作业的执行父类(预留前置后置执行逻辑)
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年1月21日 下午3:04:58
 */
public abstract class AbstractPrePostJobTemplate implements IJobTemplate {
    private JobSessionContext jobSessionContext;
    private boolean activeFlag = true;
    
    @Override
    public SessionContext execute(SessionContext context) {
        // TODO 组装jobSessionContext来传递
        return null;
    }

    @Override
    public boolean tplInit() {
        // TODO 初始化
        return false;
    }

    @Override
    public boolean tplDestroy() {
        // TODO 销毁
        //销毁作业时，设置active标签为false
        setActiveFlag(false);
        return false;
    }

    /**
     * @description 模板预操作
     * @author liuliang liuliang@newchinalife.com
     * @param jobSessionContext 作业全局配置信息
     * @return 作业全局配置信息
     */
    public abstract JobSessionContext preExecute(JobSessionContext jobSessionContext);

    /**
     * @description 模板的后置操作
     * @author liuliang liuliang@newchinalife.com
     * @param jobSessionContext 作业全局配置信息
     * @return 作业全局配置信息
     */
    public abstract JobSessionContext postExecute(JobSessionContext jobSessionContext);

    public JobSessionContext getJobSessionContext() {
        return jobSessionContext;
    }

    public void setJobSessionContext(JobSessionContext jobSessionContext) {
        this.jobSessionContext = jobSessionContext;
    }

    public boolean isActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(boolean activeFlag) {
        this.activeFlag = activeFlag;
    }
}
