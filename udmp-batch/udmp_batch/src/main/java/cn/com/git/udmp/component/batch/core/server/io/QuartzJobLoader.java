package cn.com.git.udmp.component.batch.core.server.io;

import java.util.List;
import java.util.Properties;

import org.springframework.stereotype.Component;

import cn.com.git.udmp.component.batch.common.constants.BatchCommonConst;
import cn.com.git.udmp.component.batch.common.utils.BatchModelUtil;
import cn.com.git.udmp.component.batch.core.server.manage.BatchQuartzManager;
import cn.com.git.udmp.component.batch.io.IResourceLoader;
import cn.com.git.udmp.component.batch.model.JobConfig;
import cn.com.git.udmp.impl.batch.job.ucc.IBatchJobUCC;
import cn.com.git.udmp.impl.batch.job.vo.BatchJobVO;

/**
 * @description 定时任务加载器
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年3月30日
 * @version 1.0
 */
public class QuartzJobLoader implements IResourceLoader {
    private IBatchJobUCC batchJobUCC;

    @Override
    public void load(Properties props) {
        logger.debug("定时任务加载器loading...");
        BatchQuartzManager.quartzInit();
        // 从数据库添加定时任务
        logger.debug("开始添加定时任务...");
        addQuartzJob();
    }

    /**
     * @description 从数据库添加定时任务
     * @author liuliang liuliang@newchinalife.com
     */
    private void addQuartzJob() {
        BatchJobVO batchJobVO = new BatchJobVO();
        batchJobVO.setIsEnable(BatchCommonConst.BATCH_FLAG_TRUE); 
        batchJobVO.setIsDeleted(BatchCommonConst.BATCH_FLAG_FALSE);
        batchJobVO.setIsGroup(BatchCommonConst.BATCH_FLAG_FALSE);
        batchJobVO.setJobType(BatchCommonConst.BATCH_JOB_TYPE_QUARTZ);
        List<BatchJobVO> batchJobVOResult = batchJobUCC.findAll(batchJobVO);
        // 添加定时启动任务
        for (BatchJobVO key : batchJobVOResult) {
            logger.info("添加定时任务:{}",key.getJobName());
            setQuartzJob(key);
        }
        // 添加心跳监听任务

    }

    /**
     * @description 通过任务的vo对象添加定时任务
     * @author liuliang liuliang@newchinalife.com
     * @param batchJobVO 作业VO
    */
    private void setQuartzJob(BatchJobVO batchJobVO) {
        JobConfig jobConfig = new JobConfig();
        BatchModelUtil.patchJobConfig(jobConfig, batchJobVO);
        BatchQuartzManager.setRunJob(jobConfig);
    }

    @Override
    public void load() {
        // TODO Auto-generated method stub
        load(null);
    }

    public void setBatchJobUCC(IBatchJobUCC batchJobUCC) {
        this.batchJobUCC = batchJobUCC;
    }

    @Override
    public void unload() {
        logger.debug("********quartzJobloader unload********");
        BatchQuartzManager.quartzStop();
    }

}
