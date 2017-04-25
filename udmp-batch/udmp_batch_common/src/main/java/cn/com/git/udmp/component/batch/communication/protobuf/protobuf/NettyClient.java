package cn.com.git.udmp.component.batch.communication.protobuf.protobuf;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message;
import cn.com.git.udmp.core.logging.ILog;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class NettyClient implements IRemoteClient, ILog {
    private Channel channel;
    private Bootstrap b;
    private NioEventLoopGroup workerGroup;
    private String host;
    private int port;
    private AtomicBoolean initFlag = new AtomicBoolean(false);
    

    /**
     * @title 客户端发送消费给服务端
     * @description
     * 
     * @param msg
     */
    @Override
    public void sendMsg(Message msg) {
        if (channel != null && channel.isActive()) {
            // logger.info("发送消息给客户端:{}", msg);
            logger.debug("channel:{}",channel);
            channel.writeAndFlush(msg);
        } else {
            logger.debug("channel unactive");
        }
    }

    public NettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void close() {
        workerGroup.shutdownGracefully();
        initFlag.set(false);
    }

    public boolean isConnected() {
        return channel != null && channel.isActive();
    }

    @Override
    public void connect() {
        if(!initFlag.get()){
            initFlag.set(true);
        }else{
            return ;
        }
        if (b == null||(b.group()!=null&&b.group().isShutdown())) {
            b = new Bootstrap();
            workerGroup = new NioEventLoopGroup(5);
            b.group(workerGroup);
        }
        try {
            b.channel(NioSocketChannel.class);
            // b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    // decoded
                    ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                    ch.pipeline().addLast(new ProtobufDecoder(BatchMessage.Message.getDefaultInstance()));
                    // encoded
                    ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                    ch.pipeline().addLast(new ProtobufEncoder())
                            // 注册handler
                            // .addLast(new IdleStateHandler(0, 0, 5))
                            .addLast(new NettyClientHandler(NettyClient.this));
                }
            });
            doConnect();
        } catch (InterruptedException e) {
            logger.error("连接服务端发生异常:\n{}", e);
        } finally {
            // workerGroup.shutdownGracefully();
        }
    }

    public void doConnect() throws InterruptedException {
        if (channel != null && channel.isActive()) {
            return;
        }

        ChannelFuture future = b.connect(host, port);

        future.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture futureListener) throws Exception {
                if (futureListener.isSuccess()) {
                    channel = futureListener.channel();
                    logger.info("Connect to server successfully!");
                } else {
                    logger.warn("Failed to connect to server({}:{}), try connect after 10s",host,port);

                    futureListener.channel().eventLoop().schedule(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                doConnect();
                            } catch (InterruptedException e) {
                                logger.error("重连线程异常中断..{}", e);
                            }
                        }
                    }, 10, TimeUnit.SECONDS);
                }
            }
        });
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public static void main(String[] args) {
        try {
            new NettyClient("127.0.0.1", 8888).connect();
        } catch (Exception e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }
}
