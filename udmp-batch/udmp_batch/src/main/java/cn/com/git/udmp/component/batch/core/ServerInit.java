package cn.com.git.udmp.component.batch.core;

import java.util.List;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import cn.com.git.udmp.component.batch.core.server.communicate.BatchSocketServer;
import cn.com.git.udmp.core.logging.ILog;

/**
 * @description 服务器初始化 <li>根据配置自动初始化服务器的所有配置</li>
 * @author liuliang 
 * @date 2015年4月8日
 * @version 1.0
 */
public class ServerInit implements ApplicationListener, ILog {
    private List<IBooter> booters;
    private boolean initFlag;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            if (initFlag) {
                logger.debug("服务器初始化...");
                init();
                initFlag = false;
            }
            // TODO 将jobManager统一管理起来，后续删除
        }
    }

    /**
     * @description 初始化
     */
    public void init() {
        try {
            for (IBooter booter : booters) {
                booter.boot();
            }
        } catch (RuntimeException e) {
//            e.printStackTrace();
            logger.error("初始化异常:{}", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * @description 根据自定义的端口初始化批处理
     * @author liuliang 
     * @param port 端口
    */
    public void initByCustom(String port) {
        try {
            for (IBooter booter : booters) {
                if (booter instanceof BatchSocketServer) {
                    // 如果发现启动器是启动socket通信端口，则更新启动端口
                    // TODO 更新后的端口需要入库或者写入配置文件中
                    ((BatchSocketServer) booter).setPort(port);
                }
                booter.boot();
            }
        } catch (RuntimeException e) {
            logger.error("初始化异常:{}", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * @description 批处理框架停止
     * @author liuliang 
     */
    public void shutdown() {
        try {
            for (IBooter booter : booters) {
                booter.close();
            }
        } catch (RuntimeException e) {
            logger.error("停止异常:{}", e);
            throw new RuntimeException(e);
        }
    }
    
    @Override
    protected void finalize() throws Throwable {
        shutdown();
        super.finalize();
    }

    public void setBooters(List<IBooter> booters) {
        this.booters = booters;
    }

    public void setInitFlag(boolean initFlag) {
        this.initFlag = initFlag;
    }
}
