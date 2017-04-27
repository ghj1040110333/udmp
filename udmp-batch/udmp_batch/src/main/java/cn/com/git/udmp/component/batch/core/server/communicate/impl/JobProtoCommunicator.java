package cn.com.git.udmp.component.batch.core.server.communicate.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.git.udmp.common.exception.FrameworkException;
import cn.com.git.udmp.component.batch.common.constants.BatchCommonConst;
import cn.com.git.udmp.component.batch.common.constants.CommandEnum;
import cn.com.git.udmp.component.batch.common.utils.BatchMsgPatchUtil;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.BasicInfo;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Builder;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Job;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Param;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.ShardInfo;
import cn.com.git.udmp.component.batch.communication.protobuf.ICommunicator;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.IJobCommunicator;
import cn.com.git.udmp.component.batch.core.server.communicate.BatchSocketServer;
import cn.com.git.udmp.component.batch.core.server.manage.AgentManager;
import cn.com.git.udmp.component.batch.core.server.manage.IJobManager;
import cn.com.git.udmp.component.batch.model.AgentInfo;
import cn.com.git.udmp.component.batch.model.AgentWithJob;
import cn.com.git.udmp.component.batch.model.JobConfig;
import cn.com.git.udmp.component.batch.model.JobParam;
import cn.com.git.udmp.core.exception.FrameworkRemoteException;
import cn.com.git.udmp.utils.lang.UtilDate;

/**
 * @description 任务的通讯类
 * @author liuliang
 * @date 2015年2月3日 下午2:32:02
 */
@Component
public class JobProtoCommunicator implements IJobCommunicator {
    @Autowired
    private ICommunicator communicator;
    @Autowired
    private IJobManager jobManager;
    private static List<Thread> threads = new ArrayList<Thread>();

    /**
     * @description 在线程中启动任务
     * @param agent 代理和作业的组合对象
     * @param command 指令
     */
    public void sendJob(final AgentWithJob agent, final CommandEnum command) {
        // 将通信放入线程当中，防止主线程堵塞
        try {
            sendJobByThread(agent, command);
        } catch (Exception e) {
            // 保证线程不会异常退出
            logger.error("发送报文中发生异常:{}", e);
        }

    }

