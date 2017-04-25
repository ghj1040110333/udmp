package cn.com.git.udmp.component.batch.core.agent.communicate;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.com.git.udmp.common.utils.SpringContextHolder;
import cn.com.git.udmp.component.batch.common.constants.BatchCommonConst;
import cn.com.git.udmp.component.batch.common.utils.BatchMsgPatchUtil;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.BasicInfo;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Builder;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Header;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Job;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Param;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.ShardInfo;
import cn.com.git.udmp.component.batch.communication.protobuf.protobuf.ProtoCommunicator;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.exception.BatchBizException;
import cn.com.git.udmp.component.batch.model.JobParam;
import cn.com.git.udmp.core.logging.ILog;

/**
 * TODO batch--通信接口需要支持多种方式，所以还需要再下一步开发过程中重构此类,将接口抽象出来
 * 
 * @description agent端的主程序与通信的对接口（将程序信息封装后发送）
 * @author liuliang 
 * @date 2015年2月6日 上午9:23:52
 */
public class AgentCommunicator implements ILog{
    private ProtoCommunicator communicator ;
    private JobSessionContext jsContext;
    

    /**
     * <p>
     * Title: agent通信类
     * </p>
     * <p>
     * Description: agent通信类
     * </p>
     * 
     * @param jsContext 上下文对象
     */
    public AgentCommunicator(JobSessionContext jsContext) {
        this.jsContext = jsContext;
        this.communicator = SpringContextHolder.getBean(ProtoCommunicator.class);
    }

    /**
     * @description 作业信息通过jsContext传递，异常信息通过参数传递
     * @param startNum
     * @param endNum
     * @param exceptionResults 异常信息
     */
    public void sendBack(Map<String, String> exceptionResults) {
        logger.debug("agent回执信息给{}", jsContext.getServerIp());
        Builder messageBuilder = Message.newBuilder();

        BatchMsgPatchUtil.patchHeader(messageBuilder, BatchCommonConst.BATCH_MESSAGE_COMMAND_TYPE_REPORT,
                jsContext.getCommand());
        BatchMsgPatchUtil.patchReceiver(messageBuilder, jsContext.getServerIp(), jsContext.getServerPort());
        BatchMsgPatchUtil.patchSender(messageBuilder, jsContext.getAgentPort());
        /**
         * modified by liang on 2016-09-13 
         * description：设置返回报文的extension
         * 
         */
        if(jsContext.getExtension()!=null&&jsContext.getExtension().size()!=0){
            BatchMsgPatchUtil.patchExtensions(messageBuilder, jsContext.getExtension());
        }
        
        // 组装agent服务器信息
        if (jsContext.getAgentInfo() != null) {
            BatchMsgPatchUtil.patchAgentInfo(messageBuilder, jsContext.getAgentInfo());
        }

        cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Job.Builder jobBuilder = Job
                .newBuilder();

        cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.BasicInfo.Builder baseInfoBuilder = BasicInfo
                .newBuilder();
        //  任务执行状态
        baseInfoBuilder.setStatus(jsContext.getCommand().toString());
        baseInfoBuilder.setTaskClazz(jsContext.getJobClazzName());
        baseInfoBuilder.setIsSpringBean(1);
        baseInfoBuilder.setId(jsContext.getJobId());
        baseInfoBuilder.setBatchSize(jsContext.getBatchSize());
        // 2015-6-17 回复report时要带着运行jobrunid，否则server端不知道是否是同一个运行实例
        baseInfoBuilder.setRunId(jsContext.getJobRunId());
        //add by L.liang on 2017/1/10 添加任务链实例ID的设置
        if(StringUtils.isNotEmpty(jsContext.getJobChainRunId())){
            baseInfoBuilder.setChainRunId(jsContext.getJobChainRunId());
        }
        // TODO name属性待定
        baseInfoBuilder.setName(jsContext.getJobId());

        // 设置分片信息
        cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.ShardInfo.Builder shardInfoBuilder = ShardInfo
                .newBuilder();
        shardInfoBuilder.setStartRowNum(jsContext.getStartNum());
        shardInfoBuilder.setEndRowNum(jsContext.getEndNum());
        jobBuilder.setBasicInfo(baseInfoBuilder.build());
        jobBuilder.setShardInfo(shardInfoBuilder.build());
        // 拼装参数
        patchParamToJob(jobBuilder);
        if (exceptionResults == null || exceptionResults.size() == 0) {
            // TODO 回执完毕

        } else {
            // TODO 回执异常并附加异常信息
        }
        messageBuilder.setJob(jobBuilder.build());
        try {
            communicator.communicate(messageBuilder.build(), jsContext.getServerIp(), jsContext.getServerPort());
        } catch (IOException e) {
//            e.printStackTrace();
            logger.debug("发送消息异常:{}",e.getMessage());
            // TODO 通信的异常处理还需要完善
        }
    }

