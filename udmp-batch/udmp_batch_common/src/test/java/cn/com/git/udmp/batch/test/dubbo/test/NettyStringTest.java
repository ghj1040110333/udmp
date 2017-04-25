/*
 * Copyright 1999-2011 Alibaba Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.com.git.udmp.batch.test.dubbo.test;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.remoting.exchange.ExchangeClient;
import com.alibaba.dubbo.remoting.exchange.ExchangeServer;
import com.alibaba.dubbo.remoting.exchange.Exchangers;
import com.alibaba.dubbo.remoting.exchange.ResponseFuture;

import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Builder;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Header;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Receiver;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Sender;

/**
 * User: heyman
 * Date: 4/26/11
 * Time: 4:13 PM
 */
public class NettyStringTest {
    static ExchangeServer server;
    static ExchangeClient client;

    @BeforeClass
    public static void setUp() throws Exception {
        //int port = (int) (1000 * Math.random() + 10000);
        int port = 10001;
        System.out.println(port);
        int reconnectPeriod = 200;
        client = Exchangers.connect(URL.valueOf("telnet://127.0.0.1:" + port + "?client=netty&serialization=protobuf&check=false&"+Constants.RECONNECT_KEY+"="+reconnectPeriod ), new TelnetClientHandler());
        server = Exchangers.bind(URL.valueOf("telnet://127.0.0.1:" + port + "?server=netty&serialization=protobuf"), new TelnetServerHandler());
        System.out.println(client.isConnected());
        while(!client.isConnected()){
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(client.isConnected());
    }

    @Test
    public void testHandler() throws Exception {
        //Thread.sleep(20000);
        Message message ;
        Builder builder = Message.newBuilder();
        builder.setHeader(Header.newBuilder().setCommand("test").setCommandType("run"));
        
        builder.setSender(Sender.newBuilder().setFromIp("127.0.0.1").setFromPort("8080"));
        builder.setReceiver(Receiver.newBuilder().setToIp("127.0.0.1").setToPort("8080"));
        message = builder.build();
//        message = Message.getDefaultInstance();  
        
        ResponseFuture future = client.request(message, 10000);
        Message result = (Message)future.get();
//        Assert.assertEquals(result instanceof Message,result);
        System.out.println(result.getClass().getName());
    }

    @AfterClass
    public static void tearDown() throws Exception {
        try {
            if (server != null)
                server.close();
        } finally {
            if (client != null)
                client.close();
        }
    }
    
    
}