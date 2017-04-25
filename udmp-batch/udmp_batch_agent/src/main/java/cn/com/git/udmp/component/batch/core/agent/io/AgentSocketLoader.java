package cn.com.git.udmp.component.batch.core.agent.io;

import java.util.Properties;

import cn.com.git.udmp.component.batch.communication.protobuf.IAccepter;
import cn.com.git.udmp.component.batch.io.IResourceLoader;

/**
 * @description agent的socket服务端加载器
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年4月15日 下午4:51:49
 */
public class AgentSocketLoader implements IResourceLoader {
    private String port = "10000";
    private IAccepter agentSocketServer;
    private Thread socketThread;

    @Override
    public void load(Properties props) {
        // TODO port可以从配置property中读取
        socketThread = new Thread() {
            public void run() {
                try {
                    agentSocketServer.accept(port);
                } catch (RuntimeException e) {
                    logger.error("agent发生异常{}", e);
                }
            }
        };
        socketThread.start();
    }

    @Override
    public void load() {
        load(null);
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setAgentSocketServer(IAccepter agentSocketServer) {
        this.agentSocketServer = agentSocketServer;
    }

    @Override
    public void unload() {
        if (socketThread != null) {
            socketThread.interrupt();
        }
    }

}
