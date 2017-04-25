package cn.com.git.udmp.component.batch.io;

import java.util.Properties;

import cn.com.git.udmp.core.logging.ILog;


/**
 * @description 资源加载
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年3月30日
 * @version 1.0
 */
public interface IResourceLoader extends ILog {
    /**
     * @description 加载资源
     * @param props 相关配置
     */
    public void load(Properties props);

    /**
     * @description 加载资源
     * @author liuliang liuliang@newchinalife.com
     */
    public void load();

    /**
     * @description 卸载配置
     * @author liuliang liuliang@newchinalife.com
     */
    public void unload();
}
