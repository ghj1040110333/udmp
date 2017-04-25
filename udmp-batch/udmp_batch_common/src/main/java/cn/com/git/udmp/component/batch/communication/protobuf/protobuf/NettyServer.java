package cn.com.git.udmp.component.batch.communication.protobuf.protobuf;

import java.util.concurrent.TimeUnit;

import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message;
import cn.com.git.udmp.core.logging.ILog;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

public class NettyServer implements IRemoteServer,ILog {
   
    EventLoopGroup bossGroup ;// (1)
    EventLoopGroup workerGroup;// (2)
    boolean startFlag = false;
    
    NettyServerHandler nettyServerHandler;
    
    public NettyServer(NettyServerHandler nettyServerHandler) {
        this.nettyServerHandler = nettyServerHandler;
    }
    
    @Override
    public void start(int port) throws Exception{
        start(port, nettyServerHandler);
    }
    
    
    private void start(int port,final NettyServerHandler nettyServerHandler) throws Exception {
        if(startFlag){
            return ;
        }
        try {
            startFlag =true;
            ServerBootstrap b = new ServerBootstrap();
            bossGroup =  new NioEventLoopGroup(10);
            workerGroup =  new NioEventLoopGroup(10);
            b.group(bossGroup, workerGroup); // (4)
            b.channel(NioServerSocketChannel.class);
            b.handler(new LoggingHandler(LogLevel.INFO));
            b.childHandler(new ChannelInitializer<SocketChannel>() {
                private static final int READ_IDEL_TIME_OUT = 4; // 读超时
                private static final int WRITE_IDEL_TIME_OUT = 5;// 写超时
                private static final int ALL_IDEL_TIME_OUT = 7; // 所有超时
                
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    //设置超时处理器，若超时会设置超时状态
                    ch.pipeline().addLast(new IdleStateHandler(READ_IDEL_TIME_OUT,
                WRITE_IDEL_TIME_OUT, ALL_IDEL_TIME_OUT, TimeUnit.SECONDS));
                    ch.pipeline().addLast(new ProtobufVarint32FrameDecoder())
                    // decoded
                    .addLast(new ProtobufDecoder(BatchMessage.Message.getDefaultInstance()))
                    // encoded
                    .addLast(new ProtobufVarint32LengthFieldPrepender())
                    .addLast(new ProtobufEncoder())
                    // 注册handler
                    .addLast(nettyServerHandler);
                }
            });
            b.option(ChannelOption.SO_BACKLOG, 128);
            b.childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture f = b.bind(port).sync();
            // 等待服务端监听端口关闭
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            startFlag =false;
            throw e;
        }finally {
            logger.info("服务停止，准备关闭服务");
            shutdown();
            startFlag = false;
        }
    }
    
    /**
     * @title 停止netty Server
     * @description
     *  
    */
    @Override
    public void shutdown() {
        try {
            logger.info("关闭nettyServer的worker通道和boss通道");
            if(null!=workerGroup){
                workerGroup.shutdownGracefully();
            }
            if(null!=bossGroup){
                bossGroup.shutdownGracefully();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        }
    }
    
    public static void main(String[] args) throws Exception {
        final NettyServerHandler nettyServerHandler = new NettyServerHandler() {
            
            @Override
            public void handle(Message request) {
                System.out.println("handler it");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    // TODO 自动生成的 catch 块
                    e.printStackTrace();
                }
            }
        };
        final NettyServer nettyServer = new NettyServer(nettyServerHandler);
        final int port = 8888;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    nettyServer.start(port , nettyServerHandler);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        System.out.println("启动了");
//        Thread.sleep(1000);
        nettyServer.shutdown();
        System.out.println("停止了");
        Thread.sleep(3000);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    nettyServer.start(port , nettyServerHandler);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        System.out.println("又启动了");
        Thread.sleep(3000);
    }
    
}
