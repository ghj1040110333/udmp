package cn.com.git.udmp.component.batch.core.server.duty.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.git.udmp.component.batch.common.constants.BatchCommonConst;
import cn.com.git.udmp.component.batch.common.constants.StatusEnum;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.component.chain.DutyEndException;
import cn.com.git.udmp.component.batch.core.component.chain.duty.AbsJobDuty;
import cn.com.git.udmp.component.batch.core.server.manage.JobStatusManager;
import cn.com.git.udmp.component.batch.model.JobParam;
import cn.com.git.udmp.impl.batch.param.ucc.IParamManageUCC;
import cn.com.git.udmp.impl.batch.param.vo.ParamManageVO;

/**
 * @description EOD任务返回处理
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年7月4日 上午11:32:08
 */
@Deprecated
public class EODReportDuty extends AbsJobDuty {
    private IParamManageUCC paramManageUCC;
    private JobParam jobParam;
    @Autowired
    private JobStatusManager jobStatusManager;
    
    
    
    @Override
    protected JobSessionContext execute(JobSessionContext context) {
        // EOD对应的逻辑
        ParamManageVO paramManageVO = new ParamManageVO();
        // 从context中获取需要修改的参数并且封装到VO当中，目前为EOD，后续可以改成针对任意参数或者任意表的更改
        JobParam jobParam = this.getJobParam();
        logger.debug("EOD操作获取到的参数名称为：{},参数值为:{}", jobParam.getParamName(), jobParam.getParamValue());
        paramManageVO.setParamName(jobParam.getParamName());
        paramManageVO.setIsDeleted(BatchCommonConst.BATCH_FLAG_FALSE);
        paramManageVO.setIsSingleValue(BatchCommonConst.BATCH_FLAG_TRUE);
        ParamManageVO paramVO = paramManageUCC.find(paramManageVO);
        if (paramVO.getParamId() != null) {
            logger.debug("修改EOD对应的参数{}", jobParam.getParamValue());
            paramVO.setParamValue(jobParam.getParamValue());
            paramManageUCC.update(paramVO);
        } else {
            logger.debug("查询不到EOD对应的参数");
        }
        // 激活EOD任务后续的任务
        jobStatusManager.setJobStatus(context.getJobId(),context.getJobRunId(),context.getJobChainRunId(), StatusEnum.SUCCESS);
        // 终止后续操作
        throw new DutyEndException();
    }

    @Override
    public boolean isDuty(JobSessionContext context) {
        // TODO 通过某一标识判断是否是Duty
        List<JobParam> params = context.getParams();
        if (params == null) {
            return false;
        }
        for (JobParam key : params) {
            if ("EOD".equals(key.getParamName())) {
                // logger.debug("获取到EOD的值{}",key.getParamValue());
                this.setJobParam(key);
                return true;
            }
        }
        // logger.debug("未获取到EOD的值");
        return false;
    }

    public void setJobParam(JobParam jobParam) {
        this.jobParam = jobParam;
    }

    public JobParam getJobParam() {
        return jobParam;
    }

    public void setParamManageUCC(IParamManageUCC paramManageUCC) {
        this.paramManageUCC = paramManageUCC;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
        
    }

    public void setJobStatusManager(JobStatusManager jobStatusManager) {
        this.jobStatusManager = jobStatusManager;
    }

}
