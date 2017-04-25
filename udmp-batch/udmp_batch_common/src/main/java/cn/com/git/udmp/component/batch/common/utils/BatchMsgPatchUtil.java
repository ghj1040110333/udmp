package cn.com.git.udmp.component.batch.common.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import cn.com.git.udmp.component.batch.common.constants.CommandEnum;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Agent;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Builder;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Extension;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Header;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Receiver;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Sender;
import cn.com.git.udmp.component.batch.model.AgentInfo;
import cn.com.git.udmp.core.logging.ILog;

/**
 * @description 批处理详细的拼装工具类
 * @author liuliang 
 * @date 2015年7月7日 上午10:09:27
 */
public class BatchMsgPatchUtil implements ILog{

    /**
     * @description 将接收方的IP和端口号拼入通信报文中
     * @param messageBuilder ProtoBuf生成的Message对象的Builder类
     * @param ip IP地址
     * @param port 端口
     */
    public static void patchReceiver(Builder messageBuilder, String ip, String port) {
        // agent信息
        cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Receiver.Builder receiverBuilder = Receiver
                .newBuilder();
        // TODO batch--id是否可取还待定
//        receiverBuilder.setToId(ip);
        receiverBuilder.setToIp(ip);
        receiverBuilder.setToPort(port);
        messageBuilder.setReceiver(receiverBuilder.build());
    }

    /**
     * @description 组装发送方
     * @param messageBuilder ProtoBuf生成的Message对象的Builder类
     * @param port 端口
     */
    public static void patchSender(Builder messageBuilder, String port) {
        // TODO batch--服务器信息(从基础参数表获取本机ip和端口)
        cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Sender.Builder senderBuilder = Sender
                .newBuilder();
        String localHost;
        try {
            localHost = InetAddress.getLocalHost().getHostAddress().toString();
            senderBuilder.setFromIp(localHost);
            logger.debug("获取的本地IP是{}", localHost);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            logger.error("获取本地IP异常:{}", e);
        }
        // senderBuilder.setFromPort("8888");
        senderBuilder.setFromPort(port);
        messageBuilder.setSender(senderBuilder.build());
    }

    /**
     * @description 组装报文头
     * @param messageBuilder ProtoBuf生成的Message对象的Builder类
     * @param commandType 命令类型
     * @param command 命令枚举类型
     */
    public static void patchHeader(Builder messageBuilder, String commandType, CommandEnum command) {
        cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Header.Builder headerBuilder = Header
                .newBuilder();
        // 设置返回报文的状态为服务器向下
        // TODO batch-command需要使用常量替换
        // headerBuilder.setCommandType("COMMAND");
        headerBuilder.setCommandType(commandType);
        // 设置执行的命令种类
        headerBuilder.setCommand(command.toString());
        messageBuilder.setHeader(headerBuilder.build());
    }

    /**
     * @description 组装通信报文中的Agent信息
     * @param messageBuilder ProtoBuf生成的Message对象的Builder类
     * @param agentInfo Agent信息
     */
    public static void patchAgentInfo(Builder messageBuilder, AgentInfo agentInfo) {
        cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Agent.Builder agentBuilder = Agent
                .newBuilder();
        if(agentInfo.getAgentId()!=null){
            agentBuilder.setId(agentInfo.getAgentId());
        }
        agentBuilder.setCpuLoad(agentInfo.getCpuLoad());
        agentBuilder.setMemortyLoad(agentInfo.getMemoryLoad());
        messageBuilder.setAgent(agentBuilder.build());
    }
    
    
    /**
     * @title 拼装拓展信息
     * @description 拼装extendsion的信息
     * 
     * @param messageBuilder
     * @param list 
    */
    public static void patchExtensions(Builder messageBuilder,List<Extension> list){
        for (int i = 0; i < list.size(); i++) {
            messageBuilder.addExtensions(i, list.get(i));
        }
    }
}
