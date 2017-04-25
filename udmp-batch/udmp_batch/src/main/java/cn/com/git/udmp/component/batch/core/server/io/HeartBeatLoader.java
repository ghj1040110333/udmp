package cn.com.git.udmp.component.batch.core.server.io;

import java.util.Properties;

import org.springframework.scheduling.annotation.Async;

import cn.com.git.udmp.component.batch.core.strategy.IListenStrategy;
import cn.com.git.udmp.component.batch.io.IResourceLoader;

/**
 * @description 心跳监听加载器
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年5月21日 下午1:48:26
 */
public class HeartBeatLoader implements IResourceLoader {
    private IListenStrategy listenStrategy;

    @Override
    public void load(Properties props) {
        listenStrategy.listen();
    }
    
    @Async
    @Override
    public void load() {
        load(null);
    }

    public void setListenStrategy(IListenStrategy listenStrategy) {
        this.listenStrategy = listenStrategy;
    }

    @Override
    public void unload() {
        logger.debug("********heartBeatLoader unload********");
        listenStrategy.stoplisten();
    }
    
}
