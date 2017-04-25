package cn.com.git.udmp.component.batch.core.server.duty.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;

import cn.com.git.udmp.common.exception.FrameworkException;
import cn.com.git.udmp.component.batch.common.constants.BatchCommonConst;
import cn.com.git.udmp.component.batch.common.constants.CommandEnum;
import cn.com.git.udmp.component.batch.common.constants.JobRunStatus;
import cn.com.git.udmp.component.batch.common.constants.StatusEnum;
import cn.com.git.udmp.component.batch.common.utils.BatchJobUtil;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.IJobCommunicator;
import cn.com.git.udmp.component.batch.core.component.chain.duty.AbsJobDuty;
import cn.com.git.udmp.component.batch.core.component.strategy.balancing.JobBalancer;
import cn.com.git.udmp.component.batch.core.server.manage.AgentManager;
import cn.com.git.udmp.component.batch.core.server.manage.IJobManager;
import cn.com.git.udmp.component.batch.model.AgentConfig;
import cn.com.git.udmp.component.batch.model.AgentInfo;
import cn.com.git.udmp.component.batch.model.AgentWithJob;
import cn.com.git.udmp.component.batch.model.JobConfig;
import cn.com.git.udmp.impl.batch.jobRun.ucc.IBatchJobRunUCC;
import cn.com.git.udmp.impl.batch.jobRun.vo.BatchJobRunVO;
import cn.com.git.udmp.impl.batch.jobRunLog.ucc.IBatchJobRunLogUCC;
import cn.com.git.udmp.impl.batch.jobRunLog.vo.BatchJobRunLogVO;

/**
 * @description 任务分发
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年4月8日
 * @version 1.0
 */
@Component
public class RemoteDispatchDuty extends AbsJobDuty {
    @Autowired
    private IJobCommunicator jobCommunicator;
    @Autowired
    private IBatchJobRunUCC batchJobRunUCC;
    @Autowired
    private IBatchJobRunLogUCC batchJobRunLogUCC;
    @Autowired
    private IJobManager jobManager;
    @Autowired
    private AgentManager agentManager;
    @Autowired
    private JobBalancer jobBalancer;
    
    @Override
    public boolean isDuty(JobSessionContext context) {
        return true;
    }

    @Override
    protected JobSessionContext execute(JobSessionContext context) {
        logger.debug("正在执行远程分发...");
        if (context.getCommand() == CommandEnum.HEARTBEAT) {
            // 如果操作指令是心跳监听，则使用心跳监听的逻辑
            heartBeatProcess();
            return context;
        }

        List<AgentWithJob> agents = getAgentWithJob(context);
        
        //若命令只是DISPATCH-分发检测、请求负载信息及总记录数,则只取一个agent节点查询总记录数
        if (context.getCommand() == CommandEnum.DISPATCH) {
            AgentWithJob agent = agents.get(0);
            logger.debug("远程分发duty执行运行任务{}", context.getJobId());
            logger.info("------运行实例编号{}", context.getJobRunId());
            logger.info("server-remotedispatch-command:" + context.getCommand().name());
            //add by L.liang on 2017/1/10 分发检测操作需要设置任务的jobChainRunId,以保证返回来的结果可以与当前任务链挂钩
            Preconditions.checkNotNull(context.getJobChainRunId());
            agent.setJobChainId(context.getJobChainRunId());
            jobCommunicator.sendJob(agent, context.getCommand()); 
            return context;
        }
        //命令不是DISPATCH、HEARTBEAT的情况
        for (AgentWithJob agent : agents) {
            logger.info("-----agent ip:" + agent.getAgentIp());
            AgentInfo agentInfo = AgentManager.getAgentInfoByIP(agent.getAgentIp(),agent.getAgentPort());
            if (agentInfo == null || !agentInfo.isActive()) {
                logger.debug("节点{}通信失败,计入日志", agent.getAgentIp());
                saveJobRun(agent, context, BatchJobUtil.getStatusFlagByEnum(StatusEnum.UNSTART));
                continue;
            }

            String jobRunId = "";
            if (context.getCommand() == CommandEnum.ABORT) {
                // 获取需要停止的任务实例ID
                List<BatchJobRunVO> jobRunVOs = getJobRun(context.getJobId());
                if (jobRunVOs != null) {
                    // 停止命令会停止所有实例
                    for (BatchJobRunVO vo : jobRunVOs) {
                        agent.setJobRunId(vo.getJobRunId().toString());
                        jobCommunicator.sendJob(agent, context.getCommand());
                    }
                } else {
                    // 如果找不到对应的实例，则无需分发停止命令到各个子机
                    return context;
                }
            } else {
                logger.debug("远程分发duty执行运行任务{}", context.getJobId());
                logger.info("------运行实例编号{}", context.getJobRunId());
                // 将任务的运行信息记录到任务运行信息表中
                BatchJobRunVO saveJobRun = saveJobRun(agent, context,
                        BatchJobUtil.getStatusFlagByEnum(StatusEnum.RUNNING));
                if (null != saveJobRun) {
                    jobRunId = saveJobRun.getJobRunId().toString();
                    logger.info("保存后，运行实例编号：" + jobRunId);
                } else {
                    jobRunId = context.getJobRunId();
                    logger.info("准备启动，运行实例编号：" + jobRunId);
                }
                agent.setJobRunId(jobRunId);
                logger.info("server-remotedispatch-command:" + context.getCommand().name());
                jobCommunicator.sendJob(agent, context.getCommand());
            }
        }
        return context;
    }

