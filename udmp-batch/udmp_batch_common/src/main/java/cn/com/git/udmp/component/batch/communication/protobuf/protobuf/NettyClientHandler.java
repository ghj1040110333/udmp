package cn.com.git.udmp.component.batch.communication.protobuf.protobuf;

import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message;
import cn.com.git.udmp.core.logging.ILog;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

@Sharable
public  class NettyClientHandler extends ChannelInboundHandlerAdapter implements ILog{

    public  NettyClientHandler() {
    }
    private NettyClient client;
    
    public  NettyClientHandler(NettyClient client) {
        this.client = client;
    }
    
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("AuthClientInitHandler activeCaught");
        if(MessageCache.getMsgCache().size()>0){
            for(Message msg:MessageCache.getMsgCache()){
                    ctx.channel().write(msg);
            }
        }
    }
    
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.warn("----- {}:{} inactive",client.getHost(),client.getPort());
        super.channelInactive(ctx);
        client.doConnect();
    }
    
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        logger.info("AuthClientInitHandler channelRead");
        logger.info("收到服务回执:{}",msg);
//        System.out.println("response: code="+response.getResultCode()+", message="+response.getResultMessage());
        //ctx.close();
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        logger.error("client 发生异常:{}",cause);
    }
    
}
