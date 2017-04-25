package cn.com.git.udmp.component.batch.job;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.agent.job.AbstractBatchJobForSingle;
import cn.com.git.udmp.component.batch.core.agent.job.JobData;
import cn.com.git.udmp.modules.batch.dao.BatchTestSouceMapper;
import cn.com.git.udmp.modules.batch.entity.BatchTestSouce;


@Service
public class AgentJob2 extends AbstractBatchJobForSingle{
    
    @Autowired
    private BatchTestSouceMapper sourceMapper;
    
    /* （非 Javadoc）
     * @see cn.com.git.udmp.component.batch.core.agent.job.AbstractBatchJobForSingle#execute(cn.com.git.udmp.component.batch.context.JobSessionContext, cn.com.git.udmp.component.batch.core.agent.job.JobData)
     */
    @Override
    public JobData execute(JobSessionContext jobSessionContext, JobData jobData) {
        logger.debug("正在执行批处理作业agentJob2的逻辑操作");
        return jobData;
    }

    /* （非 Javadoc）
     * @see cn.com.git.udmp.component.batch.core.agent.job.AbstractBatchJobForSingle#write(cn.com.git.udmp.component.batch.context.JobSessionContext, cn.com.git.udmp.component.batch.core.agent.job.JobData)
     */
    @Override
    public void write(JobSessionContext jobSessionContext, JobData jobData) {
        // TODO 自动生成的方法存根
        logger.debug("正在执行批处理作业agentJob2的写入操作");
    }

    /* （非 Javadoc）
     * @see cn.com.git.udmp.component.batch.core.agent.job.AbstractBatchJob#getIdName()
     */
    @Override
    public String getIdName() {
        // TODO 自动生成的方法存根
        return "num_a";
    }

    /* （非 Javadoc）
     * @see cn.com.git.udmp.component.batch.core.agent.job.AbstractBatchJob#isCanBeRun()
     */
    @Override
    public boolean isCanBeRun() {
        return true;
    }

    /* （非 Javadoc）
     * @see cn.com.git.udmp.component.batch.core.agent.job.AbstractBatchJob#queryCounts(cn.com.git.udmp.component.batch.context.JobSessionContext)
     */
    @Override
    public JobSessionContext queryCounts(JobSessionContext jobSessionContext) {
        logger.debug("正在执行批处理作业agentJob2的查询总数操作");
        BatchTestSouce record= new BatchTestSouce();
        List<BatchTestSouce> resultList = sourceMapper.findList(record);
        //查询最小和最大的序列区间
        if(resultList.size()>0){
            jobSessionContext.setStartNum(resultList.get(0).getNumA());
            jobSessionContext.setEndNum(resultList.get(resultList.size()-1).getNumA());
        }else{
            jobSessionContext.setStartNum(0);
            jobSessionContext.setEndNum(1);
        }
        return jobSessionContext;
    }

    /* （非 Javadoc）
     * @see cn.com.git.udmp.component.batch.core.agent.job.AbstractBatchJob#query(cn.com.git.udmp.component.batch.context.JobSessionContext, long, int)
     */
    @Override
    public List<JobData> query(JobSessionContext jobSessionContext, long start, int counts) {
        logger.debug("正在执行批处理作业agentJob2的查询记录操作");
//        sourceMapper.selectByPrimaryKey(numA);
        List<Map> resultList = sourceMapper.findIndexSection(start, start+counts);
        List<JobData> list = Lists.newArrayList();
        
        for (Map source : resultList) {
            JobData jobData = new JobData();
            jobData.setData(source);
            list.add(jobData);
        }
        logger.debug("");
        return list;
    }

    /* （非 Javadoc）
     * @see cn.com.git.udmp.component.batch.core.agent.job.AbstractBatchJob#jobStop()
     */
    @Override
    public void jobStop() {
        // TODO 自动生成的方法存根
        
    }

}
