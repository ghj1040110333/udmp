package cn.com.git.udmp.batch.test.proto;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Builder;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Header;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Receiver;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Sender;
import cn.com.git.udmp.component.batch.communication.protobuf.protobuf.NettyServerHandler;
import cn.com.git.udmp.component.batch.communication.protobuf.protobuf.ProtoCommunicator;
import cn.com.git.udmp.component.batch.communication.server.AbstractRemoteServer;

public class RemoteTest {

    ProtoCommunicator client;
    
    public void setUpServer(String port) {
        new RemoteServer().accept(port);
    }
    
    public ProtoCommunicator setUpClient(){
        return new ProtoCommunicator();
    }
    
    @Test
    public void testServer(){
        String port = "8080";
        setUpServer(port);
//        new Thread(()->setUpServer(port)).start();
    }
    
    /**
     * @title 测试通信100次
     * @description
     * 
     * @throws IOException
     * @throws InterruptedException 
    */
    @Test
    public void testClient() throws IOException, InterruptedException{
        final String port = "8080";
        client=setUpClient();
        for(int i=0;i<10000;i++){
           final int j=i;
           new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    client.communicate(getMsg(j), "127.0.0.1", port);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();;
        }
        TimeUnit.SECONDS.sleep(30);
    }
    
    @Test
    @Deprecated
    public void testSendMsg() throws IOException, InterruptedException{
        final String port = "8080";
        new Thread(new Runnable() {
            @Override
            public void run() {
                setUpServer(port);
            }
        }).start();
        client = setUpClient();
        
        client.communicate(getMsg(1), "127.0.0.1", port);
        TimeUnit.SECONDS.sleep(20);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private Message getMsg(int i) {
      Builder messageBuilder = BatchMessage.Message.newBuilder();
      messageBuilder.setHeader(Header.newBuilder().setCommand("123").setCommandType("start").build());
      messageBuilder.setSender(Sender.newBuilder().setFromIp("127.0.0.1").setFromPort(String.valueOf(i)).build());
      messageBuilder.setReceiver(Receiver.newBuilder().setToIp("127.0.0.1").setToPort("8888").build());
      
      Message message = messageBuilder.build();
      return message;
    }























    private class RemoteServer extends AbstractRemoteServer {

        @Override
        public NettyServerHandler getHandler() {
            return new NettyServerHandler() {
                int count= 0;
                @Override
                public void handle(Message msg) {
                    System.out.println(msg);
                    count++;
                    System.out.println("计数:"+count);
                }
            };
        }

    }

}
