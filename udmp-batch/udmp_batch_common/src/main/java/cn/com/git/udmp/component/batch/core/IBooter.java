package cn.com.git.udmp.component.batch.core;

import org.springframework.scheduling.annotation.Async;

import cn.com.git.udmp.core.logging.ILog;

/**
 * @description 初始化接口
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年3月30日
 * @version 1.0
 */
public interface IBooter extends ILog, ICloser {
    /**
     * @description 初始化方法
     */
    public void boot();

}