    /**
     * @description 启动任务
     * @param agent 代理和任务的组合对象
     * @param command 指令
     */
    private void sendJobByThread(AgentWithJob agent, CommandEnum command) {
        // 组装protobuf对象
        Builder messageBuilder = Message.newBuilder();
        BatchMsgPatchUtil.patchHeader(messageBuilder, BatchCommonConst.BATCH_MESSAGE_COMMAND_TYPE_COMMAND, command); // 组装报文头
        BatchMsgPatchUtil.patchSender(messageBuilder, BatchSocketServer.getPort()); // 组装发送方报文
        BatchMsgPatchUtil.patchReceiver(messageBuilder, agent.getAgentIp(), String.valueOf(agent.getAgentPort())); // 组装响应方报文

        /**
         * modified by liang on 2016-09-13 description：设置返回报文的extension
         * 
         */
        JobSessionContext jsContext = agent.getJsContext();
        if (jsContext != null && jsContext.getExtension() != null && jsContext.getExtension().size() != 0) {
            BatchMsgPatchUtil.patchExtensions(messageBuilder, jsContext.getExtension());
        }

        if (!StringUtils.isEmpty(agent.getJobId())) {
            JobConfig jobConfig = jobManager.getJob(agent.getJobId());
            cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Job.Builder jobBuilder = Job
                    .newBuilder();

            cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.BasicInfo.Builder baseInfoBuilder = BasicInfo
                    .newBuilder();
            baseInfoBuilder.setStatus(command.toString());
            baseInfoBuilder.setTaskClazz(jobConfig.getTaskClazz());
            baseInfoBuilder.setIsSpringBean(Integer.valueOf(jobConfig.getIsSpringBean()));
            baseInfoBuilder.setId(agent.getJobId());
            baseInfoBuilder.setBatchSize(jobConfig.getJobBatchSize());
            baseInfoBuilder.setRunId(agent.getJobRunId() == null ? "" : agent.getJobRunId()); // 任务实例ID
            // add by L.liang on 2017/1/10 添加任务链实例ID的设置
            if (!StringUtils.isEmpty(agent.getJobChainId())) {
                baseInfoBuilder.setChainRunId(agent.getJobChainId());
            }
            // TODO 按需添加name属性
            baseInfoBuilder.setName(agent.getJobId());
            baseInfoBuilder.setJobThreadLimit(agent.getJobThreadLimit());
            // 设置启动时间
            if (jobConfig.getJobStartWindow() != null) {
                baseInfoBuilder.setJobStartWindow(
                        UtilDate.format(jobConfig.getJobStartWindow(), BatchCommonConst.TIME_WINDOW_FORMAT));
            }
            // 设置停止时间
            if (jobConfig.getJobEndWindow() != null) {
                baseInfoBuilder.setJobEndWindow(
                        UtilDate.format(jobConfig.getJobEndWindow(), BatchCommonConst.TIME_WINDOW_FORMAT));
            }
            // 添加一个轮询任务的标识
            baseInfoBuilder.setIsLoop(BatchCommonConst.BATCH_FLAG_TRUE.equals(jobConfig.getIsLoop()) ? true : false);
            // 分页信息
            cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.ShardInfo.Builder shardInfoBuilder = ShardInfo
                    .newBuilder();
            shardInfoBuilder.setStartRowNum(agent.getStartNum());
            shardInfoBuilder.setEndRowNum(agent.getEndNum());

            jobBuilder.setBasicInfo(baseInfoBuilder.build());
            jobBuilder.setShardInfo(shardInfoBuilder.build());

            int i = 0;
            for (JobParam param : agent.getParams()) {
                // 组装参数
                cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Param.Builder paramBuilder = Param
                        .newBuilder();
                String paramValue = param.getParamValue();
                // TODO batch--给参数赋值
                // TODO 2015-6-15 bihb 字段有非空设置，不能传递null 运行时抛异常 先暂时添加三元操作符排除null
                paramBuilder.setParamName(null == param.getParamName() ? "" : param.getParamName());
                paramBuilder.setParamCode(null == param.getParamCode() ? "" : param.getParamCode());
                paramBuilder.setParamType(null == param.getParamType() ? "" : param.getParamType());
                paramBuilder.setParamValue(paramValue);
                jobBuilder.addParams(paramBuilder.build());
            }
            messageBuilder.setJob(jobBuilder.build());
        }
        try {
            communicator.communicate(messageBuilder.build(), agent.getAgentIp(), String.valueOf(agent.getAgentPort()));
        } catch (FrameworkRemoteException e) {
            logger.error("通信异常:{}", e.getMessage());
            unActiveAgent(agent);
            throw e;
        } catch (UnknownHostException e) {
            logger.error("通信异常:{}", e.getMessage());
            unActiveAgent(agent);
        } catch (IOException e) {
            logger.error("通信异常:{}", e.getMessage());
            unActiveAgent(agent);
        }
    }

    /**
     * @description 将agent设为异常状态
     * @param agent agent信息
     */
    private void unActiveAgent(AgentWithJob agent) {
        logger.debug("将代理{}:{}设为异常", agent.getAgentIp(), agent.getAgentPort());
        AgentInfo info = new AgentInfo();
        try {
            BeanUtils.copyProperties(info, agent);
        } catch (IllegalAccessException e) {
            // e.printStackTrace();
            throw new FrameworkException(e.getMessage());
        } catch (InvocationTargetException e) {
            // e.printStackTrace();
            throw new FrameworkException(e.getMessage());
        }
        info.setActive(false);
        AgentManager.updateAgentInfo(info);
    }

    public ICommunicator getCommunicator() {
        return communicator;
    }

    public void setCommunicator(ICommunicator communicator) {
        this.communicator = communicator;
    }

    public void setJobManager(IJobManager jobManager) {
        this.jobManager = jobManager;
    }

    /**
     * @description 通讯关闭逻辑，防止IO操作堵塞线程
     * @see cn.com.git.udmp.component.batch.core.ICloser#close()
     */
    @Override
    @Deprecated
    public void close() {
        if (threads != null && threads.size() != 0) {
            for (Thread key : threads) {
                key.interrupt();
            }
        }
        
    }
}
