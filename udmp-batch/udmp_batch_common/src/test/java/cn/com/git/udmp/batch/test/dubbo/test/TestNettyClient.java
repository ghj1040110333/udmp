package cn.com.git.udmp.batch.test.dubbo.test;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.remoting.Client;
import com.alibaba.dubbo.remoting.RemotingException;
import com.alibaba.dubbo.remoting.exchange.Exchangers;

public class TestNettyClient {
    public Client client;

    public Client setUpClient(int port, int reconnectPeriod) throws RemotingException {
        final String url = "protobuf://127.0.0.1:" + port + "/client.reconnect.test?check=false&"
                + Constants.RECONNECT_KEY + "=" + reconnectPeriod + "&server=netty&serialization=protobuf";
        return Exchangers.connect(url);
    }
}