    private List<AgentWithJob> getAgentWithJob(JobSessionContext context) {
        List<AgentWithJob> agents = getAgents(context.getJobId(), context.getStartNum(), context.getEndNum(),context);
        logger.info("-----getAgents(context.getJobId(), context.getStartNum(), context.getEndNum())任务及其代理数量:"
                + agents.size());
        logger.info("command:" + context.getCommand() + "--context.getJobRunId():" + context.getJobRunId());
        // 如果查询到的agents列表为空，则将不执行分发操作，在jobRun表中记录并且抛出异常
        if (agents.size() == 0) {
            logger.debug("获取到的agents列表为空，无法分发任务");
            AgentWithJob agent = new AgentWithJob();
            // 设置当前任务的agentId为缺省的-1
            agent.setJobId(context.getJobId());
            agent.setAgentId(BatchCommonConst.BATCH_EMPTY_AGENT_ID);
            BatchJobRunVO saveJobRun = saveJobRun(agent, context, BatchJobUtil.getStatusFlagByEnum(StatusEnum.FAIL));
            context.setJobRunId(saveJobRun.getJobRunId().toPlainString());
            saveJobRunLog(context,"任务"+context.getJobId()+"对应的代理列表为空，请检查对应代理端的配置","0","0");
            // 记录完毕后抛出异常,外围操作可以根据此异常做自定义处理
            // TODO 这里面定义的异常可以通过常量CODE来定义，通过异常的CODE值来定位对应异常
            throw new FrameworkException("任务"+context.getJobId()+"对应的代理列表为空，请检查对应代理端的配置");
        }
        return agents;
    }

    private void saveJobRunLog(JobSessionContext context, String log, String logLevel, String logType) {
        BatchJobRunLogVO batchJobRunLogVO = new BatchJobRunLogVO();
        batchJobRunLogVO.setJobRunId(new BigDecimal(context.getJobRunId()));
        batchJobRunLogVO.setLogInfo(log);
        batchJobRunLogVO.setLogLevel(logLevel);
        batchJobRunLogVO.setLogType(logType);
        batchJobRunLogUCC.add(batchJobRunLogVO );
    }

    /**
     * @description 心跳监听的流程
     * @author liuliang liuliang@newchinalife.com
     */
    private void heartBeatProcess() {
        logger.debug("正在执行心跳监听的分发操作..");
        List<AgentConfig> agents = getAllAgents();
        for (AgentConfig agentConfig : agents) {
            AgentWithJob agent = new AgentWithJob();
            try {
                BeanUtils.copyProperties(agent, agentConfig);
            } catch (IllegalAccessException e) {
//                e.printStackTrace();
                throw new FrameworkException(e.getMessage());
            } catch (InvocationTargetException e) {
//                e.printStackTrace();
                throw new FrameworkException(e.getMessage());
            }
            jobCommunicator.sendJob(agent, CommandEnum.HEARTBEAT);
        }
    }

    private List<AgentConfig> getAllAgents() {
        return agentManager.getActivedAgents();
    }

    /**
     * @description 根据任务ID获取任务实例信息
     * @author liuliang liuliang@newchinalife.com
     * @param jobId 任务ID
     * @return 任务实例信息
     */
    private List<BatchJobRunVO> getJobRun(String jobId) {
        BatchJobRunVO batchJobRunVO = new BatchJobRunVO();
        batchJobRunVO.setJobId(new BigDecimal(jobId));
        batchJobRunVO.setStatus(JobRunStatus.RUNNING);
        List<BatchJobRunVO> resultVOs = batchJobRunUCC.findAll(batchJobRunVO);
        return resultVOs;
    }