    /**
     * @description 回写执行记录状态记录
     * @version 1.0
     * @title 回写执行记录状态记录
     * @param jobRunId 运行实例主键
     * @param totalCnt 总记录数
     * @param successCnt 成功记录数
     * @param failCnt 失败记录数
     */
    public void reportStatusCnt(String jobRunId, long totalCnt, long successCnt, long failCnt) {
        logger.debug("agent回执信息给{},进行执行记录数量更新", jsContext.getServerIp());
        Builder messageBuilder = Message.newBuilder();
        // 设置头文件，标明操作类型
        BatchMessage.Message.Header.Builder headerBuilder = Header.newBuilder();
        headerBuilder.setCommand("STATUS");
        headerBuilder.setCommandType("REPORT");
        // 设置任务信息
        BatchMessage.Message.Job.Builder jobBuilder = Job.newBuilder();
        BatchMessage.Message.BasicInfo.Builder basicInfoBuilder = BasicInfo.newBuilder();

        basicInfoBuilder.setRunId(jobRunId);
        // TODO proto文件中，数据类型定义有误，要定义为long，不是int
        basicInfoBuilder.setTotalCnt((int) totalCnt);
        basicInfoBuilder.setSuccessCnt((int) successCnt);
        basicInfoBuilder.setFailedCnt((int) failCnt);

        // 应该可以不用设置
        basicInfoBuilder.setStatus(jsContext.getCommand().toString());
        basicInfoBuilder.setTaskClazz(jsContext.getJobClazzName());
        basicInfoBuilder.setIsSpringBean(1);
        basicInfoBuilder.setId(jsContext.getJobId());
        basicInfoBuilder.setName("传递个名称");
        basicInfoBuilder.setBatchSize(jsContext.getBatchSize());

        jobBuilder.setBasicInfo(basicInfoBuilder.build());

        messageBuilder.setHeader(headerBuilder.build());
        messageBuilder.setJob(jobBuilder.build());

        // TODO 2015-6-15 添加数据回写，添加sender、receiver必填字段
        BatchMsgPatchUtil.patchReceiver(messageBuilder, jsContext.getServerIp(), jsContext.getServerPort());
        BatchMsgPatchUtil.patchSender(messageBuilder, jsContext.getAgentPort());

        logger.info("*************实时数据回传：" + messageBuilder.build());

        try {
            communicator.communicate(messageBuilder.build(), jsContext.getServerIp(), jsContext.getServerPort());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @description 拼装job参数部分
     * @param jobBuilder ProtoBuf类的job类的builder对象
     */
    private void patchParamToJob(
            cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Job.Builder jobBuilder) {
        // 拼装参数
        List<JobParam> params = jsContext.getParams();
        if (params != null && params.size() != 0) {
            for (int i = 0; i < params.size(); i++) {
                // 设置报文中的参数数组
                JobParam jobParam = params.get(i);
                cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Param.Builder paramBuidler = Param
                        .newBuilder();
                paramBuidler.setParamName(jobParam.getParamName() == null ? "" : jobParam.getParamName());
                paramBuidler.setParamType(jobParam.getParamType() == null ? "" : jobParam.getParamType());
                paramBuidler.setParamValue(jobParam.getParamValue() == null ? "" : jobParam.getParamValue());
                paramBuidler.setParamCode(jobParam.getParamCode() == null ? "" : jobParam.getParamCode());
                jobBuilder.addParams(paramBuidler.build());
            }
        }
    }

    /**
     * 回写执行记录状态
     * 
     * @description 回写执行记录状态
     * @version 1.0
     * @title 回写执行记录状态
     * @param jobRunId 运行实例主键
     * @param status 
     *            状态，对应com.nci.udmp.component.batch.common.constants.JobRunStatus常量类
     */
    public void reportStatus(String jobRunId, String status) {
        logger.debug("agent回执信息给{},进行执行记录状态更新", jsContext.getServerIp());
        Builder messageBuilder = Message.newBuilder();
        // 设置头文件，标明操作类型
        BatchMessage.Message.Header.Builder headerBuilder = Header.newBuilder();
        // 1.COMMAND_TYPE = REPORT-代理端向上
        // 2.COMMAND = FINISH-执行完毕
        headerBuilder.setCommand("FINISH");
        headerBuilder.setCommandType("REPORT");
        // 设置任务信息
        BatchMessage.Message.Job.Builder jobBuilder = Job.newBuilder();
        BatchMessage.Message.BasicInfo.Builder basicInfoBuilder = BasicInfo.newBuilder();

        basicInfoBuilder.setRunId(jobRunId);
        basicInfoBuilder.setStatus(status);

        // 应该可以不用设置
        basicInfoBuilder.setTaskClazz(jsContext.getJobClazzName());
        basicInfoBuilder.setIsSpringBean(1);
        basicInfoBuilder.setId(jsContext.getJobId());
        basicInfoBuilder.setName("传递个名称");
        basicInfoBuilder.setBatchSize(jsContext.getBatchSize());

        jobBuilder.setBasicInfo(basicInfoBuilder.build());
        // 组装jobBuilder的参数部分
        patchParamToJob(jobBuilder);

        messageBuilder.setHeader(headerBuilder.build());
        messageBuilder.setJob(jobBuilder.build());

        // TODO 2015-6-15 添加数据回写，添加sender、receiver必填字段
        BatchMsgPatchUtil.patchReceiver(messageBuilder, jsContext.getServerIp(), jsContext.getServerPort());
        BatchMsgPatchUtil.patchSender(messageBuilder, jsContext.getAgentPort());

        // logger.info("*************执行完毕，job-run实例状态更新：" +
        // messageBuilder.build());
        try {
            communicator.communicate(messageBuilder.build(), jsContext.getServerIp(), jsContext.getServerPort());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 回写异常信息
     * 
     * @description 回写异常信息
     * @version 1.0
     * @title 回写异常信息
     * @author bihb bihb_wb@newchinalife.com
     * @param jobRunId 运行实例主键
     * @param e BatchBizException对象
     */
    public void reportException(String jobRunId, BatchBizException e) {
        logger.debug("agent回执信息给{},进行执行异常信息回写", jsContext.getServerIp());
        Builder messageBuilder = Message.newBuilder();
        // 设置头文件，标明操作类型
        BatchMessage.Message.Header.Builder headerBuilder = Header.newBuilder();
        // 1.COMMAND_TYPE = REPORT-代理端向上
        // 2.COMMAND = EXCEPTION-异常信息
        headerBuilder.setCommand("EXCEPTION");
        headerBuilder.setCommandType("REPORT");
        // 设置任务信息
        BatchMessage.Message.Job.Builder jobBuilder = Job.newBuilder();
        BatchMessage.Message.BasicInfo.Builder basicInfoBuilder = BasicInfo.newBuilder();

        basicInfoBuilder.setRunId(jobRunId);

        basicInfoBuilder.setLogType(e.getErrCode());
        basicInfoBuilder.setLogLevel(e.getInfoLevel());
        basicInfoBuilder.setLogInfo(e.getMessage());

        // 应该可以不用设置
        basicInfoBuilder.setTaskClazz(jsContext.getJobClazzName());
        basicInfoBuilder.setIsSpringBean(1);
        basicInfoBuilder.setId(jsContext.getJobId());
        basicInfoBuilder.setName("传递个名称");
        basicInfoBuilder.setBatchSize(jsContext.getBatchSize());

        jobBuilder.setBasicInfo(basicInfoBuilder.build());
        messageBuilder.setHeader(headerBuilder.build());
        messageBuilder.setJob(jobBuilder.build());

        // TODO 2015-6-15 添加数据回写，添加sender、receiver必填字段
        BatchMsgPatchUtil.patchReceiver(messageBuilder, jsContext.getServerIp(), jsContext.getServerPort());
        BatchMsgPatchUtil.patchSender(messageBuilder, jsContext.getAgentPort());

        logger.info("*************job-run实例异常信息更新：" + messageBuilder.build());
        try {
            communicator.communicate(messageBuilder.build(), jsContext.getServerIp(), jsContext.getServerPort());
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    public JobSessionContext getJsContext() {
        return jsContext;
    }

    public void setJsContext(JobSessionContext jsContext) {
        this.jsContext = jsContext;
    }
}
