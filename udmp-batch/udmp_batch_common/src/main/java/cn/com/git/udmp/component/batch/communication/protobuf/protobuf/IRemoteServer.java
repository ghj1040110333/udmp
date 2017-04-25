package cn.com.git.udmp.component.batch.communication.protobuf.protobuf;

public interface IRemoteServer {

    /**
     * @title 通信服务端关闭接口
     * @description
     *  
    */
    void shutdown();

    /**
     * @title 根据端口号启动服务端
     * @description
     * 
     * @param port
     * @throws Exception 
    */
    void start(int port) throws Exception;

}
