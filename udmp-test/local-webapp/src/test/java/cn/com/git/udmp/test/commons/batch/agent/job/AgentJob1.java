/**
 * GIT Confidential
 * Licensed Materials - Property of GIT
 * Copyright (c) 1998-2015 Global InfoTech Corp. All Rights Reserved.
 * Global InfoTech, Inc. owns the copyright and other intellectual
 * property rights in this software.
 *
 * The source code for this program is not published.
 * Duplication or use of the Software is not permitted
 * except as expressly provided in a written agreement
 * between your company and Global InfoTech, Inc.
 */

package cn.com.git.udmp.test.commons.batch.agent.job;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.agent.job.AbstractBatchJobForSingle;
import cn.com.git.udmp.component.batch.core.agent.job.JobData;

/**
 * @author liang
 *
 */
@Service(value="agentJob1")
public class AgentJob1 extends AbstractBatchJobForSingle{

    /* （非 Javadoc）
     * @see cn.com.git.udmp.component.batch.core.agent.job.AbstractBatchJobForSingle#execute(cn.com.git.udmp.component.batch.context.JobSessionContext, cn.com.git.udmp.component.batch.core.agent.job.JobData)
     */
    @Override
    public JobData execute(JobSessionContext jobSessionContext, JobData jobData) {
        logger.debug("正在执行批处理作业agentJob1的逻辑操作");
        return jobData;
    }

    /* （非 Javadoc）
     * @see cn.com.git.udmp.component.batch.core.agent.job.AbstractBatchJobForSingle#write(cn.com.git.udmp.component.batch.context.JobSessionContext, cn.com.git.udmp.component.batch.core.agent.job.JobData)
     */
    @Override
    public void write(JobSessionContext jobSessionContext, JobData jobData) {
        // TODO 自动生成的方法存根
        logger.debug("正在执行批处理作业agentJob1的写入操作");
    }

    /* （非 Javadoc）
     * @see cn.com.git.udmp.component.batch.core.agent.job.AbstractBatchJob#getIdName()
     */
    @Override
    public String getIdName() {
        // TODO 自动生成的方法存根
        return "";
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
        // TODO 自动生成的方法存根
        logger.debug("正在执行批处理作业agentJob1的查询总数操作");
        jobSessionContext.setStartNum(1);
        jobSessionContext.setEndNum(100);
        return jobSessionContext;
    }

    /* （非 Javadoc）
     * @see cn.com.git.udmp.component.batch.core.agent.job.AbstractBatchJob#query(cn.com.git.udmp.component.batch.context.JobSessionContext, long, int)
     */
    @Override
    public List<JobData> query(JobSessionContext jobSessionContext, long start, int counts) {
        // TODO 自动生成的方法存根
        logger.debug("正在执行批处理作业agentJob1的查询记录操作");
        JobData data = new JobData();
        return Arrays.asList(new JobData[]{data});
    }

    /* （非 Javadoc）
     * @see cn.com.git.udmp.component.batch.core.agent.job.AbstractBatchJob#jobStop()
     */
    @Override
    public void jobStop() {
        // TODO 自动生成的方法存根
        
    }

}
