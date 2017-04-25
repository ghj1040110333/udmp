package cn.com.git.udmp.component.batch.core.agent.communicate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.com.git.udmp.component.batch.common.constants.BatchCommonConst;
import cn.com.git.udmp.component.batch.common.constants.CommandEnum;
import cn.com.git.udmp.component.batch.common.utils.BatchJobUtil;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.BasicInfo;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Extension;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Param;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.ShardInfo;
import cn.com.git.udmp.component.batch.communication.protobuf.protobuf.NettyServerHandler;
import cn.com.git.udmp.component.batch.communication.server.AbstractRemoteServer;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.agent.ITaskExecutor;
import cn.com.git.udmp.component.batch.core.agent.TaskExecutorFactory;
import cn.com.git.udmp.component.batch.core.component.chain.IChain;
import cn.com.git.udmp.component.batch.model.JobParam;
import cn.com.git.udmp.utils.lang.UtilDate;
import cn.com.git.udmp.utils.log.MDCLogUtil;

/** 
 * @description 略微重构后的作业执行端的服务发布server
 * @author Liang liuliang1@git.com.cn 
 * @date 2017年4月9日 下午4:03:35  
*/
public class AgentServer extends AbstractRemoteServer {

    private Map<String, IChain> chainExecutors = new HashMap<String, IChain>();
    
    @Override
    public NettyServerHandler getHandler() {
        return new AgentServerHandler();
    }
    
    /**
     * @description Message的值转换成JobSessionContext
     * @author liuliang liuliang@newchinalife.com
     * @param message 通信的信息对象
     * @return 任务执行信息的session对象
     */
    private JobSessionContext genJsContext(Message message) {
        JobSessionContext jsContext = new JobSessionContext();

        BasicInfo basicInfo = message.getJob().getBasicInfo();
        jsContext.setJobId(basicInfo.getId());
        jsContext.setJobName(basicInfo.getName());
        jsContext.setJobClazzName(basicInfo.getTaskClazz());
        jsContext.setBatchSize(basicInfo.getBatchSize());
        jsContext.setThreadSize(basicInfo.getJobThreadLimit());
        // 设置jobrunid，运行实例编号
        jsContext.setJobRunId(basicInfo.getRunId());
        //add by L.liang on 2017/1/10 添加任务链实例ID的设置
        if(StringUtils.isNoneEmpty(basicInfo.getChainRunId())){
            jsContext.setJobChainRunId(basicInfo.getChainRunId());
        }
        
        // 从报文中获取时间窗口信息
        if (!StringUtils.isEmpty(basicInfo.getJobStartWindow())) {
            jsContext.setJobStartWindow(
                    UtilDate.parse(basicInfo.getJobStartWindow(), BatchCommonConst.TIME_WINDOW_FORMAT));
        }
        if (!StringUtils.isEmpty(basicInfo.getJobEndWindow())) {
            jsContext.setJobEndWindow(UtilDate.parse(basicInfo.getJobEndWindow(), BatchCommonConst.TIME_WINDOW_FORMAT));
        }
        // 配置是否是轮询任务的属性
        jsContext.setLoop(basicInfo.getIsLoop());

        ShardInfo shardInfo = message.getJob().getShardInfo();
        // 设置分区起始和终止值
        if (shardInfo != null) {
            logger.debug("agent获取到的执行分区是{}到{}", shardInfo.getStartRowNum(), shardInfo.getEndRowNum());
            jsContext.setStartNum(shardInfo.getStartRowNum());
            jsContext.setEndNum(shardInfo.getEndRowNum());
        }
        jsContext.setServerIp(message.getSender().getFromIp());
        jsContext.setServerPort(message.getSender().getFromPort());
        jsContext.setAgentIp(message.getReceiver().getToIp());
        jsContext.setAgentPort(message.getReceiver().getToPort());

        logger.debug("事务批次是{}", basicInfo.getBatchSize());
        jsContext.setSpringBean(basicInfo.getIsSpringBean() == 1 ? true : false);
        jsContext.setJobRunId(basicInfo.getRunId()); // 获取任务实例ID
        List<JobParam> paramslist = new ArrayList<JobParam>();
        for (Param param : message.getJob().getParamsList()) {
            logger.debug("获取到的参数为{}:{}", param.getParamName(), param.getParamValue());
            JobParam jobParam = new JobParam();
            jobParam.setParamCode(param.getParamCode());
            jobParam.setParamName(param.getParamName());
            jobParam.setParamType(param.getParamType());
            jobParam.setParamValue(param.getParamValue());
            paramslist.add(jobParam);
        }
        jsContext.setParams(paramslist);

        // 通过报文获取需要执行的command
        String command = message.getHeader().getCommand();
        jsContext.setCommand(BatchJobUtil.getCommandEnumByName(command));

        /**
         * modified by liang on 2016-09014 description:添加extension信息拼装
         */
        List<Extension> extensionsList = message.getExtensionsList();
        jsContext.setExtension(extensionsList);
        return jsContext;
    }
    
    /**
     * @title
     * @description TODO 执行异常回执失败
     * 
     * @param message 
    */
    private void reportFail(Message message) {
        //TODO 执行异常回执失败
        /**
         * TODO 1.记录当前异常
         *      2.报告异常信息给调度端记录
         */
        
        
        
    }
    

    
    private class AgentServerHandler extends NettyServerHandler {

        @Override
        public void handle(Message message) {
            // 服务端处理逻辑
            // 1.获取大线程池，从中获取小线程池（小线程池负责管理具体的作业线程）
            // 2.具体作业线程需要一个线程模板，处理逻辑抽象化
            logger.debug("agent端的socket监听服务端在处理呢...");
            if (!"HEARTBEAT".equals(message.getHeader().getCommand())) {
                logger.debug("接收到的信息是{}", message.toString());
            }

            JobSessionContext jsContext = genJsContext(message);
            MDCLogUtil.setJobContext(jsContext);
            logger.debug("接收到的命令是{}", jsContext.getCommand());
            try {
                if (jsContext.getCommand() != CommandEnum.START && jsContext.getCommand() != CommandEnum.ABORT) {
                    // TODO batch--统计任务的数量
                    logger.debug("agent开始查询作业{}的总数", jsContext.getJobId());
                    chainExecutors.get(jsContext.getCommand().toString()).executeChain(jsContext);
                } else {
                    // 从作业处理工厂中获取一个作业处理器
                    ITaskExecutor taskExecutor = TaskExecutorFactory.getTaskExecutor(jsContext);
                    // modified by liang on 2015/5/13 执行任务通过命令区分
                    // 所以这里需要调用executeTask方法而不是startTask方法
                    // taskExecutor.startTask();

                    // 调用作业执行器的执行任务方法
                    taskExecutor.executeTask();
                }
            } catch (Exception e) {
                logger.debug("agent处理请求发生异常:{}", e);
                // TODO 返回失败信息回去
                reportFail(message);
            }
        }
    }



    public void setChainExecutors(Map<String, IChain> chainExecutors) {
        this.chainExecutors = chainExecutors;
    }
    

}
