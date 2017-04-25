package cn.com.git.udmp.component.batch.io;

import java.util.List;

import org.springframework.scheduling.annotation.Async;

import cn.com.git.udmp.common.exception.FrameworkException;
import cn.com.git.udmp.component.batch.core.IBooter;

/**
 * @description 加载器工厂(继承初始化接口)
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年3月30日
 * @version 1.0
 */
public class LoaderFactory implements IBooter {
    private List<IResourceLoader> loaders;

    public List<IResourceLoader> getLoaders() {
        return loaders;
    }

    public void setLoaders(List<IResourceLoader> loaders) {
        this.loaders = loaders;
    }

    @Async
    @Override
    public void boot() {
        // 初始化加载器工厂
        for (IResourceLoader loader : loaders) {
            if (loader == null) {
                throw new FrameworkException("加载器初始化异常");
            }
            loader.load();
        }
    }

    @Override
    public void close() {
        // 停止工厂
        for (IResourceLoader loader : loaders) {
            if (loader == null) {
                throw new FrameworkException("加载器异常");
            }
            loader.unload();
        }
    }
}
