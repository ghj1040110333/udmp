package cn.com.git.udmp.batch.test.communicate;

import org.junit.Test;

import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Builder;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Header;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Receiver;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Sender;
import cn.com.git.udmp.component.batch.communication.protobuf.protobuf.NettyClient;
import cn.com.git.udmp.component.batch.communication.protobuf.protobuf.NettyServer;
import cn.com.git.udmp.component.batch.communication.protobuf.protobuf.NettyServerHandler;

public class NettyTest {
    /**
     * @title
     * @description 测试启动服务器
     * 
     * @throws Exception
     */
    @Test
    public void testServerInit() throws Exception {
        NettyServer server = new NettyServer(new NettyServerHandler() {

            @Override
            public void handle(Message request) {
                System.out.println("server 收到了requset:"+request);
            }
        });
        server.start(8888);

    }
    
    public static void main(String[] args) throws Exception {
        new NettyTest().testClientInit();
    }

    public void testClientInit() throws Exception {
//        Builder messageBuilder = BatchMessage.Message.newBuilder();
//        messageBuilder.setHeader(Header.newBuilder().setCommand("123").setCommandType("start").build());
//        messageBuilder.setSender(Sender.newBuilder().setFromIp("127.0.0.1").setFromPort("9999").build());
//        messageBuilder.setReceiver(Receiver.newBuilder().setToIp("127.0.0.1").setToPort("8888").build());
//
//        Message message = messageBuilder.build();
//        int i = 0;
        NettyClient client = new NettyClient("127.0.0.1",8888);
        client.connect();
//        while (i++ < 10) {
//            new Thread(() -> {
//                try {
//                    client.sendMsg(message);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }).start();
//        }
        Thread.sleep(20000);
    }
}
