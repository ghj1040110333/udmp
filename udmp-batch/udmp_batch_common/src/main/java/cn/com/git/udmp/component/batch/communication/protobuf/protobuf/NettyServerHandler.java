package cn.com.git.udmp.component.batch.communication.protobuf.protobuf;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message;
import cn.com.git.udmp.core.logging.ILog;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

@Sharable
public abstract class NettyServerHandler extends ChannelInboundHandlerAdapter implements ILog{
    private ExecutorService es = Executors.newFixedThreadPool(10); // 线程池
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        logger.info( "AuthServerInitHandler channelRead");
        final BatchMessage.Message request=(BatchMessage.Message)msg;
        //TODO 修改成批处理的通信
        logger.debug("收到请求，来自{}:{}",request.getSender().getFromIp(),request.getSender().getFromPort());
        //        BatchMessage.Message response=BatchMessage.Message.newBuilder().setResultCode(0)
//                                .setResultMessage("success")
//                                .build();
//        ctx.writeAndFlush(response);
        Future<?> listenThreadControl = es.submit(new Thread(new Runnable() {
            @Override
            public void run() {
                handle(request);
            }
        }));
        
        ctx.flush();
        //ctx.close();
    }
    
    

    public abstract  void handle(Message request);



    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        logger.info("AuthServerInitHandler channelReadComplete");
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        logger.info("AuthServerInitHandler exceptionCaught:{}",cause);
        ctx.flush();
    }
}
