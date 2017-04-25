package cn.com.git.udmp.component.batch.core.server.communicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

import cn.com.git.udmp.component.batch.common.constants.BatchCommonConst;
import cn.com.git.udmp.component.batch.common.constants.CommandEnum;
import cn.com.git.udmp.component.batch.common.utils.BatchJobUtil;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Agent;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.BasicInfo;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Header;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Param;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.ShardInfo;
import cn.com.git.udmp.component.batch.communication.protobuf.protobuf.NettyServer;
import cn.com.git.udmp.component.batch.communication.protobuf.protobuf.NettyServerHandler;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.IBooter;
import cn.com.git.udmp.component.batch.core.command.handler.ICommandHandler;
import cn.com.git.udmp.component.batch.core.command.handler.server.IServerCommandHandler;
import cn.com.git.udmp.component.batch.core.server.job.IJobController;
import cn.com.git.udmp.component.batch.model.AgentInfo;
import cn.com.git.udmp.component.batch.model.JobParam;

/**
 * @description 服务端的socket监听类
 * @author liuliang 
 * @date 2015年2月6日 上午9:38:06
 */
public class BatchSocketServer implements IBooter,  InitializingBean {
    private static String port;
    private static NettyServer server;
    private Map<CommandEnum,ICommandHandler> commandHandlers = Maps.newHashMap();
    
    @Autowired
    private List<IServerCommandHandler> handlers;
    
    
    private NettyServerHandler getServerHandler(){
        return new NettyServerHandler() {
            @Override
            public void handle(Message message) {
                logger.debug("任务{}回执了", message.getJob().getBasicInfo().getId());
                if(!"HEARTBEAT".equals(message.getHeader().getCommand())){
                    logger.debug("回执的信息是\n{}", message);
                }
                // IMessageHandler chain = getMessageHandler();
                // chain.process(message);
                JobSessionContext jobSessionContext = new JobSessionContext();
                jobSessionContext.setJobId(message.getJob().getBasicInfo().getId());
                // TODO batch--可以在这里添加对报文中参数的校验逻辑
                // 将报文中的值赋值到jobSessionContext当中
                setJobSessionContext(jobSessionContext, message);
                // TODO batch--message不应该被丢入jobSessionContext当中，待上一步赋值完善后就不需要下面这一步
                jobSessionContext.set(BatchCommonConst.MESSAGE_NAME_IN_CONTEXT, message);
//                jobController.control(jobSessionContext);
                /**
                 * 返回信息处理逻辑
                 * 1.处理逻辑
                 * 2.异常控制（短信、记日志等等）
                 */
                try{
                    ICommandHandler handler = getHandler(jobSessionContext.getCommand());
                    handler.handle(jobSessionContext);
                }catch (RuntimeException e) {
                    logger.error("返回信息异常:{}",e);
                }
            }
            
            private void setJobSessionContext(JobSessionContext jobSessionContext, Message message) {
                // TODO batch--赋值还需要补充
                ShardInfo shardInfo = message.getJob().getShardInfo();
                jobSessionContext.setStartNum(shardInfo.getStartRowNum());
                jobSessionContext.setEndNum(shardInfo.getEndRowNum());
                if(message.getExtensionsList()!=null&&message.getExtensionsList().size()!=0){
                    jobSessionContext.setExtension(message.getExtensionsList());
                }

                // 设置报文头信息
                Header header = message.getHeader();
                jobSessionContext.setCommand(BatchJobUtil.getCommandEnumByName(header.getCommand()));

                // 设置agent信息
                Agent agent = message.getAgent();
                AgentInfo info = new AgentInfo();
                info.setAgentIp(message.getSender().getFromIp());
                info.setAgentPort(Integer.valueOf(message.getSender().getFromPort()));
                info.setCpuLoad(agent.getCpuLoad());
                info.setMemoryLoad(agent.getMemortyLoad());
                jobSessionContext.setAgentInfo(info);

                // 设置jobrun信息 2015-6-18添加
                BasicInfo basicInfo = message.getJob().getBasicInfo();
                jobSessionContext.setJobRunId(basicInfo.getRunId());

                // 设置job参数信息
                List<Param> paramsList = message.getJob().getParamsList();
                List<JobParam> params = new ArrayList<JobParam>();
                for (Param param : paramsList) {
                    JobParam jobParam = new JobParam();
                    jobParam.setParamCode(param.getParamCode().trim());
                    jobParam.setParamName(param.getParamName().trim());
                    jobParam.setParamType(param.getParamType().trim());
                    jobParam.setParamValue(param.getParamValue().trim());
                    params.add(jobParam);
                }
                jobSessionContext.setParams(params);
            }
        };
    }
    
    @Async
    @Override
    public void boot() {
        logger.debug("启动服务器的socket监听进程");
        if(server==null){
            server = new NettyServer(getServerHandler());
        }
        try {
            server.start(Integer.valueOf(port));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getPort() {
        return port;
    }

    @Override
    public void close() {
        if(server!=null){
            logger.info("==============》执行停止nettyServer操作");
            server.shutdown();
            logger.info("==============》停止nettyServer完成");
        }
    }
    
    private void addHandler(ICommandHandler handler){
        commandHandlers.put(handler.getCommand(),handler);
    }
    
    private ICommandHandler getHandler(CommandEnum command){
        Preconditions.checkNotNull(command);
        return commandHandlers.get(command);
        
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        for (IServerCommandHandler iServerCommandHandler : handlers) {
            addHandler(iServerCommandHandler);
        }
    }

    public List<IServerCommandHandler> getHandlers() {
        return handlers;
    }

    public void setHandlers(List<IServerCommandHandler> handlers) {
        this.handlers = handlers;
    }

    public static void setPort(String port) {
        BatchSocketServer.port = port;
    }

}
