package cn.com.git.udmp.component.batch.communication.server;

import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message;
import cn.com.git.udmp.component.batch.communication.protobuf.IAccepter;
import cn.com.git.udmp.component.batch.communication.protobuf.protobuf.IRemoteServer;
import cn.com.git.udmp.component.batch.communication.protobuf.protobuf.NettyServer;
import cn.com.git.udmp.component.batch.communication.protobuf.protobuf.NettyServerHandler;

/** 
 * @description 远程服务端的抽象父类
 * @author Liang liuliang1@git.com.cn 
 * @date 2017年4月9日 下午3:57:44  
*/
public abstract class AbstractRemoteServer implements IAccepter {
    Map<String, IRemoteServer> servers = Maps.newConcurrentMap();

    @Override
    public Message accept(String port) {
        Preconditions.checkNotNull(port);
        // TODO 判断端口是否占用
        IRemoteServer nettyServer = getServer(port);
        try {
            logger.debug("开始启动服务端服务,端口:{}",port);
            servers.put(port, nettyServer);
            nettyServer.start(Integer.valueOf(port));
        } catch (NumberFormatException e) {
            logger.error("非法的端口号:{},请输入正确的端口号", port);
        } catch (Exception e) {
            logger.error("远程服务启动失败:启动端口{}异常,异常情况:{}", port, e);
        }
        return null;
    }

    /*
     * 获取server对象，若重复监听则获取之前的server对象
     */
    private IRemoteServer getServer(String port) {
        if (null != servers.get(port)) {
            return servers.get(port);
        } else {
            return new NettyServer(getHandler());
        }
    }

    /**
     * @title
     * @description
     * @TODO 现在和netty绑定较紧，后续可以再抽象出接口来
     * @return
     */
    public abstract NettyServerHandler getHandler();

    @Override
    public void stopAccepter() {
        // 停止接收服务
        for (String key : servers.keySet()) {
            servers.get(key).shutdown();
            servers.remove(key);
        }
    }

}
