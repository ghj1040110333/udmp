package cn.com.git.udmp.batch.test.communicate;

import org.junit.Test;

import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message;
import cn.com.git.udmp.component.batch.communication.protobuf.protobuf.ProtoAccepter;

/** 
 * @description sockerServer单元测试类
 * @author Liang liuliang1@git.com.cn 
 * @date 2016年12月29日 下午3:46:23  
*/
public class SocketServerTest {
    /**
     * @throws InterruptedException 
     * @title
     * @description 测试关闭socketServer
     *  
    */
    @Test
    public void testShutdownServer() throws InterruptedException{
        final ProtoAccepter protoAccepter = new ProtoAccepter() {
            @Override
            public void handle(Message message) {
                return ;
            }
            @Override
            public void run() {
                this.accept("8888");
            }
        };
        protoAccepter.start();
        Thread.sleep(1000);
        System.out.println("停止server");
//        protoAccepter.interrupt();
//        Thread.sleep(1000);
        protoAccepter.stopAccepter();
        Thread.sleep(1000);
        new Thread(new Runnable() {
            @Override
            public void run() {
                protoAccepter.accept("8888");
            }
        }).start();
        Thread.sleep(10000);
    }
}
