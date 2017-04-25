package cn.com.git.udmp.component.batch.core.strategy.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.ICloser;
import cn.com.git.udmp.component.batch.core.server.job.IJobController;
import cn.com.git.udmp.component.batch.core.strategy.IListenStrategy;

/**
 * @description 默认心跳监听策略
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年5月21日 下午1:54:59
 */
@Component
public class DefaultListenStrategy implements IListenStrategy {
    /**
     * @Fields frequency : 心跳监听频率(ms)
     */
    private long frequency = 30000;
    @Autowired
    @Qualifier("heartBeatController")
    private IJobController heartBeatController;
    private ListenThread listenThread; // 监听线程
    private ExecutorService es; // 线程池
    private List<Future<?>> listenThreadControls = new ArrayList<Future<?>>(); // 线程控制集合
    private static boolean stopFlag;// 线程停止的共享变量,默认值false

    @Override
    public void listen() {
        if (es == null||es.isShutdown()) {
            es = Executors.newFixedThreadPool(1);
            /**
             * add by L.liang 
             * @description - 添加一个停止hook,正常退出应用时回收当前线程池
             */
            Runtime.getRuntime().addShutdownHook(new Thread(){
                @Override
                public void run() {
                    logger.debug("回收心跳监听线程池");
                    if(es!=null){
                        es.shutdown();
                    }
                }
            });
        }
        stopFlag = false;
        logger.debug("默认心跳监听策略执行..");
        // TODO batch--默认的心跳监听策略,监听线程需要一个管理器维护，防止异常导致线程中断而无继续运行批处理
        listenThread = new ListenThread();
        Future<?> listenThreadControl = es.submit(listenThread);
        listenThreadControls.add(listenThreadControl);
    }

    /**
     * @description 监听线程
     * @author liuliang liuliang_wb@newchina.live
     * @date 2015年7月7日 下午4:35:05
     */
    class ListenThread implements Runnable {
        @Override
        public void run() {
            logger.debug("启动监听线程");
            long date = new Date().getTime();
            boolean initFlag = false;
            try {
                while (!stopFlag) {
                    long dateNow = new Date().getTime();
                    long time = dateNow - date;
                    if (time > frequency || !initFlag) {
                        initFlag = true;
                        date = dateNow;
                        JobSessionContext jobSessionContext = new JobSessionContext();
                        heartBeatController.control(jobSessionContext);
                    }
                }
                logger.debug("监听线程停止");
            } catch (Exception e) {
//                e.printStackTrace();
                logger.error("心跳线程异常：{}",e);
            } finally {
                // TODO 回收当前线程
            }
        }
    }

    @Override
    public void stoplisten() {
        try {
            stopFlag = true;
            if (listenThreadControls != null && listenThreadControls.size() != 0) {
                logger.debug("心跳监听正在停止当前心跳监听线程...");
                // 调用心跳监听事件的close停止方法
                ICloser closer = (ICloser) heartBeatController;
                closer.close();
                for (Future<?> key : listenThreadControls) {
                    boolean result = key.cancel(true);
                    logger.info("心跳监听关闭结果：{}", result);
                }
            }
        } finally {
                logger.debug("心跳监听进程已停止");
        }
    }
    
    
    

    public void setHeartBeatController(IJobController heartBeatController) {
        this.heartBeatController = heartBeatController;
    }

    public void setFrequency(long frequency) {
        this.frequency = frequency;
    }

    public void setStopFlag(boolean stopFlag) {
        DefaultListenStrategy.stopFlag = stopFlag;
    }

}