    /**
     * @description 记录任务执行的记录
     * @author liuliang liuliang@newchinalife.com
     * @param agent 代理agent和任务的组合对象
     * @param context 上下文
     * @param status 状态
     * @return 作业执行记录
     */
    private BatchJobRunVO saveJobRun(AgentWithJob agent, JobSessionContext context, String status) {
        // TODO batch--审计这块考虑可以不可以重构，可维护和可重用行太差
        JobConfig jobConfig = jobManager.getJob(context.getJobId());
        BatchJobRunVO batchJobRunVO = new BatchJobRunVO();

        logger.info("-----context.getJobRunId()：" + context.getJobRunId());

        batchJobRunVO.setStatus(status);
        if (StringUtils.isNotEmpty(context.getJobRunId())) {
            logger.debug("任务运行实例{}已经存在，执行更新任务运行表", context.getJobRunId());
            BatchJobRunVO vo = new BatchJobRunVO();
            vo.setAgentId(new BigDecimal(agent.getAgentId()));
            vo.setJobRunId(new BigDecimal(context.getJobRunId()));
            List<BatchJobRunVO> resultList = batchJobRunUCC.findAll(vo);
            
            if(resultList == null||resultList.size()==0){
                // 新增记录
                batchJobRunVO.setJobId(new BigDecimal(agent.getJobId()));
                batchJobRunVO.setCreateTime(new Date());
                batchJobRunVO
                        .setStartTime(jobConfig.getJobStartWindow() == null ? new Date() : jobConfig.getJobStartWindow());
                batchJobRunVO.setProcessDate(new Date());
                batchJobRunVO.setDueDate(jobConfig.getJobEndWindow() == null ? new Date() : jobConfig.getJobEndWindow());
                batchJobRunVO.setIsSplit(jobConfig.getIsAllowSplit());
                batchJobRunVO.setAgentId(new BigDecimal(agent.getAgentId()));
                batchJobRunVO.setSplitRelaGrpId(UUID.randomUUID().toString());
                batchJobRunVO.setSuccessCnt(new BigDecimal(0));
                batchJobRunVO.setFailedCnt(new BigDecimal(0));
                batchJobRunVO.setJobChainRunId(context.getJobChainRunId());
                batchJobRunVO.setJobRunId(new BigDecimal(context.getJobRunId()));
                batchJobRunVO.setJobChainBatch(new BigDecimal(context.getJobChainBatch()));
                batchJobRunUCC.addWithId(batchJobRunVO);
            } else {
                // 如果任务运行实例已经存在，则执行更新任务运行表
                logger.debug("查询到当前agent:{}的当前任务实例:{},执行更新实例操作，状态为:{}",agent.getAgentId(),context.getJobRunId(),status);
                batchJobRunVO.setJobId(new BigDecimal(agent.getJobId()));
                batchJobRunVO.setAgentId(new BigDecimal(agent.getAgentId()));
                batchJobRunVO.setSplitRelaGrpId(UUID.randomUUID().toString());
                batchJobRunVO.setJobRunId(new BigDecimal(context.getJobRunId()));
                batchJobRunVO.setJobChainBatch(new BigDecimal(context.getJobChainBatch()));
                batchJobRunUCC.update(batchJobRunVO);
            }
            batchJobRunVO.setJobRunId(new BigDecimal(context.getJobRunId()));
            return batchJobRunVO;
        } else {
            // 如果任务运行实例不存在，则执行新增任务运行表
            logger.debug("任务运行实例不存在，执行新增任务运行表");
            batchJobRunVO.setJobId(new BigDecimal(agent.getJobId()));
            batchJobRunVO.setCreateTime(new Date());
            batchJobRunVO.setStartTime(jobConfig.getJobStartWindow() == null ? new Date() : jobConfig
                    .getJobStartWindow());
            batchJobRunVO.setProcessDate(new Date());
            batchJobRunVO.setDueDate(jobConfig.getJobEndWindow() == null ? new Date() : jobConfig.getJobEndWindow());
            batchJobRunVO.setIsSplit(jobConfig.getIsAllowSplit());
            batchJobRunVO.setAgentId(new BigDecimal(agent.getAgentId()));
            batchJobRunVO.setSplitRelaGrpId(UUID.randomUUID().toString());
            batchJobRunVO.setSuccessCnt(new BigDecimal(0));
            batchJobRunVO.setFailedCnt(new BigDecimal(0));
            batchJobRunVO.setJobChainRunId(context.getJobChainRunId());
            batchJobRunVO.setJobChainBatch(new BigDecimal(context.getJobChainBatch()));
            batchJobRunUCC.add(batchJobRunVO);
            //将当前的任务实例ID传递到全局变量中
            context.setJobRunId(batchJobRunVO.getJobRunId().toPlainString());
            return batchJobRunVO;
        }

    }

    /**
     * @description 根据任务ID获取需要分发的agent列表及其处理的区间
     * @author liuliang liuliang@newchinalife.com
     * @param jobId 任务ID
     * @param end 结束值
     * @param start 起始值
     * @param context
     * @return 包含任务关系的agent列表
     */
    private List<AgentWithJob> getAgents(String jobId, long start, long end, JobSessionContext context) {
        // 负载均衡+心跳监听+赋值参数
        List<AgentWithJob> agentsWithJob = jobBalancer.getAgentsWithJob(jobId, start, end,context);
        return agentsWithJob;
    }

    public void setJobCommunicator(IJobCommunicator jobCommunicator) {
        this.jobCommunicator = jobCommunicator;
    }

    public void setBatchJobRunUCC(IBatchJobRunUCC batchJobRunUCC) {
        this.batchJobRunUCC = batchJobRunUCC;
    }

    public void setJobManager(IJobManager jobManager) {
        this.jobManager = jobManager;
    }

    public void setAgentManager(AgentManager agentManager) {
        this.agentManager = agentManager;
    }

    @Override
    public void close() {
        jobCommunicator.close();
    }

    public void setBatchJobRunLogUCC(IBatchJobRunLogUCC batchJobRunLogUCC) {
        this.batchJobRunLogUCC = batchJobRunLogUCC;
    }

    public void setJobBalancer(JobBalancer jobBalancer) {
        this.jobBalancer = jobBalancer;
    }

}
