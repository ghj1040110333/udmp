package cn.com.git.udmp.component.batch.communication.protobuf;

import java.io.IOException;

import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message;
import cn.com.git.udmp.core.logging.ILog;

/**
 * @description 通信接口
 * @author L.liang
 * @createDate 2014年12月22日
 * @version 1.0
 */
public interface ICommunicator extends ILog{

    /**
     * @description 与服务端通信方法
     * @param message 消息对象
     * @param ip IP地址
     * @param port 端口
     * @return 消息对象
     * @throws IOException 异常 
    */
    public Message communicate(Message message, String ip, String port) throws IOException;
    
    /**
     * @description 关闭远程连接，供线程停止使用
     * @throws IOException 
    */
    public void close() throws IOException;
}
