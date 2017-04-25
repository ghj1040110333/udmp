package cn.com.git.udmp.component.batch.communication.protobuf.protobuf;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message;
import cn.com.git.udmp.component.batch.communication.protobuf.ICommunicator;

/**
 * @description protobuf的通信实现类
 * @author L.liang
 * @createDate 2014年12月22日
 * @version 1.0
 */
@Component("communicator")
public class ProtoCommunicator implements ICommunicator {

    private Map<String, NettyClient> clients = Maps.newHashMap();
    
    @Override
    public Message communicate(Message message, String ip, String port) throws IOException {
        if (!"HEARTBEAT".equals(message.getHeader().getCommand())) {
            logger.debug("开始与{}:{}通信", ip, port);
            logger.debug("发送的消息是:{}", message.toString());
        }
        try {

            NettyClient client = getClient(ip, port);
            logger.debug("client:{}",client);
            checkAndconnect(client);

            if (client.isConnected()) {
                try{
//                  logger.debug("client({}:{}) connected",ip,port);
                    client.sendMsg(message);
                }catch (Exception e) {
                    logger.error("发送消息发生异常:{}",e);
                    saveFailedMsg(message);
                }
            } else {
                // 将消息缓存
//                logger.debug("client({}:{}) unconnected,save the message in the cache",ip,port);
                saveFailedMsg(message);
            }
        } catch (Exception e) {
            logger.error("通信{}:{}异常:{}", ip, port, e);
        }
        return null;
    }

    private void saveFailedMsg(Message message) {
        if (!"HEARTBEAT".equals(message.getHeader().getCommand())) {
            logger.debug("备份的消息是:{}", message.toString());
            MessageCache.addMsg(message);
        }else{
//            logger.debug("保存的是心跳消息,不记录");
        }
    }

    private void checkAndconnect(NettyClient client) {
        if (!client.isConnected()) {
            client.connect();
        }
    }

    private synchronized NettyClient getClient(String ip, String port) {
        String key = ip + ":" + port;
        NettyClient client;
        if (clients.get(key) == null) {
            client = new NettyClient(ip, Integer.valueOf(port));
            clients.put(key, client);
        } else {
            client = clients.get(key);
        }
        return client;
    }

    @Override
    public void close() throws IOException {
        for (String key : clients.keySet()) {
            clients.get(key).close();
        }
    }
}
