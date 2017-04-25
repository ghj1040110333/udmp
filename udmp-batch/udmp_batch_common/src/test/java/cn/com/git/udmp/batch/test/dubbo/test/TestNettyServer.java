package cn.com.git.udmp.batch.test.dubbo.test;

import org.junit.Test;

import com.alibaba.dubbo.remoting.Channel;
import com.alibaba.dubbo.remoting.RemotingException;
import com.alibaba.dubbo.remoting.Server;
import com.alibaba.dubbo.remoting.exchange.Exchangers;
import com.alibaba.dubbo.remoting.exchange.support.ExchangeHandlerAdapter;

public class TestNettyServer {

    public Server setUpServer(int port) throws RemotingException {
        final String url = "exchange://127.0.0.1:" + port + "/client.reconnect.test?server=netty&serialization=protobuf";
        return Exchangers.bind(url, new HandlerAdapter());
    }

    static class HandlerAdapter extends ExchangeHandlerAdapter {
        public void connected(Channel channel) throws RemotingException {
            System.out.println("server connected ");
        }

        public void disconnected(Channel channel) throws RemotingException {
            System.out.println("server disConnected ");
        }
        
        @Override
        public void received(Channel channel, Object message) throws RemotingException {
            System.out.println("msg received:"+message);
            super.received(channel, message);
        }

        public void caught(Channel channel, Throwable exception) throws RemotingException {
            System.out.println("exception " + exception.getMessage());
        }
    }
}
