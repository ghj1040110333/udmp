package cn.com.git.udmp.component.batch.communication.protobuf.protobuf;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.protobuf.CodedInputStream;

import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.BasicInfo;
import cn.com.git.udmp.component.batch.communication.protobuf.IAccepter;
import cn.com.git.udmp.core.logging.ILog;

/**
 * @description 协议的服务端
 * @author L.liang
 * @createDate 2014年12月29日
 * @version 1.0
 */
@Deprecated
public abstract class ProtoAccepter extends Thread implements IAccepter, ILog {
    private static List<HandlerThread> handlers = new ArrayList<ProtoAccepter.HandlerThread>();
    private ServerSocket server;
    private Socket socket;
    private ExecutorService es = Executors.newFixedThreadPool(30);// 通信接收线程池

    /**
     * @description socket接收到信息后的处理线程
     * @date 2015年7月7日 下午2:30:36
     */
    class HandlerThread extends Thread {
        private BatchMessage.Message message;

        /**
         * <p>
         * Title: 消息处理线程
         * </p>
         * <p>
         * Description: 消息处理线程
         * </p>
         * 
         * @param message 消息
         */
        public HandlerThread(Message message) {
            this.message = message;
        }

        @Override
        public void run() {
            BasicInfo basicInfo = message.getJob().getBasicInfo();
            // int command = message.getJob().getCommandInfo().getCommand();
            logger.debug("收到任务ID是{}", basicInfo.getId());
            logger.debug("收到任务名称是{}", basicInfo.getName());
            // logger.debug("收到的任务请求是:{}", command);
            logger.debug("处理的作业实现类是{}", basicInfo.getTaskClazz());
            handle(message);
        }

    }

    @Override
    public Message accept(String port) {
        try {
            logger.debug("服务器启动....,端口:{}", port);
            try {
                new NettyServer(new NettyServerHandler() {
                    
                    @Override
                    public void handle(Message request) {
                        // TODO 自动生成的方法存根
                        
                    }
                }).start(Integer.valueOf(port));
            } catch (Exception e1) {
                // TODO 自动生成的 catch 块
                logger.error("netty server启动异常,{}",e1);
            }
            server = new ServerSocket(Integer.parseInt(port));
            while (!this.isInterrupted()) {
                // 获取服务端的socket服务
                socket = server.accept();
                Thread.sleep(1000);
                logger.debug("socket server waiting...");
                System.out.println("socket server waiting...");
                // 获取socket的输入流
                try {
                    logger.debug("开始解析protobuf报文...");
                    // handler.handle(message);
                    // 将处理操作甩到异步线程当中，防止socket流被锁
                    CodedInputStream input = CodedInputStream.newInstance(socket.getInputStream());
                    BatchMessage.Message message = BatchMessage.Message.parseFrom(input);
                    HandlerThread thread = new HandlerThread(message);
                    handlers.add(thread);
                    es.submit(thread);
                    // 获取socket的输出流
                    // out = new PrintWriter(socket.getOutputStream(), true);
                    // out.close();
                } catch (IOException e) {
                    logger.debug("socket通讯异常:{}",e.getMessage());
                } catch (RuntimeException e) {
                    logger.debug("socket通讯异常:{}",e.getMessage());
                }finally {
                    try {
                        if (socket != null) {
                            // socket.shutdownInput();
                            // socket.shutdownOutput();
                            socket.close();
                        }
                    } catch (IOException e) {
//                        e.printStackTrace();
                        logger.debug("socket关闭异常:{}",e.getMessage());
                    }
                    logger.debug("-----server-buffer:" + server.getReceiveBufferSize());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            
        }finally {
            try {
                if (server != null) {
                    logger.debug("Socket监听端口关闭");
                    server.close();
                }
                interruptHandlers();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 
     * @title 关闭socket的服务端
     * @description
     *
     * @see cn.com.git.udmp.component.batch.communication.protobuf.IAccepter#stopAccepter()
     */
    public final void stopAccepter() {
        try {
            if (server != null) {
                server.close();
            }
            interruptHandlers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @title 关闭线程
     * @description
     *
     */
    private void interruptHandlers() {
        for (HandlerThread thread : handlers) {
            thread.interrupt();
        }
    }

    /**
     * @description 处理逻辑抽象化
     * @param message 消息对象
     */
    public abstract void handle(Message message);
}
