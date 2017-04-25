package cn.com.git.udmp.batch.test.dubbo.test;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.alibaba.dubbo.common.utils.NetUtils;
import com.alibaba.dubbo.remoting.Client;
import com.alibaba.dubbo.remoting.RemotingException;
import com.alibaba.dubbo.remoting.Server;

public class DubboRemoteTest {
    int port = NetUtils.getAvailablePort();

    @Test
    public void test() throws RemotingException, InterruptedException {
        Server server = new TestNettyServer().setUpServer(port);
        
        Client client = new TestNettyClient().setUpClient(port, 200);
        client.send("i see you");
        TimeUnit.SECONDS.sleep(1);
    }
}
